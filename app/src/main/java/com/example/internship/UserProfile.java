package com.example.internship;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.textfield.TextInputLayout;


public class UserProfile extends AppCompatActivity {

    TextInputLayout FName;
    TextInputLayout LName;
    TextInputLayout mail;
    TextInputLayout Pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);


        FName=(TextInputLayout)findViewById(R.id.FN);
        LName=(TextInputLayout)findViewById(R.id.LN);
        mail=(TextInputLayout)findViewById(R.id.Gmail);
        Pass=(TextInputLayout)findViewById(R.id.Pwd);

         showAllUserData();
    }
    public void showAllUserData(){
        Intent intent=getIntent();
        String FName_username=intent.getStringExtra("fname");
        String LName_name=intent.getStringExtra("name");
        String Email_username=intent.getStringExtra("maill");
        String Pass_userpass=intent.getStringExtra("pass");

        FName.getEditText().setText(FName_username);
        LName.getEditText().setText(LName_name);
        mail.getEditText().setText(Email_username);
        Pass.getEditText().setText(Pass_userpass);


    }
}