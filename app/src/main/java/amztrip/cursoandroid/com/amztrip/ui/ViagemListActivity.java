package amztrip.cursoandroid.com.amztrip.ui;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import amztrip.cursoandroid.com.amztrip.util.Constantes;
import amztrip.cursoandroid.com.amztrip.db.DatabaseHelper;
import amztrip.cursoandroid.com.amztrip.R;

/**
 * Created by Gabriel on 12/08/17.
 */

public class ViagemListActivity extends ListActivity implements AdapterView.OnItemClickListener, DialogInterface.OnClickListener {

    private AlertDialog alertDialog;

    private AlertDialog dialogConfirmacao;

    private int viagemSelecionada;

    private DatabaseHelper helper;
    private SimpleDateFormat dateFormat;
    private Double valorLimite;


    private List<Map<String, Object>> viagens;

    private List<Map<String, Object>> listarViagens() {

        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT _id, tipo_viagem, destino, " + "data_chegada, data_saida, orcamento FROM viagem", null);

        cursor.moveToFirst();

        viagens = new ArrayList<Map<String, Object>>();

        for (int i = 0; i < cursor.getCount(); i++) {

            Map<String, Object> item = new HashMap<String, Object>();

            String id = cursor.getString(0);
            int tipoViagem = cursor.getInt(1);
            String destino = cursor.getString(2);
            long dataChegada = cursor.getLong(3);
            long dataSaida = cursor.getLong(4);
            double orcamento = cursor.getDouble(5);
            item.put("id", id);
            if (tipoViagem == Constantes.VIAGEM_LAZER)
            item.put("destino", destino);
            Date dataChegadaDate = new Date(dataChegada);
            Date dataSaidaDate = new Date(dataSaida);
            String periodo = dateFormat.format(dataChegadaDate) + " a "

                    + dateFormat.format(dataSaidaDate);
            item.put("data", periodo);
            double totalGasto = calcularTotalGasto(db, id);
            item.put("total", "Gasto total R$ " + totalGasto);
            double alerta = orcamento * valorLimite / 100;
            Double[] valores =
                    new Double[]{orcamento, alerta, totalGasto};
            item.put("barraProgresso", valores);
            viagens.add(item);
            cursor.moveToNext();
        }
        cursor.close();

        return viagens;
    }

    private String id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        helper = new DatabaseHelper(this);
        dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        SharedPreferences preferencias = PreferenceManager.getDefaultSharedPreferences(this);
        String valor = preferencias.getString("valor_limite", "-1");
        valorLimite = Double.valueOf(valor);

        String[] de = {"imagem", "destino", "data", "total", "barraProgresso"};
        int[] para = {R.id.imgTipoViagem, R.id.data, R.id.tv_destino, R.id.tv_valor, R.id.barraProgresso};

        SimpleAdapter adapter = new SimpleAdapter(this, listarViagens(), R.layout.lista_viagem, de, para);

        setListAdapter(adapter);

        getListView().setOnItemClickListener(this);

        this.alertDialog = criaAlertDialog();

        this.dialogConfirmacao = criaDialogConfirmacao();

    }


    private AlertDialog criaAlertDialog() {
        final CharSequence[] items = {
                getString(R.string.editar),
                getString(R.string.novo_gasto),
                getString(R.string.gastos_realizados),
                getString(R.string.remover)};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.opcoes);
        builder.setItems(items, (DialogInterface.OnClickListener) this);
        return builder.create();
    }

    private AlertDialog criaDialogConfirmacao() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.confirmacao_exclusao_viagem);
        builder.setPositiveButton(getString(R.string.sim), this);
        builder.setNegativeButton(getString(R.string.nao), this);
        return builder.create();
    }

    @Override
    public void onClick(DialogInterface dialog, int item) {

        Intent intent;
        String id = (String) viagens.get(viagemSelecionada).get("id");

        switch (item) {
            case 0: // editar viagem
                intent = new Intent(this, ViagemActivity.class);
                intent.putExtra(Constantes.VIAGEM_ID, id);
                startActivity(intent);
                break;
            case 1:
                startActivity(new Intent(this, ViagemActivity.class));
                break;
            case 2:
                startActivity(new Intent(this, GastoActivity.class));
                break;
            case 3:
                startActivity(new Intent(this, GastoListActivity.class));
                break;
            case 4:
                dialogConfirmacao.show();
                break;
            case DialogInterface.BUTTON_POSITIVE:
                viagens.remove(viagemSelecionada);
                removerViagem(id);
                getListView().invalidateViews();
                break;
            case DialogInterface.BUTTON_NEGATIVE:
                dialogConfirmacao.dismiss();
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Map<String, Object> map = viagens.get(position);
        String destino = (String) map.get("destino");
        String mensagem = "Viagem selecionada: " + destino;
        this.viagemSelecionada = position;
        alertDialog.show();

        Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, GastoListActivity.class));

    }

    private double calcularTotalGasto(SQLiteDatabase db, String id) {
        Cursor cursor = db.rawQuery("SELECT SUM(valor) FROM gasto WHERE viagem_id = ?",
                new String[]{ id });
        cursor.moveToFirst();
        double total = cursor.getDouble(0);
        cursor.close();
        return total;
    }


    private void removerViagem(String id) {
        SQLiteDatabase db = helper.getWritableDatabase();
        String where[] = new String[]{id};
        db.delete("gasto", "viagem_id = ?", where);
        db.delete("viagem", "_id = ?", where);
    }



}
