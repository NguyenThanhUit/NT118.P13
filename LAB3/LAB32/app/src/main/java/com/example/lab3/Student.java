package com.example.lab3;

public class Student {
    private String mssv;
    private String name;
    private String phonenumber;
    private String ngaysinh;

    public Student(String  mssv, String name, String phonenumber, String ngaysinh) {
        this.mssv = mssv;
        this.name = name;
        this.phonenumber = phonenumber;
        this.ngaysinh = ngaysinh;
    }

    public String getMssv() {
        return mssv;
    }

    public void setMssv(String mssv) {
        this.mssv = mssv;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(String ngaysinh) {
        this.ngaysinh = ngaysinh;
    }
}
