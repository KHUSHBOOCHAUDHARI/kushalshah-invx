package com.example.contactmanagementsys.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;

import com.example.contactmanagementsys.Model.DataTable;
import com.example.contactmanagementsys.Database.MyDataBase;
import com.example.contactmanagementsys.R;
import com.example.contactmanagementsys.Dao.UserDao;
import com.example.contactmanagementsys.databinding.ActivityEditContactBinding;

public class EditContactActivity extends AppCompatActivity {
    private ActivityEditContactBinding binding;
    String Name;
    MyDataBase myDb;
    UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(EditContactActivity.this, R.layout.activity_edit_contact);
        Name = getIntent().getStringExtra("name");
        binding.nameEdit.setText(Name.toString());
        myDb = Room.databaseBuilder(EditContactActivity.this, MyDataBase.class, "datatable").allowMainThreadQueries().fallbackToDestructiveMigration().build();
        userDao = myDb.getDao();
        binding.updatebtnLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataTable dataTable = new DataTable();
                onBackPressed();
                //dataTable.setId(0);
                dataTable.setUsername(binding.nameEdit.getText().toString());
                userDao.updatePerson(dataTable);
            }
        });


    }
}