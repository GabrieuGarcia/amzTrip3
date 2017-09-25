package amztrip.cursoandroid.com.amztrip.ui;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;

import amztrip.cursoandroid.com.amztrip.R;
import amztrip.cursoandroid.com.amztrip.dao.AmzTripDAO;
import amztrip.cursoandroid.com.amztrip.model.GastoModel;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GastoActivity extends AppCompatActivity {

    private int ano, mes, dia;
    @BindView(R.id.btn_data) Button btnData;
    @BindView(R.id.btn_save) Button btnSave;
    @BindView(R.id.spinner_categoria) Spinner categoria;
    @BindView(R.id.edt_valor) EditText valor;
    @BindView(R.id.edt_tipo) EditText tipo;
    @BindView(R.id.edt_local) EditText lugar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gasto);

        ButterKnife.bind(this);

        Calendar calendar = Calendar.getInstance();
        ano = calendar.get(Calendar.YEAR);
        mes = calendar.get(Calendar.MONTH);
        dia = calendar.get(Calendar.DAY_OF_MONTH);

        btnData.setText(dia + "/" + (mes + 1) + "/" + ano);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.categoria_gasto, android.R.layout.simple_spinner_item);
        categoria.setAdapter(adapter);

    }

    @OnClick(R.id.btn_data)
    public void selecionarData(View view) {
        showDialog(view.getId());
    }

    @OnClick(R.id.btn_save)
    public void salvarGasto(View view) {
        AmzTripDAO dao = new AmzTripDAO(this);
        GastoModel gasto = new GastoModel();

        gasto.setCategoria(categoria.getSelectedItem().toString());
        gasto.setValor(valor.getText().toString());
        gasto.setTipo(tipo.getText().toString());
        gasto.setLocal(lugar.getText().toString());

        dao.insertGasto(gasto);

        Toast.makeText(this, "Gasto inserido com sucesso", Toast.LENGTH_SHORT).show();

    }

    @Override
    protected Dialog onCreateDialog(int id) {
        if (R.id.btn_data == id) {
            return new DatePickerDialog(this, listener, ano, mes, dia);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            ano = year;
            mes = month;
            dia = dayOfMonth;
            btnData.setText(dia + "/" + (mes + 1) + "/" + ano);
        }
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.novo_gasto:
                startActivity(new Intent(this, GastoActivity.class));
                return true;
            case R.id.remover:
                //remover viagem do banco de dados
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
