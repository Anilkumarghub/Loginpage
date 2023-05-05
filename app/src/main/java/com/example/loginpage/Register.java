package com.example.loginpage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Button;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {
    private FirebaseAuth auth;
    private EditText userName;
    private EditText userEmail;
    private EditText userPass;
    private EditText userPhone;
    private TextView button;
    private TextView register;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        auth = FirebaseAuth.getInstance();
        userName = findViewById(R.id.fullname);
        userEmail = findViewById(R.id.email);
        userPass = findViewById(R.id.password);
        userPhone = findViewById(R.id.phone);
        button = findViewById(R.id.createtext);
        register = findViewById(R.id.registerBtn);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = userName.getText().toString().trim();
                String uEmail = userEmail.getText().toString().trim();
                String uPass = userPass.getText().toString();
                String uPhone = userPhone.getText().toString();

                if (user.isEmpty()) {
                    userName.setError("userName can't be empty");
                }
                if (!uEmail.contains("@")){
                    userEmail.setError("Enter valid email");
                }
                if(!uPass.contains("@") && !uPass.contains("#") && !uPass.contains("$") && !uPass.contains("%") && !uPass.contains("&") && !uPass.contains("*")){
                    userPass.setError("Password should contain special charecter");
                }
                if(!uPass.contains("1") && !uPass.contains("2") && !uPass.contains("3") && !uPass.contains("4") && !uPass.contains("5") && !uPass.contains("6")){
                    userPass.setError("Password should contain atleast one Number");
                }
                if(!uPass.contains("A") && !uPass.contains("B") && !uPass.contains("C") && !uPass.contains("D") && !uPass.contains("E") && !uPass.contains("F")){
                    userPass.setError("Password should contain atleast one capital letter");
                }
                if(!uPass.contains("a") && !uPass.contains("b") && !uPass.contains("c") && !uPass.contains("d") && !uPass.contains("e") && !uPass.contains("f")){
                    userPass.setError("Password should contain atleast one small letter");
                }
                if(uPass.length() < 8){
                    userPass.setError("password length should be greater than 8");
                }
                else {
                    auth.createUserWithEmailAndPassword(uEmail, uPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(Register.this, "Registration successful", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(Register.this, Login.class));
                            }else{
                                Toast.makeText(Register.this, "Registration Failed" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               openActivity2();
            }
        });
    }

    public void openActivity2(){
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }

}