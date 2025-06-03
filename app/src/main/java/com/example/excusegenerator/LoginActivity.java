package com.example.excusegenerator;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameInput, passwordInput;
    private Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameInput = findViewById(R.id.login_username);
        passwordInput = findViewById(R.id.login_password);
        loginBtn = findViewById(R.id.login_button);

        loginBtn.setOnClickListener(v -> {
            String username = usernameInput.getText().toString();
            String password = passwordInput.getText().toString();

            SharedPreferences prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE);
            String savedUsername = prefs.getString("username", null);
            String savedPassword = prefs.getString("password", null);

            if (username.equals(savedUsername) && password.equals(savedPassword)) {
                Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, MainActivity.class));
                finish();
            } else {
                Toast.makeText(this, "Invalid credentials", Toast.LENGTH_SHORT).show();
            }
        });

        TextView forgotPasswordText = findViewById(R.id.forgot_password);
        forgotPasswordText.setOnClickListener(v -> {
            Toast.makeText(LoginActivity.this, "Feature coming soon!", Toast.LENGTH_SHORT).show();
            // Or launch a ForgotPasswordActivity if implemented
        });
    }

}
