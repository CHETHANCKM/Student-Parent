package com.deepika.myapplication;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class homepage extends AppCompatActivity {

    private DrawerLayout drawer;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.options_menu);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.nav_dashboard:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                new DashboardFragment()).commit();
                        break;
                    case R.id.nav_noticeboard:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                new NoticeBoard()).commit();
                        break;

                    case R.id.nav_myprofile:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                new myprofile()).commit();
                        break;

                    case R.id.nav_logout:
                            FirebaseAuth.getInstance().signOut();
                            Intent intent = new Intent(homepage.this, main_login.class);
                            startActivity(intent);
                        break;


                }
                drawer.closeDrawer(GravityCompat.START);
                return true;

            }
        });


        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new DashboardFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_dashboard);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);

        for(int i = 0; i < menu.size(); i++){
            Drawable drawable = menu.getItem(i).getIcon();
            if(drawable != null) {
                drawable.mutate();
                drawable.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_ATOP);
            }
        }
        return true;
    }




    public void onClick(View v) {
        Intent intent = new Intent(homepage.this, DashboardFragment.class);
        startActivity(intent);
    }
}

