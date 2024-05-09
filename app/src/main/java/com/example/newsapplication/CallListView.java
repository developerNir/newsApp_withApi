package com.example.newsapplication;

import android.annotation.SuppressLint;
import android.content.ContentResolver;

import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;

import android.widget.ArrayAdapter;
import android.widget.Button;

import android.widget.ListView;
import android.Manifest;


import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;



import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CallListView extends AppCompatActivity {


    ListView listView;
    ArrayList<String> arrayList;
    ArrayAdapter arrayAdapter;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_list_view);


        listView = findViewById(R.id.contentListView);
        arrayList = new ArrayList<>(); //empty array list.
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(arrayAdapter);






            //checking whether the read contact permission is granted.
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
// requesting to the user for permission.
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, 100);

            } else {
//if app already has permission this block will execute.
                readContacts();
            }












    }





    // if the user clicks ALLOW in dialog this method gets called.
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        readContacts();
    }
    // function to read contacts using content resolver
    @SuppressLint("Range")
    private void readContacts() {

        ContentResolver contentResolver = getContentResolver();
        Cursor cursor = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null, null, null, null);

        if (cursor!= null && cursor.moveToFirst()) {
            do {
                // name and number from the cursor
                arrayList.add("Number : "+cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)));
                arrayList.add("Name : "+cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)));

            } while (cursor.moveToNext());
            arrayAdapter.notifyDataSetChanged();
        }
    }
    // end function ======================







}