package com.example.qlysach;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    EditText edtUsername, edtPassword;
    Button btnLogin;
    TextView tvError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        tvError = findViewById(R.id.tvError);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = edtUsername.getText().toString().trim();
                String pass = edtPassword.getText().toString().trim();

                if (user.isEmpty() || pass.isEmpty()) {
                    tvError.setText("Vui lòng nhập đầy đủ thông tin");
                    tvError.setVisibility(View.VISIBLE);
                } else if (user.equals("admin") && pass.equals("123456")) { // Tài khoản hard-code
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish(); // Gọi finish() để không quay lại màn hình này bằng nút Back
                } else {
                    tvError.setText("Sai tên đăng nhập hoặc mật khẩu");
                    tvError.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}