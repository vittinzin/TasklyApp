package com.vitor.taskly.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.vitor.taskly.R;
import com.vitor.taskly.controller.TaskDbController;

public class ThirdActivity extends BaseActivity {

    private ImageView taskPage;
    private TextView editTask, seeTasks, addTask;
    private TaskDbController taskDbController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_third);

        taskPage = findViewById(R.id.taskPage);
        seeTasks = findViewById(R.id.seeTasks);
        addTask = findViewById(R.id.addNewPage);
        editTask = findViewById(R.id.editTaskPage);

        taskDbController = new TaskDbController(this);

        taskPage.setOnClickListener(v -> {
            Intent intent = new Intent(ThirdActivity.this, FourthActivity.class);
            startActivity(intent);
        });

        seeTasks.setOnClickListener(v -> {
            Intent intent = new Intent(ThirdActivity.this, FourthActivity.class);
            startActivity(intent);
        });

        if (!taskDbController.getAllTasks().isEmpty()) {
            editTask.setOnClickListener(v -> {
                Intent intent = new Intent(ThirdActivity.this, SixthActivity.class);
                startActivity(intent);
            });
        }

        addTask.setOnClickListener(v -> {
            Intent intent = new Intent(ThirdActivity.this, FifthActivity.class);
            startActivity(intent);
        });

    }
}