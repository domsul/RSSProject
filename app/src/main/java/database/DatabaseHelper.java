package database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import ui.main.MainActivity;

/**
 * Created by Dominik on 2017-12-08.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="Database.db";
    public static final String TABLE_NAME="news_table";
    public static final String COL_1="ID";
    public static final String COL_2="TITLE";
    public static final String COL_3="CONTENT";
    public static final String COL_4="DATE";
    public static final String COL_5="DESCRIPTION";
    public static final String COL_6="CATEGORY";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db= this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+TABLE_NAME+" (ID INTEGER PRIMARY KEY AUTOINCREMENT, TITLE TEXT, CONTENT TEXT, DATE TEXT, DESCRIPTION TEXT, CATEGORY TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String title, String content, String date, String description, String category) {
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_2,title);
        contentValues.put(COL_3,content);
        contentValues.put(COL_4,date);
        contentValues.put(COL_5,description);
        contentValues.put(COL_6,category);
        long result= db.insert(TABLE_NAME,null,contentValues);

        if(result==-1)
            return false;
        else
            return true;
    }

    public boolean insertByleCo() {
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_2,"aa");
        contentValues.put(COL_3,"bb");
        contentValues.put(COL_4,"cc");
        contentValues.put(COL_5,"dd");
        contentValues.put(COL_6,"ee");
        long result= db.insert(TABLE_NAME,null,contentValues);

        if(result==-1)
            return false;
        else
            return true;
    }

    public Cursor SelectData() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM "+TABLE_NAME+";", null);

        return c;
    }

    public void DeleteAllData() {
        SQLiteDatabase db= this.getWritableDatabase();
        db.execSQL("DELETE FROM "+TABLE_NAME+" WHERE ID<9999;");
    }

    public void Destroy() {
        SQLiteDatabase db= this.getWritableDatabase();
        db.execSQL("DROP TABLE "+TABLE_NAME);
        onCreate(db);
    }
}
