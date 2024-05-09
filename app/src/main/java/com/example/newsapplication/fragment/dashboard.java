package com.example.newsapplication.fragment;

import static androidx.core.content.ContextCompat.getSystemService;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.newsapplication.CallListView;
import com.example.newsapplication.MainActivity;
import com.example.newsapplication.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.zip.Inflater;


public class dashboard extends Fragment {

    GridView gridView;
    Context context = getContext();
    ArrayList<HashMap<String,String>>arrayList = new ArrayList<>();
    HashMap<String,String>hashMap = new HashMap<>();

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myView =  inflater.inflate(R.layout.fragment_dashboard, container, false);


        gridView = myView.findViewById(R.id.GridView);



        hashMap = new HashMap<>();
        hashMap.put("id", "1");
        hashMap.put("image", "https://res.cloudinary.com/dwlcudfef/image/upload/v1715218902/icons/hcspubfb3titsq2ojrj2.png");
        hashMap.put("text", "News");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("id", "2");
        hashMap.put("image", "https://res.cloudinary.com/dwlcudfef/image/upload/v1715221364/icons/tzw5qvycyiy1xaqwehfh.png");
        hashMap.put("text", "Education");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("id", "3");
        hashMap.put("image", "https://res.cloudinary.com/dwlcudfef/image/upload/v1715256380/icons/ti2riptmdjlwatvyjn6j.png");
        hashMap.put("text", "Call Book");
        arrayList.add(hashMap);



        MyAdapter adapter = new MyAdapter(getContext());
        gridView.setAdapter(adapter);













        return myView;
    }





    private class MyAdapter extends BaseAdapter{

        private Context context;

        public MyAdapter(Context context) {
            this.context = context;
        }
        @Override
        public int getCount() {
            return arrayList.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @SuppressLint("MissingInflatedId")
        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            ImageView imageView;
            TextView textView;
            CardView cardView;

            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View viewHolder = layoutInflater.inflate(R.layout.dash_item, viewGroup, false);

            imageView = viewHolder.findViewById(R.id.dash_image);
            textView = viewHolder.findViewById(R.id.dash_text);
            cardView = viewHolder.findViewById(R.id.cardView);



            hashMap = arrayList.get(i);
            String id = hashMap.get("id");
            String image = hashMap.get("image");
            String text = hashMap.get("text");


            if (id.equals("1")){
                cardView.setOnClickListener(view1 -> {
                    startActivity(new Intent(context, MainActivity.class));
                });
            }
            if (id.equals("2")){
                cardView.setOnClickListener(view1 -> {
                    Toast.makeText(context, "this 2 item", Toast.LENGTH_SHORT).show();
                });
            }
            if (id.equals("3")){
                cardView.setOnClickListener(view1 -> {
                    startActivity(new Intent(context, CallListView.class));
                });
            }




            Picasso.get().load(image).into(imageView);
            textView.setText(text);


            return viewHolder;
        }
    }





}