package com.example.practise;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper db;

    EditText name, password, email, id;

    Button insertData1, showRecords, showSpecificRecords, updateARecord, deleteARecord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DatabaseHelper(this);

        insertData1 = findViewById(R.id.insertData);

        showRecords = findViewById(R.id.show_all);

        showSpecificRecords = findViewById(R.id.showSpecificRecord);

        updateARecord = findViewById(R.id.updateARecord);

        deleteARecord = findViewById(R.id.deleteARecord);

        insertData1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = findViewById(R.id.name);
                String name1 = name.getText().toString();
                email = findViewById(R.id.email);
                String email1 = email.getText().toString();
                password = findViewById(R.id.password);
                String password1 = password.getText().toString();
                id = findViewById(R.id.id10);
                String id1 = id.getText().toString();
                int idInt = Integer.parseInt(id1);

                boolean insertedOrNot = db.insertData(idInt,name1,email1,password1);
                if (insertedOrNot) {
                    Toast.makeText(MainActivity.this, "Data inserted successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Sorry, problem occured", Toast.LENGTH_SHORT).show();
                }
            }
        });

        showRecords.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Cursor res = db.showRecords();
               while (res.moveToNext()) {
                   String name1 = res.getString(1);
                   String email1 = res.getString(2);
                   String password1 = res.getString(3);
                   Toast.makeText(MainActivity.this, "the name is " + name1 + " the email is " + email1 + " the password is " + password1, Toast.LENGTH_SHORT).show();
               }
             }
        });

        showSpecificRecords.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id = findViewById(R.id.id10);
                String id1 = id.getText().toString();
                int idInt = Integer.parseInt(id1);
                Cursor res = db.showSpecificRecord(idInt);
                while (res.moveToNext()) {
                    String name = res.getString(1);
                    String email = res.getString(2);
                    String password = res.getString(3);
                    Toast.makeText(MainActivity.this, "The name is " + name + " the email is " + email + " the password is " + password , Toast.LENGTH_SHORT).show();
                }
            }
        });

        updateARecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = findViewById(R.id.name);
                String name1 = name.getText().toString();
                email = findViewById(R.id.email);
                String email1 = email.getText().toString();
                password = findViewById(R.id.password);
                String password1 = password.getText().toString();
                id = findViewById(R.id.id10);
                String id1 = id.getText().toString();
                int idInt = Integer.parseInt(id1);
                boolean updatedOrNot = db.updateRecord(idInt,name1,email1,password1);
                if (updatedOrNot) {
                    Toast.makeText(MainActivity.this, "Record Updated", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Record Not Updated", Toast.LENGTH_SHORT).show();
                }
            }
        });

      deleteARecord.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              id = findViewById(R.id.id10);
              String id1 = id.getText().toString();
              int idInt = Integer.parseInt(id1);
              boolean deletedOrNot = db.deleteARecord(idInt);
              if (deletedOrNot) {
                  Toast.makeText(MainActivity.this, "Record Deleted Successfully", Toast.LENGTH_SHORT).show();
              } else {
                  Toast.makeText(MainActivity.this, "Sorry, record could not be deleted", Toast.LENGTH_SHORT).show();
              }
          }
      });

    }
}