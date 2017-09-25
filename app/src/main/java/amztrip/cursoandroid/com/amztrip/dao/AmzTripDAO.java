package amztrip.cursoandroid.com.amztrip.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import amztrip.cursoandroid.com.amztrip.db.DatabaseHelper;
import amztrip.cursoandroid.com.amztrip.model.GastoModel;

/**
 * Created by Gabriel on 21/08/17.
 */

public class AmzTripDAO {

    private DatabaseHelper helper;
    private SQLiteDatabase db;

    public AmzTripDAO(Context context) {
        helper = new DatabaseHelper(context);
    }

    private SQLiteDatabase getDb() {
        if (db == null) {

            db = helper.getWritableDatabase();
        }
        return db;
    }

    public void close() {
        helper.close();
    }

    public void insertGasto(GastoModel gastoModel) {
        ContentValues contentValues = new ContentValues();

        db = helper.getWritableDatabase();

        contentValues.put("categoria", gastoModel.getCategoria());
        contentValues.put("valor", gastoModel.getValor());
        contentValues.put("tipo", gastoModel.getTipo());
        contentValues.put("local", gastoModel.getLocal());

        db.insert(DatabaseHelper.GASTO, null, contentValues);
        db.close();

    }

    public ArrayList<GastoModel> listaGastos() {
        ArrayList<GastoModel> result = new ArrayList<>();
        db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT categoria, valor , tipo, local FROM gasto", null);
        if (cursor.moveToFirst()) {
            do {
                GastoModel gastoModel = new GastoModel();
                gastoModel.setCategoria(cursor.getString(0));
                gastoModel.setValor(cursor.getString(1));
                gastoModel.setTipo(cursor.getString(2));
                gastoModel.setLocal(cursor.getString(3));

                result.add(gastoModel);

            } while (cursor.moveToNext());
        }

        return result;
    }

}
