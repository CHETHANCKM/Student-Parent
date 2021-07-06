package com.deepika.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class teacher_login extends AppCompatActivity {
    Button t_login;
    TextInputLayout facid, facpswd;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_login);

        t_login = findViewById(R.id.t_login);
        facid = findViewById(R.id.facid);
        facpswd = findViewById(R.id.facpas);
        mAuth = FirebaseAuth.getInstance();


        t_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = facid.getEditText().getText().toString();
                String password = facpswd.getEditText().getText().toString();

                if (number.isEmpty() || number.length() != 3) {
                    facid.setError("Valid ID is required");
                    facid.requestFocus();
                    return;
                }else if(password.isEmpty())
                {
                    facpswd.setError("Password required");
                    facpswd.requestFocus();
                }
                else {
                    String phoneNumber = number + "@teacherait.com";
                    Toast.makeText(teacher_login.this, "Signing in", Toast.LENGTH_SHORT).show();

                    mAuth.signInWithEmailAndPassword(phoneNumber, password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {


                                        Intent intent = new Intent(teacher_login.this, teacher_homepage.class);
                                        startActivity(intent);

                                    } else {
                                        // If sign in fails, display a message to the user.
                                        mAuth.signOut();
                                        Toast.makeText(teacher_login.this, "" + task.getException().toString(),
                                                Toast.LENGTH_SHORT).show();

                                    }
                                }
                            });
                }
            }
        });

    }

}