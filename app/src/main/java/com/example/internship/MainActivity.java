package com.example.internship;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    private EditText Name;
    private EditText Password;
    private TextView Info;
    private Button Login;
    private int counter=5;
    private Button userRegistraion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Name=(EditText)findViewById(R.id.etName);
        Password=(EditText)findViewById(R.id.etPassword);
        Info=(TextView)findViewById(R.id.tvInfo);
        Login=(Button)findViewById(R.id.btnLogin);
        userRegistraion=(Button) findViewById(R.id.etRegister);
        Info.setText("No of attempts remaining: 5");
       Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(Name.getText().toString() , Password.getText().toString());
            }
        });
        userRegistraion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Register.class));
            }
        });

    }
    private void validate(String userName,String userPassword){
     /*   if(userName.equals("anurag") && userPassword.equals("kaanu")){
            Intent intent=new Intent(MainActivity.this,Login.class);
            startActivity(intent);
            //isUser();
        }*/
        if(!userName.isEmpty() || !userPassword.isEmpty() ){
            isUser();
        }
        else{
            counter--;
            Info.setText("No of attempts remaining:"+ String.valueOf(counter));
            if(counter==0){
                Login.setEnabled(false);
            }
        }
    }
    public void isUser(){

        final String userEnterdUsername=Name.getText().toString().trim();
        String userEnterdpass=Password.getText().toString().trim();
        DatabaseReference refrence=FirebaseDatabase.getInstance().getReference("courses");
        Query checkuser=refrence.orderByChild("name").equalTo(userEnterdUsername);
        checkuser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    Name.setError(null);
                    String passwordFromDb = snapshot.child(userEnterdUsername).child("pass").getValue(String.class);
                    if (passwordFromDb.equals(userEnterdpass)) {
                        Password.setError(null);
                        String fNameFromDb = snapshot.child(userEnterdUsername).child("fname").getValue(String.class);
                        String emailFromDb = snapshot.child(userEnterdUsername).child("maill").getValue(String.class);
                        String NameFromDb = snapshot.child(userEnterdUsername).child("name").getValue(String.class);
                        String PassFromDb = snapshot.child(userEnterdUsername).child("pass").getValue(String.class);

                      Toast.makeText(MainActivity.this, fNameFromDb+"\n"+NameFromDb+"\n"+emailFromDb+"\n"+PassFromDb, Toast.LENGTH_LONG).show();

                       Intent intent = new Intent(MainActivity.this, Login.class);
                       /* intent.putExtra("fname", fNameFromDb);
                        intent.putExtra("maill", emailFromDb);
                        intent.putExtra("name", NameFromDb);
                        intent.putExtra("pass", PassFromDb);*/

                        startActivity(intent);


                    } else {
                        Password.setError("worng password");
                        Password.requestFocus();
                    }
                }
                else{
                    Name.setError("No such User Exist");
                    Name.requestFocus();
                }

            }



            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainActivity.this, "yo", Toast.LENGTH_SHORT).show();
            }
        });
}
    }


