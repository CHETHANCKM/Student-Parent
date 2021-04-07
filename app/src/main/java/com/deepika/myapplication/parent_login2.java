package com.deepika.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class parent_login2 extends AppCompatActivity {
    Button verify;
    private FirebaseAuth mAuth;
    EditText phno, pswd;

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
                String number = phno.getText().toString().trim();

                if (number.isEmpty() || number.length() < 10) {
                    phno.setError("Valid number is required");
                    phno.requestFocus();
                    return;
                }

                String phoneNumber = "+91" + number;

                Intent intent = new Intent(parent_login2.this, VerifyPhoneActivity.class);
                intent.putExtra("phoneNumber", phoneNumber);
                startActivity(intent);
            }


        });

    }


    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            Intent intent = new Intent(this, parent_login2.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

            startActivity(intent);
        }
    }

}