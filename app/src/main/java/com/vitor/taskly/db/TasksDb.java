package com.vitor.taskly.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class TasksDb extends SQLiteOpenHelper implements BaseColumns {

    public static final String DB_NAME = "Tasks";
    public static final String TABLE = "taskData";
    public static final String ID = "id";
    public static final String TASK_NAME = "taskName";
    public static final String TASK_DESCRIPTION = "taskDescription";
    public static final int VERSION = 2;

    public TasksDb(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String DB = "CREATE TABLE " + TasksDb.TABLE + " ( "
                + TasksDb.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TasksDb.TASK_NAME + " text, " +
                TasksDb.TASK_DESCRIPTION +" text) ";
        sqLiteDatabase.execSQL(DB);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(sqLiteDatabase);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}

