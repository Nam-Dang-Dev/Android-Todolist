package com.example.androidtodolist.Database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Task {

        @PrimaryKey (autoGenerate = true)
        public int Tid;

        @ColumnInfo(name = "task_name")
        public String task_name;

        @ColumnInfo(name = "detail")
        public String detail;

        public Task( String task_name, String detail) {
                this.task_name = task_name;
                this.detail = detail;
        }
}
