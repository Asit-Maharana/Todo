package com.example.todo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import androidx.annotation.NonNull;
import java.util.ArrayList;
public class SqliteConnection extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "TODOApp.db";
    private static final String DATA = "WorkTable";
    private static final String COLUMN_ID = "ID";
    private static final String COLUMN_NAME = "Name";
    private static final String COLUMN_DESC = "description";

    SqliteConnection(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " +
                DATA + "(" + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_NAME + " TEXT NOT NULL," + COLUMN_DESC + " TEXT)";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,
                          int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DATA);
        onCreate(db);
    }

    public ArrayList<ModelClass> loadHandler() {
        Log.d("myTag", "there is an error in LoadHandler");
        String query = "Select * FROM " + DATA;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        /*int s = cursor.getCount();
        Log.d(String.valueOf(s), "loadHandler: s");
         String[] result = new String[s];
        String res = "";*/
        ArrayList<ModelClass> stringArrayList = new ArrayList<>();
        while (cursor.moveToNext()) {
            ModelClass modelClass = new ModelClass();
            modelClass.setId(cursor.getInt(0));
            modelClass.setName(cursor.getString(1));
            modelClass.setDescription(cursor.getString(2));
            stringArrayList.add(modelClass);
            Log.d("SqliteConnection", "model item  : "+modelClass);
        }
        cursor.close();
        db.close();
        Log.d("SqliteConnection", "List items  : "+stringArrayList);

        return stringArrayList;
    }
    long addHandler(ModelClass modelClass) {
        long id;
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, modelClass.getId());
        values.put(COLUMN_NAME, modelClass.getName());
        values.put(COLUMN_DESC, modelClass.getDescription());
        SQLiteDatabase db = this.getWritableDatabase();
        id = db.insert(DATA, null, values);
        db.close();
        return id;
    }

    boolean updateHandler(int ID, String name, String desc) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues args = new ContentValues();
        args.put(COLUMN_ID, ID);
        args.put(COLUMN_NAME, name);
        args.put(COLUMN_DESC, desc);
        return db.update(DATA, args, COLUMN_ID + "=" + ID, null) > 0;
    }

    boolean deleteHandler(int ID) {
        boolean result = false;
        String query = "Select*FROM " + DATA + " WHERE " + COLUMN_ID + " = '" + String.valueOf(ID) + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        ModelClass modelClass = new ModelClass();
        if (cursor.moveToFirst()) {
            modelClass.setId(Integer.parseInt(cursor.getString(0)));
            db.delete(DATA, COLUMN_ID + "=?",
                    new String[]{
                            String.valueOf(modelClass.getId())
                    });
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }
}