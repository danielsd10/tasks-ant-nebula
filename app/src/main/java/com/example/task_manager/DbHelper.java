package com.example.task_manager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DbHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "tasks.db";
    public static final int DB_SCHEME_VERSION = 1;

    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_SCHEME_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TaskContract.schemaCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // En caso de una nueva versión habría que actualizar las tablas
        db.execSQL(TaskContract.schemaDrop);
    }

    /* Operaciones CRUD */

    public void insertTask(Task task) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TaskContract.Column.NAME, task.getName());
        values.put(TaskContract.Column.CREATED_AT, task.getCreatedAt().getTime());

        db.insert(TaskContract.TABLE_NAME, null, values);
        db.close();
    }

    public List<Task> selectTasks() {
        List<Task> list = new ArrayList<Task>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TaskContract.TABLE_NAME, null);

        if(cursor.moveToFirst()) {
            do {
                Task task = new Task(cursor.getInt(0));
                task.setName(cursor.getString(1));
                task.setCreatedAt(new Date(cursor.getLong(2)));
                list.add(task);
            } while (cursor.moveToNext());
        }

        return list;
    }

    public Task selectTask(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TaskContract.TABLE_NAME, new String[]{TaskContract.Column.ID,
                        TaskContract.Column.NAME, TaskContract.Column.CREATED_AT}, TaskContract.Column.ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }

        Task task = new Task(cursor.getInt(0));
        task.setName(cursor.getString(1));
        task.setCreatedAt(new Date(cursor.getLong(2)));

        return task;
    }

    public int updateTask(Task task) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TaskContract.Column.NAME, task.getName());

        return db.update(TaskContract.TABLE_NAME, values, TaskContract.Column.ID + " = ?",
                new String[] { String.valueOf(task.getId()) });
    }

    public void deleteTask(Task task) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TaskContract.TABLE_NAME, TaskContract.Column.ID + " = ?",
                new String[] { String.valueOf(task.getId()) });
        db.close();
    }

}
