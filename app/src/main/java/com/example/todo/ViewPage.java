package com.example.todo;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ViewPage extends AppCompatActivity {
    private static final String TAG = "ViewPage";
    private SqliteConnection sqliteConnection;
    ArrayList<ModelClass> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view);
        sqliteConnection = new SqliteConnection(this);

        RecyclerView list = findViewById(R.id.List);


        list.setLayoutManager(new LinearLayoutManager(this));
        try {
            sqliteConnection.loadHandler();
            data.addAll(sqliteConnection.loadHandler());
            Log.v(TAG, "Record " + data);
        } catch (Exception e) {
            e.printStackTrace();
        }
           RecycleAdapter adapter = new RecycleAdapter(data, getApplication());
         list.setAdapter(adapter);
    }
}
