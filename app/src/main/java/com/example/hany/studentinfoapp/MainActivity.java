package com.example.hany.studentinfoapp;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText mNameEdt;
    private EditText mAgeEdt;
    private Button mInsertBtn;
    private Button mQueryBtn;
    private MySQLiteHelper mHelper;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 初始化控件及给控件添加监听事件
        initView();
        // 获取Helper对象并打开数据库
        initDatabase();
    }

    /**
     * 获取Helper对象并打开数据库
     */
    private void initDatabase() {
        mHelper = new MySQLiteHelper(this);
        db = mHelper.getWritableDatabase();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        mNameEdt = findViewById(R.id.edt_main_name);
        mAgeEdt = findViewById(R.id.edt_main_age);
        mInsertBtn = findViewById(R.id.btn_main_insert);
        mQueryBtn = findViewById(R.id.btn_main_query);
        // 添加监听事件
        mInsertBtn.setOnClickListener(this);
        mQueryBtn.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_main_insert:
                String name = mNameEdt.getText().toString();
                int age = Integer.parseInt(mAgeEdt.getText().toString());
                ContentValues values = new ContentValues();
                values.put("name", name);
                values.put("age", age);
                long row = db.insert("information",null, values);
                values.clear();
                if (row != -1) {
                    mNameEdt.setText("");
                    mAgeEdt.setText("");
                    Toast.makeText(this, "插入数据成功", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_main_query:
                Intent intent = new Intent(MainActivity.this, QueryActivity.class);
                startActivity(intent);
                break;
        }
    }
}
