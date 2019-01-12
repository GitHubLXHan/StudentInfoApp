package com.example.hany.studentinfoapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

/**
 * @author 6小h
 * @e-mail 1026310040@qq.com
 * @date 2019/1/11 19:47
 * @filName MyAdapter
 * @describe ...
 */
public class MyAdapter extends ArrayAdapter<Student> {
    private OnDeleteClickListener onDeleteClickListener;

    public MyAdapter(@NonNull Context context, int resource, List<Student> students) {
        super(context, resource, students);
    }

    public void setOnDeleteClickListener(OnDeleteClickListener onDeleteClickListener) {
        this.onDeleteClickListener = onDeleteClickListener;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView,
                        @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).
                    inflate(R.layout.info_item, parent, false);
        }
        // 获取控件
        TextView idTxt = convertView.findViewById(R.id.txt_id);
        TextView nameTxt = convertView.findViewById(R.id.txt_name);
        TextView ageTxt = convertView.findViewById(R.id.txt_age);
        Button deleteBtn = convertView.findViewById(R.id.btn_delete);
        // 给控件赋值
        idTxt.setText(String.valueOf(getItem(position).getId()));
        nameTxt.setText(String.valueOf(getItem(position).getName()));
        ageTxt.setText(String.valueOf(getItem(position).getAge()));
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onDeleteClickListener != null) {
                    onDeleteClickListener.onDeleteClick(view, position);
                }
            }
        });
        return convertView;
    }

    public interface OnDeleteClickListener {
        void onDeleteClick(View view, int position);
    }
}
