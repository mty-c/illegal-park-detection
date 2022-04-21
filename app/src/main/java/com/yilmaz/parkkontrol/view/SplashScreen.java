package com.yilmaz.parkkontrol.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.AnimationUtils;

import com.yilmaz.parkkontrol.view.MainActivity;
import com.yilmaz.parkkontrolu.R;
import com.yilmaz.parkkontrolu.databinding.ActivitySplashScreenBinding;

public class SplashScreen extends AppCompatActivity {


    private ActivitySplashScreenBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.topText.startAnimation(AnimationUtils.loadAnimation(this,R.anim.top_animation));
        binding.middleText.startAnimation(AnimationUtils.loadAnimation(this,R.anim.middle_animation));
        binding.bottomText.startAnimation(AnimationUtils.loadAnimation(this,R.anim.bottom_animation));

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
                finish();

            }

        }, 2 * 1000);
    }
}