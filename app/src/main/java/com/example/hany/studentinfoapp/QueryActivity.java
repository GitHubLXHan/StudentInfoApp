package com.example.hany.studentinfoapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class QueryActivity extends AppCompatActivity {

    private ListView listView;
    private MyAdapter adapter;
    private List<Student> studets;
    private MySQLiteHelper mHelper;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query);
        // 获取Helper对象并打开数据库
        initDatabase();
        // 初始化ListView的数据
        initData();
        // 初始化ListView控件以及为其设置适配器
        initView();
        // 为List_item子项设置点击事件监听
        initEvent();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    studets.clear();
                    Cursor cursor= db.rawQuery("select * from information", new String[]{});
                    if (cursor.moveToFirst()) {
                        do {
                            Student student = new Student();
                            student.setId(cursor.getInt(cursor.getColumnIndex("id")));
                            student.setName(cursor.getString(cursor.getColumnIndex("name")));
                            student.setAge(cursor.getInt(cursor.getColumnIndex("age")));
                            studets.add(student);
                        } while (cursor.moveToNext());
                    }
                    adapter.notifyDataSetChanged();
                }
                break;
        }
    }

    /**
     * 为List_item子项设置点击事件监听
     */
    private void initEvent() {
        adapter.setOnDeleteClickListener(
                new MyAdapter.OnDeleteClickListener() {
            @Override
            public void onDeleteClick(View view, int position) {
                Student student = studets.get(position);
                db.delete("information", "id=?",
                        new String[]{String.valueOf(student.getId())});
                studets.remove(position);
                adapter.notifyDataSetChanged();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(QueryActivity.this, UpdateActivity.class);
                intent.putExtra("student", studets.get(i));
                startActivityForResult(intent, 1);
            }
        });
    }

    /**
     * 花去Helper对象并打开数据库
     */
    private void initDatabase() {
        mHelper = new MySQLiteHelper(this);
        db = mHelper.getWritableDatabase();
    }

    /**
     * 初始化ListView的数据
     */
    private void initData() {
        studets = new ArrayList<>();
        Cursor cursor= db.rawQuery("select * from information", new String[]{});
        if (cursor.moveToFirst()) {
            do {
                Student student = new Student();
                student.setId(cursor.getInt(cursor.getColumnIndex("id")));
                student.setName(cursor.getString(cursor.getColumnIndex("name")));
                student.setAge(cursor.getInt(cursor.getColumnIndex("age")));
                studets.add(student);
            } while (cursor.moveToNext());
        }
    }

    /**
     * 初始化ListView控件并为其设置适配器
     */
    private void initView() {
        listView = findViewById(R.id.list_view);
        adapter = new MyAdapter(this, R.layout.info_item, studets);
        listView.setAdapter(adapter);
    }
}
