package com.example.newsapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.newsapplication.fragment.dashboard;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

public class DashActivity extends AppCompatActivity {


    FrameLayout frameLayout;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    MaterialToolbar toolbar;
    View headerView;
    ImageView imageView, closeImgView;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash);

        frameLayout = findViewById(R.id.frameLayout);
        toolbar = findViewById(R.id.toolber);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        // header image and button introduce ------------------------
        headerView = navigationView.getHeaderView(0);
        imageView = headerView.findViewById(R.id.headerImage);
        closeImgView = headerView.findViewById(R.id.closeImgView);

        closeImgView.setOnClickListener(v->{
            drawerLayout.closeDrawer(GravityCompat.START);
        });


        Picasso.get().load("https://res.cloudinary.com/daily-now/image/upload/f_auto,q_auto/v1/posts/e7ef4ff144eceab8d8d06a2317fbf83d?_a=AQAEuiZ").into(imageView);


        fragmentReplace(new dashboard());


        // Drawer layout Open and close = ====================================
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                DashActivity.this,drawerLayout,toolbar,R.string.DrawerOpen,R.string.DrawerClose);

        drawerLayout.addDrawerListener(toggle);









        // navigation item selected ======================================
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                        //how to select item ---------------------------------------
                        if (item.getItemId()==R.id.education){
                            Toast.makeText(DashActivity.this, "Education Icon", Toast.LENGTH_SHORT).show();
                            drawerLayout.closeDrawer(GravityCompat.START);
                        }if (item.getItemId() == R.id.winItem){
                            Toast.makeText(DashActivity.this, "Win Icon", Toast.LENGTH_SHORT).show();
                            drawerLayout.closeDrawer(GravityCompat.START);
                        }if (item.getItemId() == R.id.newsItem){
                    drawerLayout.closeDrawer(GravityCompat.START);

                }



                return true;
            }
        });















    }

    // fragment replace ----------------------------------
    private void fragmentReplace(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }


}