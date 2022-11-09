package com.example.todo;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {
    ListView resultText;
    EditText Id;
    EditText Name;
    EditText Desc;
    ArrayList<ModelClass> item;
    ArrayAdapter myAdapter;
    private SqliteConnection sqliteConnection;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
       // resultText = (ListView) findViewById(R.id.result);
        Id = (EditText) findViewById(R.id.id);
        Name = (EditText) findViewById(R.id.name);
        Desc= (EditText) findViewById(R.id.desc);
        //resultText.setMovementMethod(new ScrollingMovementMethod());
        sqliteConnection= new SqliteConnection(this);

    }

    public void loadData(View view) {
        Intent i = new Intent(MainActivity2.this, ViewPage.class);
        startActivity(i);
      /*  item = sqliteConnection.loadHandler();
        myAdapter=new ArrayAdapter<ModelClass>(this, R.layout.list_item,R.id.userId,item);
        resultText.setAdapter(myAdapter);*/
      /*  resultText.set(sqliteConnection.loadHandler());
        Id.setText("");
        Name.setText("");
        Desc.setText("");*/
        //item.add(sqliteConnection.loadHandler());
       /* Id.setText("");
        Name.setText("");
        Desc.setText("");*/
        // SqliteConnection sqliteConnection = new SqliteConnection(this);
        // ArrayList<ModelClass> list = sqliteConnection.getAllData();
    }
    public void addData (View view) {
        if(!Id.getText().toString().isEmpty() && !Name.getText().toString().isEmpty() && !Desc.getText().toString().isEmpty()) {
            int id = Integer.parseInt(Id.getText().toString());
            String name = Name.getText().toString();
            String desc = Desc.getText().toString();
            ModelClass modelClass = new ModelClass(id,name,desc);
            long insertId=sqliteConnection.addHandler(modelClass);
            if(insertId<1){
                Toast.makeText(getApplicationContext(),
                        "Record already exists",
                        Toast.LENGTH_SHORT).show();
               // resultText.setText("Record already exists");
            }
            else{
                Id.setText("");
                Name.setText("");
                Desc.setText("");
                Toast.makeText(getApplicationContext(),
                        "Record added",
                        Toast.LENGTH_SHORT).show();
               // resultText.setText("Record added");
            }
        }
        else{
            //resultText.setText("Please fill correct id ");
            Toast.makeText(getApplicationContext(),
                    "Please fill correct id",
                    Toast.LENGTH_SHORT).show();
        }
    }
    public void updateData(View view) {
        if( !Id.getText().toString().isEmpty() && !Name.getText().toString().isEmpty() && !Desc.getText().toString().isEmpty()) {
            boolean result = sqliteConnection.updateHandler(Integer.parseInt(Id.getText().toString()), Name.getText().toString(),Desc.getText().toString());
            if (result) {
                Id.setText("");
                Name.setText("");
                Desc.setText("");
               // resultText.setText("Record Updated");
                Toast.makeText(getApplicationContext(),
                        "Record Updated",
                        Toast.LENGTH_SHORT).show();
            } else {
               // resultText.setText("No Record Found");
                Toast.makeText(getApplicationContext(),
                        "No Record Found",
                        Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(getApplicationContext(),
                    "Please fill correct id",
                    Toast.LENGTH_SHORT).show();
           // resultText.setText("Please fill correct id");
        }
    }

    public void deleteData(View view) {
        if(!Id.getText().toString().isEmpty()) {
            boolean result = sqliteConnection.deleteHandler(Integer.parseInt(
                   Id.getText().toString()));
            if (result) {
                Id.setText("");
                Name.setText("");
                Desc.setText("");
              //  resultText.setText("Record Deleted");
                Toast.makeText(getApplicationContext(),
                        "Record Deleted",
                        Toast.LENGTH_SHORT).show();
            } else {
                //resultText.setText("No Record Found");
                Toast.makeText(getApplicationContext(),
                        "No Record Found",
                        Toast.LENGTH_SHORT).show();
            }
        } else{
           // resultText.setText("Please fill correct id");
            Toast.makeText(getApplicationContext(),
                    "Please fill correct id",
                    Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        sqliteConnection.close();
    }

}
