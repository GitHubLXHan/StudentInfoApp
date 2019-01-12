package com.example.hany.studentinfoapp;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author 6小h
 * @e-mail 1026310040@qq.com
 * @date 2019/1/11 19:42
 * @filName Student
 * @describe ...
 */
public class Student implements Parcelable{

    private int id;
    private String name;
    private int age;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeInt(age);
    }

    public static final Parcelable.Creator<Student> CREATOR = new Parcelable.Creator<Student>() {
        @Override
        public Student createFromParcel(Parcel parcel) {
            Student student = new Student();
            student.id =parcel.readInt();
            student.name = parcel.readString();
            student.age = parcel.readInt();
            return student;
        }

        @Override
        public Student[] newArray(int i) {
            return new Student[i];
        }
    };

}
