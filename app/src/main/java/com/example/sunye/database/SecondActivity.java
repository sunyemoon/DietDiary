package com.example.sunye.database;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SecondActivity extends Activity {
    MyDBHelper mHelper;
    SQLiteDatabase db;
    Cursor cursor;
    MyCursorAdapter myAdapter;

    final static String KEY_ID = "_id";
    final static String KEY_NAME = "name";
    final static String KEY_AGE = "age";
    final static String TABLE_NAME = "mytable";

    final static String querySelectAll = String.format("SELECT * FROM %s", TABLE_NAME);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        ListView list = (ListView) findViewById(R.id.lv_name_age);

        mHelper = new MyDBHelper(this);
        db = mHelper.getWritableDatabase();

        cursor = db.rawQuery(querySelectAll, null);
        myAdapter = new MyCursorAdapter(this, cursor);

        list.setAdapter(myAdapter);

    }


    class MyCursorAdapter extends CursorAdapter {

        @SuppressWarnings("deprecation")
        public MyCursorAdapter(Context context, Cursor c) {
            super(context, c);
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {
            TextView tvName = (TextView) view.findViewById(R.id.tv_name);
            TextView tvAge = (TextView) view.findViewById(R.id.tv_age);

            String name = cursor.getString(cursor.getColumnIndex(KEY_NAME));
            String age = cursor.getString(cursor.getColumnIndex(KEY_AGE));

            Log.d("스트링 확인", name + ", " + age);

            tvName.setText(name);
            tvAge.setText(age);
        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {
            LayoutInflater inflater = LayoutInflater.from(context);
            View v = inflater.inflate(R.layout.list_item, parent, false);
            return v;
        }

    }

    public void mOnClick(View v) {
        EditText eName = (EditText) findViewById(R.id.et_name);
        EditText eAge = (EditText) findViewById(R.id.et_age);

        long now=System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat sdfNow = new SimpleDateFormat("yyyy년 MM월 dd일");
        String formatDate = sdfNow.format(date);

        //final String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());

        String name = formatDate + eName.getText().toString();

        try {
            int age = Integer.parseInt( eAge.getText().toString());

            String query = String.format(
                    "INSERT INTO %s VALUES ( null, '%s', %s );", TABLE_NAME, name, age);
            db.execSQL(query);
            cursor = db.rawQuery(querySelectAll, null);
            myAdapter.changeCursor(cursor);
        } catch (NumberFormatException e) {
            // Toast.makeText(this, "몸무게는 정수를 입력해야 합니다", Toast.LENGTH_LONG).show();
        }
        //eName.setText("");
        eAge.setText("");

        // 저장 버튼 누른 후 키보드 안보이게 하기
        InputMethodManager imm =
                (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(eAge.getWindowToken(), 0);
    }

    class MyDBHelper extends SQLiteOpenHelper {
        public MyDBHelper(Context context) {
            super(context, "MyData.db", null, 2);
        }

        public void onCreate(SQLiteDatabase db) {

            String query = String.format("CREATE TABLE %s ("
                    + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "%s TEXT, "
                    + "%s TEXT);", TABLE_NAME, KEY_NAME, KEY_AGE);
            db.execSQL(query);
        }

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            String query = String.format("DROP TABLE IF EXISTS %s", TABLE_NAME);
            db.execSQL(query);
            onCreate(db);
        }

    }
}