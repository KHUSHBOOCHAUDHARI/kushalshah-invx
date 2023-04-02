package com.example.contactmanagementsys.Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "datatable")
public class DataTable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String username;
    private String phoneno;

    public DataTable() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }
}
