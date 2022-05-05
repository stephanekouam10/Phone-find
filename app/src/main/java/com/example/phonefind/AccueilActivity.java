package com.example.phonefind;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AccueilActivity extends AppCompatActivity {

    private BottomNavigationView mMainNav ;
    private FrameLayout mMainFrame;

    private TextView mCompte, mEmail;

    private FragmentHome homeFragment;
    private FragmentPosition positionFragment;
    private FragmentProfil profilFragment;

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;

    FirebaseUser mFirebaseUser;
    DatabaseReference mDatabaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);

        mMainFrame = (FrameLayout)findViewById(R.id.main_frame);
        mMainNav = (BottomNavigationView) findViewById(R.id.bottom_navigation);

        mCompte = (TextView)findViewById(R.id.txt_compte);
        mEmail = (TextView)findViewById(R.id.txtEmail);

        mFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        mDatabaseReference = FirebaseDatabase.getInstance().getReference();

        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(this,mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //mEmail.setText(mFirebaseUser.getEmail().toString());

        //Toast.makeText(this, mFirebaseUser.getEmail(), Toast.LENGTH_SHORT);
        //mCompte.setText(mFirebaseUser.getDisplayName().toString());

        homeFragment = new FragmentHome();
        positionFragment = new FragmentPosition();
        profilFragment = new FragmentProfil();

        setFragment(homeFragment);
        mMainNav.setItemBackgroundResource(R.color.colorPrimaryDark);

        mMainNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){

                    case R.id.home :
                        mMainNav.setItemBackgroundResource(R.color.colorPrimaryDark);
                        setFragment(homeFragment);
                        return true;

                    case R.id.position :
//                        mMainNav.setItemBackgroundResource(R.color.colorPrimaryDark);
//                        setFragment(positionFragment);
                        Intent i = new Intent(AccueilActivity.this, LocationActivity.class);
                       startActivity(i);
                        return true;

                    case R.id.profil :
                        mMainNav.setItemBackgroundResource(R.color.colorPrimaryDark);
                        setFragment(profilFragment);
                        Toast.makeText(AccueilActivity.this, "Bonjour", Toast.LENGTH_SHORT);
                        return true;

                    default:
                        return  false;
                }
            }
        });

        if(mFirebaseUser != null){
            Toast.makeText(this, mFirebaseUser.getEmail(), Toast.LENGTH_SHORT);
        } else Toast.makeText(this, "vide", Toast.LENGTH_SHORT);

        Toast.makeText(this, "Bonjour", Toast.LENGTH_SHORT);
    }

    private void setFragment(Fragment fragment) {

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//       int id = item.getItemId();
//       if (id == R.id.locate){
//           Intent i = new Intent(AccueilActivity.this, LocationActivity.class);
//           startActivity(i);
//           return false;
//
//       }


        if ( mToggle.onOptionsItemSelected(item)){
            return  true;
        }
        return super.onOptionsItemSelected(item);
    }
}