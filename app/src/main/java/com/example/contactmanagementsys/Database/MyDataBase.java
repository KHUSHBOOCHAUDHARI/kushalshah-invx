package com.example.contactmanagementsys.Database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.contactmanagementsys.Model.DataTable;
import com.example.contactmanagementsys.Model.UserTable;
import com.example.contactmanagementsys.Dao.UserDao;

@Database(entities = {UserTable.class, DataTable.class}, version = 4)
public abstract class MyDataBase extends RoomDatabase {
    public abstract UserDao getDao();
}
