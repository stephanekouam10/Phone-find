package com.example.phonefind;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {

    private static int SPLASH_SCREEN = 5000;
    ImageView mImageView;
    TextView mTextView1, mTextView2;
    Animation top, bottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sphash);

        mImageView = (ImageView)findViewById(R.id.imageView);
        mTextView1 = (TextView) findViewById(R.id.name_app);
        mTextView2 = (TextView) findViewById(R.id.Copyright_txt);

        top = AnimationUtils.loadAnimation(this, R.anim.top);
        bottom = AnimationUtils.loadAnimation(this, R.anim.bottom);

        mImageView.setAnimation(top);
        mTextView1.setAnimation(bottom);
        mTextView2.setAnimation(bottom);

        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, OpenActivity.class);
                startActivity(intent);
                finish();
            }
        },SPLASH_SCREEN);
    }
}