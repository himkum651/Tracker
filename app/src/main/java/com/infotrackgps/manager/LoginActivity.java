package com.infotrackgps.manager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    public static final String MyPREFERENCES = "MyPrefs";
    public static final String Name = "nameKey";
    public static final String Password = "phoneKey";
    EditText name, password;
    Button buttonSignUp, buttonLogIn;

    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        name = findViewById(R.id.et_Name);
        password = findViewById(R.id.et_Password);
        buttonSignUp = findViewById(R.id.buttonSignUp);
        buttonLogIn = findViewById(R.id.buttonLogin);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        buttonSignUp.setOnClickListener(v -> {
            if (name.getText() == null || password.getText() == null) {
                Toast.makeText(this, "Enter values in the fields", Toast.LENGTH_SHORT).show();
            } else {
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString(Name, name.getText().toString());
                editor.putString(Password, password.getText().toString());
                editor.apply();
            }

        });

        buttonLogIn.setOnClickListener(v -> {

            String shname = sharedpreferences.getString(Name, "");
            String shpassword = sharedpreferences.getString(Password, "");
            if ((shname.equals(name.getText().toString())) || (shpassword.equals(password.getText().toString()))) {
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            }

        });


    }
}