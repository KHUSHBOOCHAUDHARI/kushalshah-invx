package com.example.contactmanagementsys.Adaptor;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.contactmanagementsys.Activity.EditContactActivity;
import com.example.contactmanagementsys.Model.DataTable;
import com.example.contactmanagementsys.R;

import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.Datavieholder> {
    List<DataTable> dataTables;
    Activity activity;

    public DataAdapter(List<DataTable> dataTables, Activity activity) {
        this.dataTables = dataTables;
        this.activity = activity;
    }

    @NonNull
    @Override
    public Datavieholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.data_list, parent, false);
        return new Datavieholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Datavieholder holder, @SuppressLint("RecyclerView") int position) {
        holder.name.setText(dataTables.get(position).getUsername());
        holder.phoneno.setText(dataTables.get(position).getPhoneno());
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, EditContactActivity.class);
                intent.putExtra("id", dataTables.get(position).getId());
                intent.putExtra("name", dataTables.get(position).getUsername());
                activity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataTables.size();
    }

    class Datavieholder extends RecyclerView.ViewHolder {
        TextView name, phoneno, edit;

        public Datavieholder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            phoneno = itemView.findViewById(R.id.phoneno);
            edit = itemView.findViewById(R.id.edit);
        }
    }
}
