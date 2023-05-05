package com.example.loginpage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.awt.font.TextAttribute;

public class Login extends AppCompatActivity {
    private FirebaseAuth auth;
    private TextView create;
    private EditText userE;
    private EditText userP;
    private Button lButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth = FirebaseAuth.getInstance();
        create = findViewById(R.id.already);
        userE = findViewById(R.id.loginEmail);
        userP = findViewById(R.id.loginPass);
        lButton = findViewById(R.id.LoginBtn);

        lButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userEmail = userE.getText().toString();
                String userPass = userP.getText().toString();
                if(!userEmail.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()) {
                    if(!userPass.isEmpty()){
                        auth.signInWithEmailAndPassword(userEmail,userPass)
                                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                    @Override
                                    public void onSuccess(AuthResult authResult) {
                                        Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(Login.this, MainActivity.class));
                                        finish();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(Login.this, "Login Failed", Toast.LENGTH_SHORT).show();
                                    }
                                });
                    }else{
                        userP.setError("Password cannot be empty");
                    }
                }else if(userEmail.isEmpty()){
                    userE.setError("Email can't be Empty");
                }else{
                    userE.setError("Please enter valid email");
                }
            }
        });

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RedirectToRegister();
            }
        });
    }

    public void RedirectToRegister(){
        Intent intent = new Intent(this,Register.class);
        startActivity(intent);
    }

}