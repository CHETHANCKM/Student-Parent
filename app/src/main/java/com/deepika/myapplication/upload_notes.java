package com.deepika.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import java.util.ArrayList;

public class upload_notes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_notes);
        initUI();
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