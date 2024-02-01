package com.dbapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnNext=findViewById(R.id.btnNext);

        SQLiteDatabase myDB = openOrCreateDatabase("FirstDB",MODE_PRIVATE,null);

        myDB.execSQL("CREATE TABLE IF NOT EXISTS LOGIN(Username VARCHAR,Password VARCHAR);");
        myDB.execSQL("INSERT INTO LOGIN VALUES('admin','admin');");

        Cursor resultSet = myDB.rawQuery("Select * from LOGIN",null);
        /*resultSet.moveToFirst();
        String username = resultSet.getString(0);
        String password = resultSet.getString(1);

        Toast.makeText(getApplicationContext(),username+" "+password,Toast.LENGTH_LONG).show();

*/
        if (resultSet.moveToFirst()) {
            while (resultSet.moveToNext()) {
                //Log.e("Name: ", resultSet.getString(resultSet.getColumnIndex("Username")));
                Log.e("Name: ", resultSet.getString(0));
            }
        }
        resultSet.close();
        myDB.close();

        btnNext.setOnClickListener(view -> {
            Intent intent=new Intent(getApplicationContext(),ActiveAndroidActivity.class);
            startActivity(intent);
        });
    }
}