package com.vitor.taskly.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.vitor.taskly.db.TasksDb;
import com.vitor.taskly.model.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskDbController {

    private SQLiteDatabase db;
    private final TasksDb tdb;

    public TaskDbController(Context context) {
        tdb = new TasksDb(context);
    }

    public void insert(String name, String desc) {
        db = tdb.getWritableDatabase();

        ContentValues data = new ContentValues();
        data.put(TasksDb.TASK_NAME, name);
        data.put(TasksDb.TASK_DESCRIPTION, desc);

        db.insert(TasksDb.TABLE, null, data);

    }

    public List<Task> getAllTasks() {
        List<Task> taskList = new ArrayList<>();
        db = tdb.getReadableDatabase();
        Cursor cursor = db.query(TasksDb.TABLE, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(cursor.getColumnIndexOrThrow(TasksDb.TASK_NAME));
                String desc = cursor.getString(cursor.getColumnIndexOrThrow(TasksDb.TASK_DESCRIPTION));

                taskList.add(new Task(name, desc));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return taskList;
    }

    public void deleteTask(String id) {
        SQLiteDatabase db = tdb.getWritableDatabase();
        db.delete("taskData", "taskName = ?", new String[]{String.valueOf(id)});
        db.close();
    }
}