package com.vitor.taskly.view;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.vitor.taskly.R;
import com.vitor.taskly.controller.TaskDbController;
import com.vitor.taskly.model.Task;

import android.app.AlertDialog;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SixthActivity extends BaseActivity {

    private RecyclerView recyclerView;
    private SearchView searchView;
    private ImageView homePage, taskPage;
    private TaskDbController taskDbController;
    private List<Task> taskList;
    private EditTaskAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sixth);

        recyclerView = findViewById(R.id.recyclerView);
        taskDbController = new TaskDbController(this);
        searchView = findViewById(R.id.search);
        homePage = findViewById(R.id.homePage4);
        taskPage = findViewById(R.id.taskPage4);

        taskList = taskDbController.getAllTasks();
        adapter = new EditTaskAdapter(taskList, new EditTaskAdapter.OnItemClickListener() {
            @Override
            public void onDeleteClick(Task task) {
                new AlertDialog.Builder(SixthActivity.this)
                        .setTitle("Delete task")
                        .setMessage("Are you sure you want to delete this task?")
                        .setPositiveButton("Yes", (dialog, which) -> {
                            taskDbController.deleteTask(task.getTaskName());

                            List<Task> updatedList = taskDbController.getAllTasks();
                            adapter.updateList(updatedList);
                        })
                        .setNegativeButton("No", null)
                        .show();
            }
        }) {
            @Override
            public void onDeleteClick(Task task) {

            }
        };

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.filter(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.filter(newText);
                return true;
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        homePage.setOnClickListener(v -> {
            Intent intent = new Intent(SixthActivity.this, ThirdActivity.class);
            startActivity(intent);
        });

        taskPage.setOnClickListener(v ->{
            Intent intent = new Intent(SixthActivity.this, FourthActivity.class);
            startActivity(intent);
        });
    }
    private void refreshOrders() {
        taskList = taskDbController.getAllTasks();
        adapter.updateList(taskList);

        if (taskList.isEmpty()){
            Intent intent = new Intent(SixthActivity.this, FourthActivity.class);
            startActivity(intent);
        }
    }
}