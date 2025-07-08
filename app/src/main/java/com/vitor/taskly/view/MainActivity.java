package com.vitor.taskly.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import com.vitor.taskly.R;
import com.vitor.taskly.controller.RegisterController;
import com.vitor.taskly.controller.RegisterDbController;
import com.vitor.taskly.model.Login;

public class MainActivity extends AppCompatActivity {


    private TextView signUp;
    private EditText etEmail, etPassword;
    private Login login;
    private Button loginBtn;
    private RegisterDbController registerDBController;
    private RegisterController registerController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        registerController = new RegisterController(this);
        String savedName = registerController.getSavedName();

        if (savedName != null && !savedName.isEmpty()) {
            Intent intent = new Intent(MainActivity.this, ThirdActivity.class);
            startActivity(intent);
            finish();
            return;
        }
        setContentView(R.layout.activity_main);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        loginBtn = findViewById(R.id.loginBtn);
        etEmail = findViewById(R.id.emailEt);
        etPassword = findViewById(R.id.etPassword);

        loginBtn.setOnClickListener(v -> {
            registerDBController = new RegisterDbController(this);

            login = new Login(
                    etEmail.getText().toString(),
                    etPassword.getText().toString()
            );

            if (login.confirmLogin(login) == -1) {
                if (registerDBController.loginRegister(login)) {
                    String username = registerDBController.getUsernameByLogin(
                            login.getEmail(),
                            login.getPassword()
                    );

                    registerController.salvarLogin(username, login);

                    Intent intent = new Intent(MainActivity.this, ThirdActivity.class);
                    intent.putExtra("username", username);

                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(this, "User not found", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Match not found", Toast.LENGTH_SHORT).show();
            }

        });

        signUp = findViewById(R.id.signupTxt);

        signUp.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            startActivity(intent);
        });
    }
}