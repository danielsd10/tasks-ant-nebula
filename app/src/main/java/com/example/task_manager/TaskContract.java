package com.example.task_manager;

import android.provider.BaseColumns;

/* Constantes para los elementos de tareas en la base de datos */
public class TaskContract {

    public static final String TABLE_NAME = "tasks";

    public static final String DEFAULT_SORT = Column.CREATED_AT + " DESC";

    public class Column {
        public static final String ID = BaseColumns._ID;
        public static final String NAME = "name";
        public static final String CREATED_AT = "created_at";
    }

    public static final String schemaCreate = String.format("CREATE TABLE %s(" +
            "%s INTEGER PRIMARY KEY AUTOINCREMENT," +
            "%s TEXT," +
            "%s LONG NOT NULL)", TABLE_NAME, Column.ID, Column.NAME, Column.CREATED_AT);

    public static final String schemaDrop = String.format("DROP TABLE IF EXISTS %s", TABLE_NAME);
}
