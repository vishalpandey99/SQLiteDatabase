package com.example.sqlitedatabase;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText et1,et2,et3;
    Button b1,b2,b3,b4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        database g = new database(this);
        //SQLiteDatabase db = g.getReadableDatabase();

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name1 = et1.getText().toString();
                String username1 = et2.getText().toString();
                String password1 = et3.getText().toString();

                if (name1.isEmpty() || username1.isEmpty() || password1.isEmpty()){
                    Toast.makeText(MainActivity.this, "Enter All Details", Toast.LENGTH_SHORT).show();
                }
                else{

                   Boolean i = g.insert_data(name1,username1,password1);
                   if (i==true){

                       Toast.makeText(MainActivity.this, "Data Insert Successful", Toast.LENGTH_SHORT).show();
                   }
                   else {
                       Toast.makeText(MainActivity.this, "Not Successful", Toast.LENGTH_SHORT).show();
                   }
                   et1.setText("");
                   et2.setText("");
                   et3.setText("");
                }
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Cursor t = g.getinfo();
                if (t.getCount()==0){
                    Toast.makeText(MainActivity.this, "No Data Found", Toast.LENGTH_SHORT).show();
                }

                StringBuffer buffer = new StringBuffer();
                while (t.moveToNext()){

                    buffer.append("name" +t.getString(1)+"\n");
                    buffer.append("username"+t.getString(2)+"\n");
                    buffer.append("password"+t.getString(3)+"\n");
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Sign Up User Data");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = et2.getText().toString();
                Boolean i = g.delete_data(username);
                if (i==true){

                    Toast.makeText(MainActivity.this, "Data Deleted", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Not Deleted", Toast.LENGTH_SHORT).show();
                }
            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = et1.getText().toString();
                String username = et2.getText().toString();
                String password = et3.getText().toString();

                Boolean i = g.update_data(name,username,password);
                if (i==true){

                    Toast.makeText(MainActivity.this, "Data Update", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Not Update", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void init(){

        et1 = findViewById(R.id.et1);
        et2 = findViewById(R.id.et2);
        et3 = findViewById(R.id.et3);
        b1 = findViewById(R.id.b1);
        b2 = findViewById(R.id.b2);
        b3 = findViewById(R.id.b3);
        b4 = findViewById(R.id.b4);
    }
}