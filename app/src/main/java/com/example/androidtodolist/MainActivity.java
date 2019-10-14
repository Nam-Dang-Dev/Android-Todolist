package com.example.androidtodolist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.NonNull;
import com.example.androidtodolist.Database.AppDatabase;
import com.example.androidtodolist.Database.Task;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements TaskAdapter.OnItemClicked {
    AppDatabase db;
    RecyclerView recyclerViewTask;
    TaskAdapter taskAdapter;
    public static List<Task> Tasks = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database-task").build();

        //show aleart
        Button btn_aleart = findViewById(R.id.btn_aleart);
        btn_aleart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showConfirmDiaglog("Hello", "Haha");
            }
        });

        recyclerViewTask = findViewById(R.id.recyclerView);
        recyclerViewTask.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onResume() {
        super.onResume();
        getTaskAndShow();

    }

    public void clickAddTask(View view) {
        Intent myIntent = new Intent(this, AddTask.class);
        startActivity(myIntent);
    }

    public void getTaskAndShow() {
        new AsyncTask<Void, Void, List<Task>>() {
            @Override
            protected List<Task> doInBackground(Void... voids) {
                Tasks = db.taskDao().getAll();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("array", Tasks.get(0).task_name.toString());
                        taskAdapter = new TaskAdapter(this, Tasks);
                        taskAdapter.setOnClick(MainActivity.this);
                        recyclerViewTask.setAdapter(taskAdapter);


                    }
                });
                return null;
            }
        }.execute();

    }



    @Override
    public void onClickItemDelete(final int position){
       Log.d("2", "2  "+position);
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {
                db.taskDao().delete(Tasks.get(position));
                Tasks.remove(position);

                return null;
            }
        }.execute();
        getTaskAndShow();
        Toast.makeText(getApplicationContext(),"Delete task successfully",Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onClickItemUpdate(int position) {

    }









    //show diaglog
    private void showConfirmDiaglog(String title, String message) {
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setCancelable(true)
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })

                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //set what should happen when negative button is clicked

                    }
                })
                .show();

    }


}
