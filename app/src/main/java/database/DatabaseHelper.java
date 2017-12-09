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

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db= this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+TABLE_NAME+" (ID INTEGER PRIMARY KEY AUTOINCREMENT, TITLE TEXT, CONTENT TEXT, DATE TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String title, String content, String date) {
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_2,title);
        contentValues.put(COL_3,content);
        contentValues.put(COL_4,date);
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
        long result= db.insert(TABLE_NAME,null,contentValues);

        if(result==-1)
            return false;
        else
            return true;
    }

    public Cursor SelectData() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM "+TABLE_NAME+";", null);
        if (c.moveToFirst()){
            do {
                String column1 = c.getString(0);
                String column2 = c.getString(1);
                String column3 = c.getString(2);
            } while(c.moveToNext());
        }
        //c.close();
        //db.close();
        return c;
    }

    public void DeleteAllData() {
        SQLiteDatabase db= this.getWritableDatabase();
        db.execSQL("DELETE FROM "+TABLE_NAME+" WHERE ID<100;");
    }
}
