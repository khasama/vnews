package com.example.vnews.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.vnews.R;

public class SplashScreenActivity extends AppCompatActivity {

    ImageView ivLogo;
    Animation topAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        ivLogo = findViewById(R.id.ivLogo);
        topAnimation = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        topAnimation.setDuration(1500);

        ivLogo.setAnimation(topAnimation);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                startActivities(new Intent[]{intent});
                finish();
            }
        }, 3500);
    }
}