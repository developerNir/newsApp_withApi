package com.example.newsapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    ProgressBar progressBar;
    TextView textView;
    RecyclerView recyclerView;
    String TAG = "res";
    String url = "https://newsapi.org/v2/everything?q=bangla&apiKey=0b6e4660168d41e9b39f558302b16175";

    // missing keyword url -------------------- apiKey = "";
//    String urlOne = "https://newsapi.org/v2/everything?q=bitcoin";

    ArrayList<HashMap<String,String>>arrayList = new ArrayList<>();
    HashMap<String,String>hashMap;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = (ProgressBar) findViewById(R.id.progress_circular);
        textView = (TextView) findViewById(R.id.textView);
        recyclerView = findViewById(R.id.recyclerView);

        progressBar.setVisibility(View.VISIBLE);
        textView.setVisibility(View.GONE);


        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                progressBar.setVisibility(View.GONE);
                textView.setVisibility(View.GONE);
                try {
                    String status = response.getString("status");
//                    textView.append("\n"+response);
                    JSONArray jsonArray = response.getJSONArray("articles");
                    Log.d(TAG, "onResponse: "+jsonArray);
                    for (int x =1; x<jsonArray.length(); x++){
                        JSONObject jsonObject = jsonArray.getJSONObject(x);

                        JSONObject source = jsonObject.getJSONObject("source");
                        String name = source.getString("name");

                        String author = jsonObject.getString("author");
                        String title = jsonObject.getString("title");
                        String description = jsonObject.getString("description");
                        String image = jsonObject.getString("urlToImage");
                        String url = jsonObject.getString("url");
                        String date = jsonObject.getString("publishedAt");
                        String content = jsonObject.getString("content");


                        hashMap = new HashMap<>();
                        hashMap.put("Author",author);
                        hashMap.put("title", title);
                        hashMap.put("description",description);
                        hashMap.put("image",image);
                        hashMap.put("url",url);
                        hashMap.put("content", content);
                        hashMap.put("date", date);
                        hashMap.put("name",name);
                        arrayList.add(hashMap);



                    }
                    // set adapter and add recyclerView -------------------------
                    MyAdapter adapter = new MyAdapter();
                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressBar.setVisibility(View.GONE);
                textView.setVisibility(View.VISIBLE);
                textView.append("\n"+error);
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String,String>hashMap = new HashMap<>();
                hashMap.put("content-type", "application/json");
                hashMap.put("user-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/104.0.0.0 Safari/537.36");
                hashMap.put("accept", "text/html,application/xhtml+xml,application/xml,application/json;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
//                hashMap.put("Authorization","0b6e4660168d41e9b39f558302b16175");
                return hashMap;
            }
        };



        requestQueue.add(jsonObjectRequest);


    }

    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.myViewHolder>{
        @NonNull
        @Override
        public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = getLayoutInflater();
            View view = layoutInflater.inflate(R.layout.item_news, parent, false);
            return new myViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull myViewHolder holder, int position) {

            hashMap = arrayList.get(position);
            String author = hashMap.get("Author");
            String title = hashMap.get("title");
            String description = hashMap.get("description");
            String image = hashMap.get("image");
            String url = hashMap.get("url");
            String contentString  = hashMap.get("content");
            String date = hashMap.get("date");
            String name = hashMap.get("name");

            Picasso.get().load(image).into(holder.imageView);
            holder.desTv.setText(description);
            holder.titleTv.setText(title);
            holder.linearLayout.setOnClickListener(v->{
                MainActivity2.myTitle = title;
                MainActivity2.myName = author;
                MainActivity2.imageUrl = image;
                MainActivity2.myUrl = url;
                MainActivity2.myDescription = description;
                MainActivity2.myContent = contentString;
                MainActivity2.mydate = date;
                MainActivity2.whatName = name;
                startActivity(new Intent(MainActivity.this, MainActivity2.class));
            });

        }

        @Override
        public int getItemCount() {
            return arrayList.size();
        }

        private class myViewHolder extends RecyclerView.ViewHolder{

            TextView titleTv,desTv;
            LinearLayout linearLayout;
            ImageView imageView;
            public myViewHolder(@NonNull View itemView) {
                super(itemView);
                titleTv = itemView.findViewById(R.id.titleTv);
                desTv = itemView.findViewById(R.id.desTV);
                imageView = itemView.findViewById(R.id.imageView);
                linearLayout = itemView.findViewById(R.id.card);
            }
        }
    }



}