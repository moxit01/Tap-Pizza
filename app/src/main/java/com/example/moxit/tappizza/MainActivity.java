package com.example.moxit.tappizza;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


   FragmentManager FM=getSupportFragmentManager();
   FragmentTransaction FT= FM.beginTransaction();
    private DrawerLayout d1;
    private ActionBarDrawerToggle abdt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        d1 = (DrawerLayout) findViewById(R.id.dl);
        abdt=new ActionBarDrawerToggle(this,d1,R.string.open,R.string.close);
        abdt.setDrawerIndicatorEnabled(true);
        d1.addDrawerListener(abdt);
        abdt.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final android.support.v4.app.FragmentManager FM=getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction FT=FM.beginTransaction();

        NavigationView nav_view =(NavigationView)findViewById(R.id.nav_view);
        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item)
            {
                int id=item.getItemId();

                if(id==R.id.SignIn)
                {
                    Toast.makeText(MainActivity.this,"SignIn",Toast.LENGTH_SHORT).show();
                    android.support.v4.app.FragmentTransaction FT=FM.beginTransaction();
                    FT.replace(R.id.FrmLyt,new FragSignIn());
                    FT.commit();


                }
                else if(id==R.id.SignUp)
                {
                    Toast.makeText(MainActivity.this,"SignUp",Toast.LENGTH_SHORT).show();
                    android.support.v4.app.FragmentTransaction FT=FM.beginTransaction();
                    FT.replace(R.id.FrmLyt,new FragSignUp());
                    FT.commit();
                }
                else if(id==R.id.Home)
                {
                    Toast.makeText(MainActivity.this,"Home",Toast.LENGTH_SHORT).show();
                    android.support.v4.app.FragmentTransaction FT=FM.beginTransaction();
                    FT.replace(R.id.FrmLyt,new FragHome());
                    FT.commit();
                }


                return true;
            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem item) {


        return abdt.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }



}
























       /* Handler h=new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i=new Intent(MainActivity.this,FragSignUp.class);
                startActivity(i);

                //give time in millisecond
            }
        },2500);*/



