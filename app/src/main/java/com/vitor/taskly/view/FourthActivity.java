package com.vitor.taskly.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vitor.taskly.R;
import com.vitor.taskly.controller.TaskDbController;
import com.vitor.taskly.model.Task;

import java.util.List;

public class FourthActivity extends AppCompatActivity {

    private ImageView homePage;
    private RecyclerView recyclerList;
    private SearchView searchView;
    private TaskDbController taskDbController;
    private List<Task> taskList;
    private Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_fourth);

        homePage = findViewById(R.id.homePage2);
        recyclerList = findViewById(R.id.recyclerView);
        searchView = findViewById(R.id.searchView);
        addButton = findViewById(R.id.addNewBtn);
        taskDbController = new TaskDbController(this);

        taskList = taskDbController.getAllTasks();
        TaskAdapter adapter = new TaskAdapter(taskList);

        recyclerList.setLayoutManager(new LinearLayoutManager(this));
        recyclerList.setAdapter(adapter);

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

       // if (!orderList.isEmpty()) {
         //   editBtn.setOnClickListener(v -> {
          //      Intent intent = new Intent(FourthActivity.this, SixthActivity.class);
          //      startActivity(intent);
          //  });
       //}

        homePage.setOnClickListener(v -> {
            Intent intent = new Intent(FourthActivity.this, ThirdActivity.class);
            startActivity(intent);
        });

        addButton.setOnClickListener(v -> {
            Intent intent = new Intent(FourthActivity.this, FifthActivity.class);
            startActivity(intent);
        });
    }

}