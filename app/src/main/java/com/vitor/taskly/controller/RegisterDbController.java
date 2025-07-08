package com.vitor.taskly.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.vitor.taskly.db.RegisterDb;
import com.vitor.taskly.model.Login;

public class RegisterDbController {

    private SQLiteDatabase db;
    private RegisterDb rdb;

    public RegisterDbController(Context context) {
        rdb = new RegisterDb(context);
    }

    public void insert(String name, String phone, String email, String password) {
        ContentValues data;

        db = rdb.getWritableDatabase();
        data = new ContentValues();
        data.put(RegisterDb.NAME, name);
        data.put(RegisterDb.PHONE, phone);
        data.put(RegisterDb.EMAIL, email);
        data.put(RegisterDb.PASSWORD, password);

        db.insert(RegisterDb.TABLE, null, data);

    }

    public boolean loginRegister(Login login) {
        db = rdb.getReadableDatabase();

        String query = "SELECT * FROM " + RegisterDb.TABLE +
                " WHERE " + RegisterDb.EMAIL + " = ?" +
                " AND " + RegisterDb.PASSWORD + " = ?";

        String[] args = {login.getEmail(), login.getPassword()};

        Cursor cursor = db.rawQuery(query, args);

        boolean loginValido = cursor.getCount() > 0;

        cursor.close();
        db.close();

        return loginValido;
    }

    public String getUsernameByLogin(String email, String password) {
        db = rdb.getReadableDatabase();
        String query = "SELECT name FROM " + RegisterDb.TABLE +
                " WHERE " + RegisterDb.EMAIL + " = ?" +
                " AND " + RegisterDb.PASSWORD + " = ?";

        String[] args = {email, password};

        Cursor cursor = db.rawQuery(query, args );
        if (cursor.moveToFirst()) {
            return cursor.getString(0);
        }
        return null;
    }
}

