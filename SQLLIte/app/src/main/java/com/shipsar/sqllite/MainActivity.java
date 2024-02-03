package com.shipsar.sqllite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button signIn_button = findViewById(R.id.SIGNIN);
        Button signUp_button = findViewById(R.id.SIGNUP);
        EditText et_email = findViewById(R.id.emailID);
        EditText et_name = findViewById(R.id.fullName);
        EditText et_password = findViewById(R.id.password);
        final SQLiteDatabase myDB = openOrCreateDatabase("myDB", MODE_PRIVATE, null);;

//    try {
//                myDB.execSQL("CREATE TABLE IF NOT EXISTS USERS4 (ID INTEGER PRIMARY KEY AUTOINCREMENT,USERNAME VARCHAR(255) NOT NULL UNIQUE, PASSOWRD VARCHAR(255) NOT NULL, NAME VARCHAR(255))");
//
//            myDB.execSQL("ALTER TABLE USERS3 ADD COLUMN NAME VARCHAR(255)");
//
//            myDB.execSQL("INSERT INTO USERS3 VALUES (NULL, 'AJAY', '7043')");
//
//            Cursor resultSet = myDB.rawQuery("SELECT * FROM USERS3", null);
//
//            while(resultSet.moveToNext()){
//                    Log.d("SQL DATA","ID "+resultSet.getString(0));
//                    Log.d("SQL DATA","USER "+resultSet.getString(1));
//                    Log.d("SQL DATA","PASS "+resultSet.getString(2));
//                    Log.d("SQL DATA","NAME "+resultSet.getString(3));
//                }
//
//            resultSet.close();
//    } catch (Throwable e){
//        Log.e("SQL ERROR", Objects.requireNonNull(e.getMessage()));
//        Log.e("SQL ERROR", "ERROR IN SQL");
//    }

        signIn_button.setOnClickListener(v->{
            String email = et_email.getText().toString().trim();
            String password = et_password.getText().toString().trim();

            try {
                String query = "SELECT * FROM USERS4 where USERNAME='" + email + "' AND PASSOWRD='" + password + "'";
                Cursor resultSet = myDB.rawQuery(query, null);
//
                while (resultSet.moveToNext()) {
                    String cmd = "ID " + resultSet.getString(0) + " , USER " + resultSet.getString(1) + " , PASS " + resultSet.getString(2);
                    Log.d("SQL DATA", cmd);
                }

//                Log.d("SQL CURSOR", resultSet.getString(0));

                resultSet.close();

                Intent i = new Intent(this, Dashboard.class);
                startActivity(i);

            } catch (Throwable e){

                if(Objects.requireNonNull(e.getMessage()).startsWith("Index -1 requested, with a size of 0")){
                    Toast.makeText(this, "ERROR: NO USER FOUND!", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(this, "ERROR: UNKNOWN ERROR!", Toast.LENGTH_SHORT).show();
                }

                Log.e("SQL ERROR", Objects.requireNonNull(e.getMessage()));
            }
        });

        signUp_button.setOnClickListener(v->{
                String name = et_name.getText().toString().trim();
                String email = et_email.getText().toString().trim();
                String password = et_password.getText().toString().trim();

                try {
                    myDB.execSQL("INSERT INTO USERS4 VALUES (NULL,'"+ email + "','" + password + "','" + name+"')");

                    Toast.makeText(this, "ACCOUNT CREATED SUCCESSFULLY!", Toast.LENGTH_SHORT).show();

                    Intent i = new Intent(this, Dashboard.class);
                    startActivity(i);

                } catch (Throwable e){

                    Toast.makeText(this, "ERROR: UNKNOWN ERROR!", Toast.LENGTH_SHORT).show();

                    Log.e("SQL ERROR", Objects.requireNonNull(e.getMessage()));
                }
        });


    }
}