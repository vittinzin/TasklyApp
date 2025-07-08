package com.vitor.taskly.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import com.vitor.taskly.R;
import com.vitor.taskly.controller.RegisterController;
import com.vitor.taskly.controller.RegisterDbController;
import com.vitor.taskly.model.Register;

public class SecondActivity extends BaseActivity {

    private Register register;
    private RegisterController registerController;
    private RegisterDbController rdb;
    private EditText etName, etPhone, etEmail, etPassword, etConfirm;
    private Button registerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);

        etName = findViewById(R.id.nameEt);
        etPhone = findViewById(R.id.registerphoneEmail);
        etEmail = findViewById(R.id.registerEmailEt);
        etPassword = findViewById(R.id.registerPasswordEt);
        etConfirm = findViewById(R.id.confirmpassEt);

        registerBtn = findViewById(R.id.registerBtn);

        registerBtn.setOnClickListener(v -> {

            registerController = new RegisterController();
            rdb = new RegisterDbController(this);

            if (etPassword.getText().toString().equals(etConfirm.getText().toString())) {
                register = new Register(
                        etName.getText().toString(),
                        etPhone.getText().toString(),
                        etEmail.getText().toString(),
                        etPassword.getText().toString()
                );

                if (registerController.confirmRegister(register) == -1) {
                    rdb.insert(
                            register.getName(),
                            register.getPhone(),
                            register.getEmail(),
                            register.getPassword()
                    );
                    Intent intent = new Intent(SecondActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "Invalid!", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Passwords dont match!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
