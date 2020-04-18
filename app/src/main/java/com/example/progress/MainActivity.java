package com.example.progress;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
    public static int SPLASH_SCREEN_DURATION=2500;

    Animation splashAnimation;
    TextView splashText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        //add animations
        splashAnimation= AnimationUtils.loadAnimation(this,R.anim.top_animation);

        //hooks
        splashText=findViewById(R.id.startText);

        //adding animation
        splashText.setAnimation(splashAnimation);

        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run() {
                Intent intent= new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        },SPLASH_SCREEN_DURATION);
    }
}
