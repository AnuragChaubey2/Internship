package com.example.internship;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {
    private EditText FullName;
    private EditText Name;
    private EditText password;
    private EditText email;
    private Button registerbtn;
FirebaseDatabase rootNode;
DatabaseReference refrence;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setUIViews();

        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Validate()){
                    //upload to the database
                 rootNode=FirebaseDatabase.getInstance();
                 refrence=rootNode.getReference("courses");

                    String Fname=FullName.getText().toString();
                    String name=Name.getText().toString();
                    String pass=email.getText().toString();
                    String Gmail=password.getText().toString();
                    UserHelper userHelper=new UserHelper(Fname,name,pass,Gmail);
                    refrence.child(name).setValue(userHelper);
                    Intent intent=new Intent(Register.this,MainActivity.class);    //new
                    startActivity(intent);



                }
            }
        });
    }
    private void setUIViews(){
        FullName=(EditText)findViewById(R.id.fullReg);
        Name=(EditText)findViewById(R.id.Regname);
        email=(EditText)findViewById(R.id.Regmail);
        password=(EditText)findViewById(R.id.Regpsw);
        registerbtn=(Button)findViewById(R.id.RegRegis);
    }
 /*   private boolean validateFName(){
        String val=FullName.getText().toString();
        if(val.isEmpty()){
            FullName.setError("Field cannot be empty");
            return false;
        }
        else{
            FullName.setError(null);
            return true;
        }

    }
    private boolean validateName(){
        String val=Name.getText().toString();
        if(val.isEmpty()){
            Name.setError("Field cannot be empty");
            return false;
        }
        else if(val.length()>10){
            Name.setError("UserName too Long");
        }
        else{
            Name.setError(null);
            return true;
        }
        return false;

    }
    private boolean validateEmail(){
        String val=email.getText().toString();
        if(val.isEmpty()){
            email.setError("Field cannot be empty");
            return false;
        }
        else{
            email.setError(null);
            return true;
        }

    }*/
    private boolean Validate(){
        boolean result=false;
        String Fname=FullName.getText().toString();
        String name=Name.getText().toString();
        String maill=email.getText().toString();
        String pass=password.getText().toString();
        if(Fname.isEmpty() || name.isEmpty() || maill.isEmpty() || pass.isEmpty()){
            Toast.makeText(this, "Please Enter all the details", Toast.LENGTH_SHORT).show();
            return result;
        }

        result=true;
        return result;

    }
}