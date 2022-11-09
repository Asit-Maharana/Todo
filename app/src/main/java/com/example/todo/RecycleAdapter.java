package com.example.todo;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ListViewHolder> {
    Context context;
    ArrayList<ModelClass> list;
    public RecycleAdapter(ArrayList<ModelClass> data, Application application){
        this.list=data;
        this.context=application;
    }
    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view =inflater.inflate(R.layout.list_item_layout,parent,false);
        return new ListViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        holder.id.setText(String.valueOf(list.get(position).getId()));
        holder.name.setText(list.get(position).getName());
        holder.desc.setText(list.get(position).getDescription());
    }
    @Override
    public int getItemCount() {
        return list.size();
    }
    public class ListViewHolder  extends RecyclerView.ViewHolder {
        TextView id;
        TextView desc;
        TextView name;
        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            id = (TextView) itemView.findViewById(R.id.UserId);
            name=(TextView)itemView.findViewById(R.id.UserName);
            desc = (TextView) itemView.findViewById(R.id.UserDesc);
        }
    }
}
