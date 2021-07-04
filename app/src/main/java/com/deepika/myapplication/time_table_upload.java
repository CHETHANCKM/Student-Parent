package com.deepika.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class time_table_upload extends AppCompatActivity {

    ImageView tt_image;
    Button choose, upload;
//    StorageReference mStorageRef;
    Uri file_uri=null;
    StorageReference mStorageRef;

    private  static final int CAMERA_REQUEST_CODE= 100;
    private  static final int STORAGE_REQUEST_CODE= 200;

    private static  final  int IMAGE_PICK_CAMERA_CODE= 300;
    private static  final  int IMAGE_PICK_GALLARY_CODE= 400;



    String [] campermission;
    String [] storagepermission;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_table_upload);

        tt_image = findViewById(R.id.tt_image);
        choose = findViewById(R.id.choose);
        upload = findViewById(R.id.upload);


        campermission = new String[] {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        storagepermission = new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE};


        showimage();


        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (file_uri == null)
                {
                    Toast.makeText(time_table_upload.this, "No file is selected", Toast.LENGTH_SHORT).show();
                }
                else
                {

                    mStorageRef = FirebaseStorage.getInstance().getReference().child("ExtraDocumennts/Timetable");

                    mStorageRef.putFile(file_uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                            while (!uriTask.isSuccessful());

                            String downloadurl = uriTask.getResult().toString();

                            if (uriTask.isSuccessful())
                            {
                                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("timetable");
//
                                reference.child("filepath").setValue(downloadurl).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        Toast.makeText(time_table_upload.this, "Time table Uploaded", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }


                        }
                    });


                    Toast.makeText(time_table_upload.this, ""+file_uri, Toast.LENGTH_SHORT).show();

                }
            }
        });

        choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showimagepicdilog();
                
            }
        });





    }

    private void showimage() {
        final String[] url = {null};
        DatabaseReference databaseReference = FirebaseDatabase.getInstance()
                .getReference("timetable")
                .child("filepath");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                url[0] = snapshot.getValue(String.class);

                if (url[0]==null)
                {
                    Toast.makeText(time_table_upload.this, "Loading... Try again", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Picasso.get().load(url[0]).into(tt_image);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void showimagepicdilog() {

        String options[] = {"Camera", "Gallary"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose Image from");

        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if (which==0)
                {

                    if (!checkcampermission())
                    {
                        requestcameraepermission();
                    }
                    else
                    {
                        pickfromcamera();
                    }

                }
                if (which==1){

                    if (!checkcampermission())
                    {
                        requestcameraepermission();
                    }
                    else
                    {
                        picfromgallary();
                    }

                }
            }
        });
        builder.create().show();
    }

    private void picfromgallary() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, IMAGE_PICK_GALLARY_CODE);
    }

    private void pickfromcamera() {
        ContentValues cv = new ContentValues();
        cv.put(MediaStore.Images.Media.TITLE, "Temp pick");
        cv.put(MediaStore.Images.Media.DESCRIPTION, "desc");
        file_uri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, cv);
        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        i.putExtra(MediaStore.EXTRA_OUTPUT, file_uri);
        startActivityForResult(i, IMAGE_PICK_CAMERA_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode)
        {
            case CAMERA_REQUEST_CODE:
            {
                if (grantResults.length>0)
                {
                    boolean cameraaccepted = grantResults[0]==PackageManager.PERMISSION_GRANTED;
                    boolean staorageaccepted = grantResults[1]==PackageManager.PERMISSION_GRANTED;

                    if (cameraaccepted && staorageaccepted)
                    {
                        pickfromcamera();
                    }
                    else
                    {
                        Toast.makeText(this, "Camera adnd storge permisson requiried", Toast.LENGTH_SHORT).show();
                    }

                }

                else

                {

                }
            }
            break;
            case STORAGE_REQUEST_CODE:
            {
                if (grantResults.length>0)
                {
                    boolean staorageaccepted = grantResults[1]==PackageManager.PERMISSION_GRANTED;

                    if (staorageaccepted)
                    {
                        picfromgallary();
                    }
                    else
                    {
                        Toast.makeText(this, "storage permisson requiried", Toast.LENGTH_SHORT).show();
                    }

                }
                else
                {

                }
            }
            break;

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (resultCode == RESULT_OK)
        {
            if(requestCode == IMAGE_PICK_CAMERA_CODE)
            {

                tt_image.setImageURI(file_uri);
            }
            else if (requestCode == IMAGE_PICK_GALLARY_CODE)
            {
                file_uri = data.getData();
                tt_image.setImageURI(file_uri);
            }
        }

    }



    private void requestcameraepermission() {
        ActivityCompat.requestPermissions(this, campermission, CAMERA_REQUEST_CODE);
    }


    private boolean checkcampermission() {
        boolean result = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)==(PackageManager.PERMISSION_GRANTED);
        boolean result1 = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)==(PackageManager.PERMISSION_GRANTED);
        return result && result1;
    }




}