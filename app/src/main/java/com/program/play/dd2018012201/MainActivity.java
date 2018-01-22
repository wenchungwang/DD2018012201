package com.program.play.dd2018012201;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void click1(View v)
    {
        File dbFile = new File(getFilesDir(), "student.db");
        InputStream is = getResources().openRawResource(R.raw.student);

        try {
            OutputStream os = new FileOutputStream(dbFile);
            int r;
            while ((r = is.read()) != -1)
            {
                os.write(r);
            }
            is.close();
            os.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void click2(View v)
    {
        File dbFile = new File(getFilesDir(),"student.db");
        SQLiteDatabase db =SQLiteDatabase.openDatabase(dbFile.getAbsolutePath(),null,SQLiteDatabase.OPEN_READWRITE);
       Cursor c=db.rawQuery("Select * from student",null);
       c.moveToFirst();
        Log.d("DB", c.getString(1) + "," + c.getInt(2));

//        c.moveToNext();
//        Log.d("DB", c.getString(1) + "," + c.getInt(2));

        while (c.moveToNext())
        {
            Log.d("DB", c.getString(1) + "," + c.getInt(2));
        }

    }
    public void click3(View v)
    {
        File dbFile = new File(getFilesDir(), "student.db");
        SQLiteDatabase db = SQLiteDatabase.openDatabase(dbFile.getAbsolutePath(), null, SQLiteDatabase.OPEN_READWRITE);
       String strSql = "Select * from student where _id=?";       // ?  =1 個參數 看陣列
    Cursor c = db.rawQuery(strSql, new String[] {"2"});          //check444
      c.moveToFirst();
      Log.d("DB", c.getString(1) + "," + c.getInt(2));
        db.close();
    }
    public void click4(View v)
    {
        File dbFile = new File(getFilesDir(), "student.db");
        SQLiteDatabase db = SQLiteDatabase.openDatabase(dbFile.getAbsolutePath(), null, SQLiteDatabase.OPEN_READWRITE);
       Cursor c = db.query("student", new String[] {"_id", "name", "score"}, null, null, null, null, null);
        c.moveToFirst();
        Log.d("DB", c.getString(1) + "," + c.getInt(2));
    }
    public void click5(View v)
    {
        File dbFile = new File(getFilesDir(), "student.db");
        SQLiteDatabase db = SQLiteDatabase.openDatabase(dbFile.getAbsolutePath(), null, SQLiteDatabase.OPEN_READWRITE);
       Cursor c = db.query("student", new String[] {"_id", "name", "score"}, "_id=?", new String[] {"2"}, null, null, null);
       c.moveToFirst();
        Log.d("DB", c.getString(1) + "," + c.getInt(2));
        db.close();
    }

    public void click6(View v)
    {
        File dbFile = new File(getFilesDir(), "student.db");
        SQLiteDatabase db = SQLiteDatabase.openDatabase(dbFile.getAbsolutePath(), null, SQLiteDatabase.OPEN_READWRITE);
        db.execSQL("Insert into student (_id, name, score) values (3, 'Bob', 95)");
        db.close();
    }

    public void click7(View v)
    {
        File dbFile = new File(getFilesDir(), "student.db");
        SQLiteDatabase db = SQLiteDatabase.openDatabase(dbFile.getAbsolutePath(), null, SQLiteDatabase.OPEN_READWRITE);
        ContentValues cv = new ContentValues();
// last data ???
        cv.put("_id", 4);
        cv.put("name", "Mary");
        cv.put("score", 92);
/*
        cv.put("_id", 5);
        cv.put("name", "5555");
        cv.put("score", 92);

        cv.put("_id", 6);
        cv.put("name", "6666");
        cv.put("score", 92);
*/
        db.insert("student", null, cv);
        db.close();
    }

    public void click8(View v)
    {
        File dbFile = new File(getFilesDir(), "student.db");
        SQLiteDatabase db = SQLiteDatabase.openDatabase(dbFile.getAbsolutePath(), null, SQLiteDatabase.OPEN_READWRITE);
        ContentValues cv = new ContentValues();
        cv.put("score", 85);
        db.update("student", cv, "_id=?", new String[] {"3"});
        db.close();
    }
    public void click9(View v)
    {
        File dbFile = new File(getFilesDir(), "student.db");
        SQLiteDatabase db = SQLiteDatabase.openDatabase(dbFile.getAbsolutePath(), null, SQLiteDatabase.OPEN_READWRITE);
        db.delete("student", "_id=?", new String[] {"3"});
        db.close();
    }

}
