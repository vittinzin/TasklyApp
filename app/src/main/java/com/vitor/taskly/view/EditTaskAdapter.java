package com.vitor.taskly.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vitor.taskly.R;
import com.vitor.taskly.model.Task;

import java.util.ArrayList;
import java.util.List;

public abstract class EditTaskAdapter extends RecyclerView.Adapter<EditTaskAdapter.TaskViewHolder> {

    public abstract void onDeleteClick(Task task);

    public interface OnItemClickListener {
        void onDeleteClick(Task task);
    }

    private List<Task> taskList;
    private final List<Task> taskListFull;
    private final OnItemClickListener listener;

    public EditTaskAdapter(List<Task> tasks, OnItemClickListener listener) {
        this.taskList = new ArrayList<>(tasks);
        this.taskListFull = new ArrayList<>(tasks);
        this.listener = listener;
    }

    public void filter(String text) {
        List<Task> filteredList = new ArrayList<>();
        if (text.isEmpty()) {
            filteredList.addAll(taskListFull);
        } else {
            text = text.toLowerCase();
            for (Task order : taskListFull) {
                if (order.getTaskName().toLowerCase().contains(text)) {
                    filteredList.add(order);
                }
            }
        }
        taskList = filteredList;
        notifyDataSetChanged();
    }
    public static class TaskViewHolder extends RecyclerView.ViewHolder {
        TextView taskName, taskDescription;
        Button btnDelete;
        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            taskName = itemView.findViewById(R.id.taskName);
            taskDescription = itemView.findViewById(R.id.taskDescription);

            btnDelete = itemView.findViewById(R.id.deleteButton);
        }
    }

    public EditTaskAdapter.TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.edit_item, parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EditTaskAdapter.TaskViewHolder holder, int position) {
        Task task = taskList.get(position);
        holder.taskName.setText(task.getTaskName());
        holder.taskDescription.setText(task.getDescription());

        holder.btnDelete.setOnClickListener(v -> listener.onDeleteClick(task));

    }

    public void updateList(List<Task> newList) {
        taskList.clear();
        taskList.addAll(newList);

        taskListFull.clear();
        taskListFull.addAll(newList);

        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }
}