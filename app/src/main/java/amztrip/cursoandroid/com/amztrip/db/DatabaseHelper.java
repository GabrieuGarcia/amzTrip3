package amztrip.cursoandroid.com.amztrip.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Gabriel on 21/08/17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String BANCO_DADOS = "amzTrip";
    private static int VERSAO = 1;
    public static final String VIAGEM = "viagem";
    public static final String GASTO = "gasto";

    public static class Viagem {
        public static final String TABELA = "viagem";
        public static final String _ID = "_id";
        public static final String DESTINO = "destino";
        public static final String DATA_CHEGADA = "data_chegada";
        public static final String DATA_SAIDA = "data_saida";
        public static final String ORCAMENTO = "orcamento";
        public static final String QUANTIDADE_PESSOAS =
                "quantidade_pessoas";
        public static final String TIPO_VIAGEM = "tipo_viagem";
        public static final String[] COLUNAS = new String[]{
                _ID, DESTINO, DATA_CHEGADA, DATA_SAIDA,
                TIPO_VIAGEM, ORCAMENTO, QUANTIDADE_PESSOAS };
    }

    public DatabaseHelper(Context context) {
        super(context, BANCO_DADOS, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + VIAGEM + " (_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " destino TEXT, tipo_viagem INTEGER, data_chegada DATE," +
                " data_saida DATE, orcamento DOUBLE," + " quantidade_pessoas INTEGER);");

        db.execSQL("CREATE TABLE " + GASTO + " (_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " categoria TEXT, data DATE, valor DOUBLE," +
                " tipo TEXT, local TEXT, viagem_id INTEGER," +
                " FOREIGN KEY(viagem_id) REFERENCES viagem(_id));");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("ALTER TABLE gasto ADD COLUMN pessoa TEXT");

    }

}

