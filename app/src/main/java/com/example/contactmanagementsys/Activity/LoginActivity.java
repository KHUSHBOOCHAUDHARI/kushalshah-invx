package com.example.contactmanagementsys.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.contactmanagementsys.Database.MyDataBase;
import com.example.contactmanagementsys.R;
import com.example.contactmanagementsys.Dao.UserDao;
import com.example.contactmanagementsys.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;
    String UserName, Password;
    MyDataBase myDb;
    UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(LoginActivity.this, R.layout.activity_login);
        myDb = Room.databaseBuilder(LoginActivity.this, MyDataBase.class, "usertable")
                .allowMainThreadQueries().fallbackToDestructiveMigration().build();
        userDao = myDb.getDao();
        binding.loginbtnLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Validation();
            }
        });
        binding.createAccountTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(i);
                overridePendingTransition(R.anim.slide_from, R.anim.slide_to_left);
            }
        });
    }

    public void Validation() {
        UserName = binding.userNameEdit.getText().toString().trim();
        Password = binding.passwordEdit.getText().toString().trim();
        binding.userNameEdit.clearFocus();
        binding.passwordEdit.clearFocus();
        binding.userNameEdit.setError(null);
        binding.passwordEdit.setError(null);

        if (UserName.equals("") || UserName.isEmpty()) {
            binding.passwordEdit.requestFocus();
            Toast.makeText(LoginActivity.this, R.string.enter_username_txt, Toast.LENGTH_SHORT).show();

        } else if (Password.equals("") || Password.isEmpty()) {
            binding.passwordEdit.requestFocus();
            Toast.makeText(LoginActivity.this, R.string.enter_username_txt, Toast.LENGTH_SHORT).show();

        } else {

            if (userDao.login(UserName, Password)) {
                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(i);
                overridePendingTransition(R.anim.slide_from, R.anim.slide_to_left);
            } else {
                Toast.makeText(LoginActivity.this, "Invalid User and password", Toast.LENGTH_SHORT).show();
            }
        }
    }
}