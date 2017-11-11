package name.hd.cloudmoney.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by AkiNobunaga on 2017/10/23.
 */

public class CreateDB extends SQLiteOpenHelper {
    String user="create table personInfo(name text,sex text,age Integer);";
    String state="create table someState(name text,state int)";
    public CreateDB(Context context, int version) {
        super(context, "person.db", null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(user);
        db.execSQL(state);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
