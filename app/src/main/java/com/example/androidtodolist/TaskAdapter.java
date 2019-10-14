package com.example.androidtodolist;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.androidtodolist.Database.Task;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {
    public List<Task> Tasks;
    private OnItemClicked onClick;

    public TaskAdapter(Runnable mainActivity, List<Task> tasks) {
        Tasks = tasks;
    }

    public interface OnItemClicked {
        void onClickItemUpdate(int position);

        void onClickItemDelete(int position);
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task, parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, final int position) {
        String infor = "";
        infor += Tasks.get(position).task_name + "    " + Tasks.get(position).detail;
        holder.tvView.setText(infor);

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("1", "1  " +position);
                onClick.onClickItemDelete(position);

            }
        });

//        holder.btnUpdate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                onclick.onClickItemUpdate(2);
//            }
//        });


    }

    @Override
    public int getItemCount() {
        return Tasks.size();
    }

    class TaskViewHolder extends RecyclerView.ViewHolder {
        TextView tvView;
        Button btnUpdate, btnDelete;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            tvView = itemView.findViewById(R.id.item_taskName);
            btnDelete = itemView.findViewById(R.id.deleteTask);
            btnUpdate = itemView.findViewById(R.id.updateTask);
        }
    }

    public void setOnClick(OnItemClicked onClick) {
        this.onClick = onClick;
    }
}
