package com.vitor.taskly.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.vitor.taskly.R;
import com.vitor.taskly.model.Task;
import java.util.ArrayList;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.OrderViewHolder> {

    private List<Task> tasks;
    private final List<Task> orderListFull;

    public TaskAdapter(List<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
        this.orderListFull = new ArrayList<>(tasks);
    }

    public void filter(String text) {
        List<Task> filteredList = new ArrayList<>();
        if (text.isEmpty()) {
            filteredList.addAll(orderListFull);
        } else {
            text = text.toLowerCase();
            for (Task task : orderListFull) {
                if (task.getTaskName().toLowerCase().contains(text)) {
                    filteredList.add(task);
                }
            }
        }
        tasks = filteredList;
        notifyDataSetChanged();
    }

    public static class OrderViewHolder extends RecyclerView.ViewHolder {
        TextView taskName, description;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            taskName = itemView.findViewById(R.id.taskName);
            description = itemView.findViewById(R.id.taskDesc);
        }
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.task_item, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        Task task = tasks.get(position);
        holder.taskName.setText(task.getTaskName());
        holder.description.setText(task.getDescription());

    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

}