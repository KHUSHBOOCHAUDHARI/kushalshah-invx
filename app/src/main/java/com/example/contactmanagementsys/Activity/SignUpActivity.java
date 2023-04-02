package com.example.contactmanagementsys.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import com.example.contactmanagementsys.Database.MyDataBase;
import com.example.contactmanagementsys.R;
import com.example.contactmanagementsys.Dao.UserDao;
import com.example.contactmanagementsys.Model.UserTable;
import com.example.contactmanagementsys.databinding.ActivitySignUpBinding;

public class SignUpActivity extends AppCompatActivity {
    private ActivitySignUpBinding binding;
    String FirstName, LastName, UserId, Password;
    MyDataBase myDb;
    UserDao userDao;
    public static boolean isAllowed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(SignUpActivity.this, R.layout.activity_sign_up);
        myDb = Room.databaseBuilder(SignUpActivity.this, MyDataBase.class, "usertable")
                .allowMainThreadQueries().fallbackToDestructiveMigration().build();
        userDao = myDb.getDao();

        binding.userNameEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String userName = s.toString();
                if (userDao.is_taken(userName)) {
                    isAllowed = false;
                    Toast.makeText(SignUpActivity.this, "Already Taken", Toast.LENGTH_SHORT).show();
                } else {
                    isAllowed = true;
                }
            }
        });

        binding.signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Validation();
            }
        });
        binding.createAccountTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(i);
                overridePendingTransition(R.anim.slide_from, R.anim.slide_to_left);
            }
        });
    }

    public void Validation() {
        FirstName = binding.firstNameEdit.getText().toString().trim();
        LastName = binding.lastNameEdit.getText().toString().trim();
        UserId = binding.userNameEdit.getText().toString().trim();
        Password = binding.passwordEdit.getText().toString().trim();
        binding.firstNameEdit.clearFocus();
        binding.lastNameEdit.clearFocus();
        binding.userNameEdit.clearFocus();
        binding.passwordEdit.clearFocus();
        binding.firstNameEdit.setError(null);
        binding.lastNameEdit.setError(null);
        binding.userNameEdit.setError(null);
        binding.passwordEdit.setError(null);
        if (FirstName.equals("") || FirstName.isEmpty()) {
            binding.firstNameEdit.requestFocus();
            Toast.makeText(SignUpActivity.this, R.string.enter_firstname_txt, Toast.LENGTH_SHORT).show();

        } else if (LastName.equals("") || LastName.isEmpty()) {
            binding.lastNameEdit.requestFocus();
            Toast.makeText(SignUpActivity.this, R.string.enter_lastname_txt, Toast.LENGTH_SHORT).show();

        } else if (UserId.equals("") || UserId.isEmpty()) {
            binding.userNameEdit.requestFocus();
            Toast.makeText(SignUpActivity.this, R.string.enter_emailid_txt, Toast.LENGTH_SHORT).show();

        } else if (Password.equals("") || Password.isEmpty()) {
            binding.passwordEdit.requestFocus();
            Toast.makeText(SignUpActivity.this, R.string.enter_password_txt, Toast.LENGTH_SHORT).show();

        } else {

            if (isAllowed) {
                UserTable userTable = new UserTable(0, binding.firstNameEdit.getText().toString(), binding.lastNameEdit.getText().toString(), binding.userNameEdit.getText().toString(), binding.passwordEdit.getText().toString());
                userDao.insertUser(userTable);


                Intent i = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(i);

                overridePendingTransition(R.anim.slide_from, R.anim.slide_to_left);
                finish();

            } else {
                Toast.makeText(SignUpActivity.this, "Username Already Taken", Toast.LENGTH_SHORT).show();
            }


        }
    }
}