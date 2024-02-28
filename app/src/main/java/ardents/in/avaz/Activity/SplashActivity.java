package ardents.in.avaz.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import ardents.in.avaz.R;
import ardents.in.avaz.Utils.SharedPrefManager;
import ardents.in.avaz.databinding.ActivitySplashBinding;

public class SplashActivity extends AppCompatActivity {
    ActivitySplashBinding binding;
    Animation topAnim,bottomAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (SharedPrefManager.getInstance(getApplicationContext()).userisLoggedIn()){
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                }else {
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                }

                finish();
            }
        },2000);




        topAnim= AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnim= AnimationUtils.loadAnimation(this,R.anim.botttom_animation);

        binding.splashLogo.setAnimation(topAnim);
        binding.textView9.setAnimation(bottomAnim);
    }
}