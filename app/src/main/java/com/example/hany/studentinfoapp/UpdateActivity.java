package com.example.hany.studentinfoapp;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    private EditText mNameEdt;
    private EditText mAgeEdt;
    private Button mUpdateBtn;
    private MySQLiteHelper mHelper;
    private SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        // 获取Helper对象并打开数据库
        initDatabase();
        // 初始化控件及为其设置值及监听事件
        initView();
    }

    /**
     * 获取Helper对象并打开数据库
     */
    private void initDatabase() {
        mHelper = new MySQLiteHelper(this);
        db = mHelper.getWritableDatabase();
    }

    /**
     * 初始化控件及为其设置值及监听事件
     */
    private void initView() {
        mNameEdt = findViewById(R.id.edt_update_name);
        mAgeEdt = findViewById(R.id.edt_update_age);
        mUpdateBtn = findViewById(R.id.btn_update_update);
        final Student student = getIntent().getParcelableExtra("student");
        mNameEdt.setText(student.getName());
        mAgeEdt.setText(String.valueOf(student.getAge()));
        mUpdateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = mNameEdt.getText().toString();
                int age = Integer.parseInt(mAgeEdt.getText().toString());
                ContentValues values = new ContentValues();
                values.put("name", name);
                values.put("age", age);
                long row = db.update("information", values, "id = ?", new String[]{String.valueOf(student.getId())});
                if (row != -1) {
                    setResult(RESULT_OK);
                    Toast.makeText(UpdateActivity.this, "更新数据成功", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }
}
