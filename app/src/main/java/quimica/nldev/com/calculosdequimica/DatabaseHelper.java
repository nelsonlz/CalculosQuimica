package quimica.nldev.com.calculosdequimica;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by nelson on 28/05/2015.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DBQUIMICA = "DBQUIMICA";
    private static int VERSAO = 1;
    private static String TAG = "NL";

    private static final String RENDIMENTO = "create table rendimento(id integer not null primary key AUTOINCREMENT, " +
            "descricao varchar(100), " +
            "data datetime, " +
            "massa_reagente numeric, " +
            "massa_molar_reagente numeric, " +
            "massa_produto numeric, " +
            "massa_molar_produto numeric," +
            "resultado numeric)";

    public DatabaseHelper(Context context) {
        super(context, DBQUIMICA, null, VERSAO);
        Log.v(TAG, "vai criar base");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.v(TAG, "vai criar banco");
        db.execSQL(RENDIMENTO);
        Log.v(TAG, "vai Criou");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
