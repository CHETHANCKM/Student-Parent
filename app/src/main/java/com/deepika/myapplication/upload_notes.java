package com.deepika.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.HashMap;

public class upload_notes extends AppCompatActivity {

    TextInputEditText up_tilte, up_desccrip;
    AutoCompleteTextView customerAutoTV;
    Button choose_file;
    StorageReference mStorageRef;
    Uri file_uri=null;
    Button add_file;

    private  static final int CAMERA_REQUEST_CODE= 100;
    private static  final  int IMAGE_PICK_CAMERA_CODE= 300;
    private static final int FILE_SELECT_CODE = 0;

    String [] campermission;
    String [] storagepermission;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_notes);
        initUI();

        up_tilte = findViewById(R.id.up_tilte);
        up_desccrip = findViewById(R.id.up_desccrip);
        customerAutoTV  = findViewById(R.id.customerTextView);
        choose_file = findViewById(R.id.choose_file);
        add_file = findViewById(R.id.add_file);

        campermission = new String[] {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        storagepermission = new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE};


        add_file.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                String tilte = up_tilte.getText().toString();
                String desc = up_desccrip.getText().toString();
                String subject = customerAutoTV.getText().toString();

                final String timestamp = String.valueOf(System.currentTimeMillis());
                String  filepathandname = "Notes/"+subject+"/"+timestamp;

                if (tilte.isEmpty() || desc.isEmpty() || subject.isEmpty() ||file_uri ==null)
                {
                    Toast.makeText(upload_notes.this, "All fields are required.", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    mStorageRef = FirebaseStorage.getInstance().getReference().child(filepathandname);
                    mStorageRef.putFile(file_uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();

                            while (!uriTask.isSuccessful());
                            String downloadurl = uriTask.getResult().toString();


                            if (uriTask.isSuccessful())
                            {
                                HashMap<Object, String> hashMap = new HashMap<>();

                                hashMap.put("documentid", timestamp);
                                hashMap.put("tilte", tilte);
                                hashMap.put("description", desc);
                                hashMap.put("fileurl", downloadurl);


                                DatabaseReference reference = FirebaseDatabase.getInstance()
                                        .getReference()
                                        .child("Notes")
                                        .child(subject);

                                reference.child(timestamp).setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull  Task<Void> task) {
                                        Toast.makeText(upload_notes.this, "Notes updatated", Toast.LENGTH_SHORT).show();
                                    }
                                });

                            }
                            else
                            {
                                Toast.makeText(upload_notes.this, ""+uriTask.getException(), Toast.LENGTH_SHORT).show();
                            }


                        }
                    });
                }
                
                
                Toast.makeText(upload_notes.this, ""+file_uri, Toast.LENGTH_SHORT).show();
            }
        });


        choose_file.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("application/pdf");
                intent.addCategory(Intent.CATEGORY_OPENABLE);

                try
                {
                    startActivityForResult( Intent.createChooser(intent, "Select a File to Upload"), FILE_SELECT_CODE);
                }
                catch (android.content.ActivityNotFoundException ex) {
                    // Potentially direct the user to the Market with a Dialog
                    Toast.makeText(upload_notes.this, "Please install a File Manager.", Toast.LENGTH_SHORT).show();
                }
            }
        });




    }


    private boolean checkcampermission() {
        boolean result = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)==(PackageManager.PERMISSION_GRANTED);
        boolean result1 = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)==(PackageManager.PERMISSION_GRANTED);
        return result && result1;
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK && requestCode == FILE_SELECT_CODE)
        {
            file_uri = data.getData();

        }
    }

    private void initUI() {
        //UI reference of textView
        final AutoCompleteTextView customerAutoTV = findViewById(R.id.customerTextView);

        // create list of customer
        ArrayList<String> customerList = getCustomerList();

        //Create adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(upload_notes.this, R.layout.spinner_item, customerList);

        //Set adapter
        customerAutoTV.setAdapter(adapter);

        //submit button click event registration
//        findViewById(R.id.submitButton).setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View view)
//            {
//                Toast.makeText(MainActivity.this, customerAutoTV.getText(), Toast.LENGTH_SHORT).show();
//            }
//        });
    }


    private ArrayList<String> getCustomerList()
    {
        ArrayList<String> customers = new ArrayList<>();
        customers.add("Kannada");
        customers.add("English");
        customers.add("Hindi");
        customers.add("Mathematics");
        customers.add("Science");
        customers.add("Social");
        return customers;
    }
}