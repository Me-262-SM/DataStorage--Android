package com.edu.sicnu.cs.zzy.datastorage;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class DataBaseActivity extends AppCompatActivity {
    private EditText et_name,et_age;
    private SQLiteDatabase db;
    private ListView listView;
    private MySQLiteHepler mySQLiteHepler;

    SimpleCursorAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_base);

        et_name = findViewById(R.id.et_name);
        et_age = findViewById(R.id.et_age);
        listView = findViewById(R.id.listView);

        mySQLiteHepler = new MySQLiteHepler(this,"db_test",null,1);
        db = mySQLiteHepler.getWritableDatabase();

        Cursor cursor = db.query(MySQLiteHepler.tableName, null, null, null, null, null, null, null);
        adapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2, cursor, new String[]{"name", "age"}, new int[]{android.R.id.text1, android.R.id.text2}, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);

        listView.setAdapter(adapter);
    }
    //增
    public void add(View v){
        String name = et_name.getText().toString();
        String age = et_age.getText().toString();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name);
        contentValues.put("age",age);
        db.insert(MySQLiteHepler.tableName,null,contentValues);
        reload();

    }

    //删
    public void delete(View v){
        String name = et_name.getText().toString();
        db.delete(MySQLiteHepler.tableName,"name=?",new String[]{name});
        reload();
    }

    //改
    public void update(View v){
        String name = et_name.getText().toString();
        String age = et_age.getText().toString();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name);
        contentValues.put("age",age);
        db.update(MySQLiteHepler.tableName,contentValues,"name=?",new String[]{name});
        reload();

    }

    //查
    public void retrieve(View v){
        String name = et_name.getText().toString();
        //Cursor cursor = db.query(MySQLiteHepler.tableName, null, "name like ?", new String[]{"%"+name+"%"}, null, null, null, null);
        //adapter.swapCursor(cursor);
        new MyAsyncTask().execute(new String[]{"%"+name+"%"});


    }

    private void reload() {
        Cursor cursor = db.query(MySQLiteHepler.tableName, null, null, null, null, null, null, null);
        adapter.swapCursor(cursor);
    }

    class MyAsyncTask extends AsyncTask<String,Integer,Cursor>{

        @Override
        protected Cursor doInBackground(String... strings) {
            Cursor cursor = db.query(MySQLiteHepler.tableName, null, "name like ?",strings, null, null, null, null);
            return cursor;
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            super.onPostExecute(cursor);
            adapter.swapCursor(cursor);
        }
    }
}
