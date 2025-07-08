package com.vitor.taskly.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.vitor.taskly.R;
import com.vitor.taskly.controller.TaskDbController;
import com.vitor.taskly.model.Task;

public class FifthActivity extends AppCompatActivity {

    private ImageView homePage, taskPage;
    private Button addBtn;
    private TextView taskName, taskDesc;
    private Task task;
    private TaskDbController taskDbController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_fifth);

        homePage = findViewById(R.id.homePage3);
        taskPage = findViewById(R.id.taskPage3);
        addBtn = findViewById(R.id.createTask);
        taskName = findViewById(R.id.taskNameEt);
        taskDesc = findViewById(R.id.createDescription);

        taskDbController = new TaskDbController(this);

        addBtn.setOnClickListener(v -> {
            task = new Task(
                    taskName.getText().toString(),
                    taskDesc.getText().toString()
            );

            taskDbController.insert(task.getTaskName(), task.getDescription());

                Intent intent = new Intent(FifthActivity.this, FourthActivity.class);
                startActivity(intent);
        });

    }
}