package amztrip.cursoandroid.com.amztrip;

import android.app.ListActivity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Gabriel on 12/08/17.
 */

public class GastoListActivity extends ListActivity implements AdapterView.OnItemClickListener{

    private List<Map<String, Object>> gastos;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String[] de = {"data", "descrição", "valor", "categoria"};
        int[] para = {R.id.data,R.id.tv_descricao, R.id.categoria};

        SimpleAdapter adapter = new SimpleAdapter(this, listarGastos(), R.layout.lista_gasto, de, para);

        adapter.setViewBinder(new GastoViewBinder());

        setListAdapter(adapter);
        getListView().setOnClickListener((View.OnClickListener) this);

        // registramos aqui o novo menu de contexto
        registerForContextMenu(getListView());
    }

    private List<Map<String, Object>> listarGastos() {

        gastos = new ArrayList<Map<String, Object>>();

        Map<String, Object> item = new HashMap<String, Object>();
        item.put("data", "04/02/2017");
        item.put("descrição", "Diária Hotel");
        item.put("valor", "R$ 260,00");
        item.put("data", R.color.colorGreenNewTrip);
        gastos.add(item);

        return gastos;

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Map<String, Object> map = gastos.get(position);
        String descricao = (String) map.get("descricao");
        String mensagem = "Gasto selecionado: " + descricao;
        Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show();

    }

    private String dataAnterior = "";

    private class GastoViewBinder implements SimpleAdapter.ViewBinder{

        @Override
        public boolean setViewValue(View view, Object data, String textRepresentation) {

//            if (view.getId() == R.id.barraProgresso) {
//                Double valores[] = (Double[]) data;
//                ProgressBar progressBar = (ProgressBar) view;
//                progressBar.setMax(valores[0].intValue());
//                progressBar.setSecondaryProgress(valores[1].intValue());
//                progressBar.setProgress(valores[2].intValue());
//                return true;
//            }
//
//            return false;

            if(view.getId() == R.id.data){
                if(!dataAnterior.equals(data)){
                    TextView textView = (TextView) view;
                    textView.setText(textRepresentation);
                    dataAnterior = textRepresentation;
                    view.setVisibility(View.VISIBLE);
                } else {
                    view.setVisibility(View.GONE);
                }
                return true;
            }

            if (view.getId() == R.id.categoria){

                Integer id = (Integer) data;
                view.setBackgroundColor(getResources().getColor(id));
                return true;
            }

            return false;
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.gasto_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.remover) {
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item
                    .getMenuInfo();
            gastos.remove(info.position);
            getListView().invalidateViews();
            dataAnterior = "";
            // remover do banco de dados
            return true;
        }
        return super.onContextItemSelected(item);
    }

}
