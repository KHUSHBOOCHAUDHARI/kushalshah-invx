package com.example.contactmanagementsys.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.contactmanagementsys.Model.DataTable;
import com.example.contactmanagementsys.Model.UserTable;

import java.util.List;

@Dao
public interface UserDao {
    @Insert
    void insertUser(UserTable userTable);

    @Insert
    void insertData(DataTable dataTable);

    @Query("SELECT EXISTS (SELECT * FROM UserTable where username=:userName)")
    boolean is_taken(String userName);

    @Query("SELECT EXISTS(SELECT * from UserTable where username=:userName AND password=:password)")
    boolean login(String userName, String password);

    @Query("SELECT * FROM datatable")
    List<DataTable> getAllData();

    @Update
    public void updatePerson(DataTable dataTable);

}
