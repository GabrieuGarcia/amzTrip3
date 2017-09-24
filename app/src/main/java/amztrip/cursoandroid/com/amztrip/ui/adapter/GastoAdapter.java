package amztrip.cursoandroid.com.amztrip.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import amztrip.cursoandroid.com.amztrip.R;
import amztrip.cursoandroid.com.amztrip.model.GastoModel;

/**
 * Created by Gabriel on 24/09/17.
 */

public class GastoAdapter extends BaseAdapter {

    private List<GastoModel> itens;
    private Context context;
    private LayoutInflater inflater;

    public GastoAdapter(ArrayList<GastoModel> list, Context context) {
        this.itens = list;
        this.context = context;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return itens.size();
    }

    @Override
    public Object getItem(int position) {
        return itens.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        GastoModel gasto = itens.get(position);
        view = inflater.inflate(R.layout.item_gasto_list, null);

        TextView categoria = (TextView) view.findViewById(R.id.item_categoria);
        TextView valor = (TextView) view.findViewById(R.id.item_valor);

        categoria.setText(gasto.getCategoria());
        valor.setText(gasto.getValor());

        return view;
    }
}
