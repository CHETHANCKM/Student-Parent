package com.deepika.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class parent_login2 extends AppCompatActivity {
    Button verify;
    private FirebaseAuth mAuth;
    TextInputLayout  phno;
    TextInputLayout pswd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_login2);
        mAuth = FirebaseAuth.getInstance();
        
        verify = findViewById(R.id.verify);
        phno = findViewById(R.id.phno);
        pswd = findViewById(R.id.phno_pswd);





        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = phno.getEditText().getText().toString();
                String password = pswd.getEditText().getText().toString();

                if (number.isEmpty() || number.length() < 3) {
                    phno.setError("Valid register is required");
                    phno.requestFocus();
                    return;
                }

                if(password.isEmpty())
                {
                    phno.setError("Password required");
                    phno.requestFocus();
                }

                String phoneNumber = number +"@student.com";

                mAuth.signInWithEmailAndPassword(phoneNumber, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {


                            Intent intent = new Intent(parent_login2.this, homepage.class);
                            startActivity(intent);

                        } else {
                            // If sign in fails, display a message to the user.
                            mAuth.signOut();
                            Toast.makeText(parent_login2.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });



            }


        });

    }


    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            Intent intent = new Intent(this, homepage.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

            startActivity(intent);
        }
    }

}