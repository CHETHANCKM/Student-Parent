package com.deepika.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class add_new_student extends AppCompatActivity {

    TextInputLayout studentname, parentname, id, password;
    Button add;
    FirebaseAuth mAuth;
    DatabaseReference mDatabase;
// ...

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_student);
        studentname = findViewById(R.id.student_name);
        id = findViewById(R.id.student_id);
        password = findViewById(R.id.password_new);
        add = findViewById(R.id.add_s);
        parentname = findViewById(R.id.parent_name);
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = studentname.getEditText().getText().toString();
                String studentid = id.getEditText().getText().toString();
                String parents = parentname.getEditText().getText().toString();
                String pass = password.getEditText().getText().toString();

                String email = studentid+"@studentait.com";


                if (name.isEmpty() || studentid.length()!=3 || parents.isEmpty())
                {

                }
                else if (pass.length()<6)
                {
                    Toast.makeText(add_new_student.this, "Password must be >6 digits", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    try
                    {
                        int d = Integer.parseInt("d");

                        Toast.makeText(getApplicationContext(), d, Toast.LENGTH_SHORT).show();
                    }
                    catch (Exception e)
                    {

                    }

                    mAuth.createUserWithEmailAndPassword(email, pass)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {

                                        FirebaseUser user = mAuth.getCurrentUser();

                                        HashMap<Object, String> hashMap = new HashMap<>();
                                        hashMap.put("name", name);
                                        hashMap.put("student_id", studentid);
                                        hashMap.put("parent", parents);
                                        hashMap.put("uid", user.getUid());

                                        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Students");
                                        reference.child(user.getUid()).setValue(hashMap)
                                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void aVoid) {
                                                        Toast.makeText(add_new_student.this, "Student Created", Toast.LENGTH_LONG).show();
                                                    }
                                                }).addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Toast.makeText(add_new_student.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                                            }
                                        });



                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Toast.makeText(add_new_student.this, ""+task.getException(),
                                                Toast.LENGTH_SHORT).show();

                                    }
                                }
                            });
                }
            }

            private void updateUI(FirebaseUser user) {
            }
        });


    }
}