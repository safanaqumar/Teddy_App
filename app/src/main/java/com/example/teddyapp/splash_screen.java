package com.example.teddyapp;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class splash_screen extends AppCompatActivity {
    private ImageView splashlogo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        init();
        getSupportActionBar().hide();

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.splash_animation);
        splashlogo.startAnimation(animation);

        final Intent intent = new Intent(this, login_activity.class);

        Thread timer = new Thread() {

            public void run() {

                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {

                    startActivity(intent);
                    finish();
                }
            }
        };

        timer.start();

    }

    private void init() {
        splashlogo = findViewById(R.id.splash_logo);
    }
}
