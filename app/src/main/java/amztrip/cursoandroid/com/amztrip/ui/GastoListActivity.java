package amztrip.cursoandroid.com.amztrip.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import amztrip.cursoandroid.com.amztrip.R;
import amztrip.cursoandroid.com.amztrip.dao.BoaViagemDAO;
import amztrip.cursoandroid.com.amztrip.model.GastoModel;
import amztrip.cursoandroid.com.amztrip.ui.adapter.GastoAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GastoListActivity extends AppCompatActivity {

    @BindView(R.id.list)
    ListView list;

    @BindView(R.id.btn_novo_gasto)
    Button btnNovoGasto;

    private GastoAdapter gastoAdapter;
    private ArrayList<GastoModel> gastoList;
    private BoaViagemDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gasto_list);

        ButterKnife.bind(this);

        atualizaGastos();
    }

    @Override
    protected void onResume() {
        super.onResume();
        atualizaGastos();
    }

    @OnClick(R.id.btn_novo_gasto)
    public void novoGasto(View view) {
        startActivity(new Intent(this, GastoActivity.class));
    }

    private void atualizaGastos() {
        dao = new BoaViagemDAO(this);
        gastoList = new ArrayList<>();

        gastoList = dao.listaGastos();

        gastoAdapter = new GastoAdapter(gastoList, this);
        list.setAdapter(gastoAdapter);
    }

}
