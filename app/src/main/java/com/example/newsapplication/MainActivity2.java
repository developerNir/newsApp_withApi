package com.example.newsapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MainActivity2 extends AppCompatActivity {

    TextView name, title, description,content,date,Myname;
    ImageView imageView;

    public static String imageUrl;
    public static String myTitle;
    public static String myName;
    public static String myDescription;
    public static String myContent;
    public static String myUrl;
    public static String mydate;
    public static String whatName;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        // introduction -------------------------------
        imageView = findViewById(R.id.imageV);
        name = findViewById(R.id.nameTv);
        title = findViewById(R.id.titleTv);
        description = findViewById(R.id.description);
        content = findViewById(R.id.content);
        date = findViewById(R.id.dete);
        Myname = findViewById(R.id.whatName);

        // set content ----------------------------
        Picasso.get().load(imageUrl).into(imageView);
        title.setText(myTitle);
        description.setText(myDescription);
        name.setText(myName);

        content.setText(myContent);
        content.append("\n\n"+myUrl);
        date.setText(mydate);
        Myname.setText(whatName);

    }
}