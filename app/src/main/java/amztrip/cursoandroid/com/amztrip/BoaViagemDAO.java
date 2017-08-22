package amztrip.cursoandroid.com.amztrip;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Gabriel on 21/08/17.
 */

public class BoaViagemDAO {

    private DatabaseHelper helper;
    private SQLiteDatabase db;
    public BoaViagemDAO(Context context){
        helper = new DatabaseHelper(context);
    }
    private SQLiteDatabase getDb() {
        if (db == null) {

            db = helper.getWritableDatabase();
        }
        return db; }
    public void close(){
        helper.close();
    }

}
