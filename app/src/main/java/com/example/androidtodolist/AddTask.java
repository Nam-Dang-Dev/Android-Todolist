package com.example.androidtodolist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidtodolist.Database.AppDatabase;
import com.example.androidtodolist.Database.Task;

public class AddTask extends AppCompatActivity {
    AppDatabase db;
    TaskAdapter taskAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database-task").build();
    }

    public void addNewTask(View view) {
        TextView viewName = findViewById(R.id.newTask);
        TextView viewDetail = findViewById(R.id.detail);
        final String taskName = viewName.getText().toString();
        final String taskDetail = viewDetail.getText().toString();

        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {
                Task user = new Task(taskName, taskDetail);
                db.taskDao().insertAll(user);
                return null;
            }
        }.execute();

        Toast.makeText(getApplicationContext(),"Add task successfully",Toast.LENGTH_SHORT).show();
    }
}
