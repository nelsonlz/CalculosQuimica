package quimica.nldev.com.calculosdequimica;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by nelson on 28/05/2015.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DBQUIMICA = "DBQUIMICA";
    private static int VERSAO = 1;

    private static final String RENDIMENTO = "create table rendimento(id int not null primary key auto_increment, " +
            "descricao varchar(100), " +
            "data datetime, " +
            "massa_reagente numeric, " +
            "massa_molar_reagente numeric, " +
            "massa_produto numeric, " +
            "massa_molar_produto numeric," +
            "resultado numeric)";

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(RENDIMENTO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
