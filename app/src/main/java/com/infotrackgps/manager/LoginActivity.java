package com.infotrackgps.manager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    public static final String MyPREFERENCES = "MyPrefs";
    public static final String Name = "nameKey";
    public static final String Password = "phoneKey";
    public static final String isPasswordSaved="isPasswordSaved";
    EditText name, password;
    Button buttonSignUp, buttonLogIn;
    CheckBox saveCheckbox;
    Boolean isChecked=false;

    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        name = findViewById(R.id.et_Name);
        password = findViewById(R.id.et_Password);
        buttonSignUp = findViewById(R.id.buttonSignUp);
        buttonLogIn = findViewById(R.id.buttonLogin);
        saveCheckbox=findViewById(R.id.saveCheckBox);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        String shIsChecked=sharedpreferences.getString(isPasswordSaved,"5");
        if(shIsChecked.equals("1")){
            String shname = sharedpreferences.getString(Name, "");
            String shpassword = sharedpreferences.getString(Password, "");
            name.setText(shname);
            password.setText(shpassword);

        }



        saveCheckbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(saveCheckbox.isChecked()){
                    isChecked=true;
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString(isPasswordSaved,"1");
                    editor.apply();
                }else{
                    isChecked=false;
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString(isPasswordSaved,"5");
                    editor.apply();
                }
            }
        });

        buttonSignUp.setOnClickListener(v -> {
            if (name.getText() == null || password.getText() == null) {
                Toast.makeText(this, "Enter values in the fields", Toast.LENGTH_SHORT).show();
            } else {
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString(Name, name.getText().toString());
                editor.putString(Password, password.getText().toString());



                editor.apply();
                name.setText("");
                password.setText("");
                Toast.makeText(this, "Sign Up Successful Please Login", Toast.LENGTH_SHORT).show();
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