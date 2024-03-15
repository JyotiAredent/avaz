package ardents.in.avaz.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.FragmentManager;


import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.Toast;

import java.util.Locale;

import ardents.in.avaz.Fragment.HomeFragment;
import ardents.in.avaz.Fragment.KeyboardFragment;
import ardents.in.avaz.R;
import ardents.in.avaz.Utils.SharedPrefManager;
import ardents.in.avaz.Utils.TextToSpeechHelper;
import ardents.in.avaz.databinding.ActivityMainBinding;
import ardents.in.avaz.databinding.DrawerLayoutBinding;
import ardents.in.avaz.databinding.KeyboardPopupBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    private TextToSpeech tps;
    private PopupWindow mPopupWindow;
    TextToSpeechHelper textToSpeechHelper;
    DrawerLayoutBinding drawerLayoutBinding;
    private AudioManager audioManager;
    KeyboardPopupBinding popupBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        drawerLayoutBinding=DrawerLayoutBinding.inflate(getLayoutInflater(),binding.navigation,true);
        popupBinding=KeyboardPopupBinding.inflate(getLayoutInflater());

        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        showHomeFragment();
        initPopup();
        textToSpeechHelper=new TextToSpeechHelper(getApplicationContext());

        binding.cardKeyboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textToSpeechHelper.speak(binding.txtKeyboard.getText().toString());
                binding.cardKeyboard.setVisibility(View.GONE);
               binding.cardPictures.setVisibility(View.VISIBLE);
                showPopup();
                hideFragment();
            }
        });

        binding.cardPictures.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textToSpeechHelper.speak(binding.txtPicture.getText().toString());
                binding.cardPictures.setVisibility(View.GONE);
                binding.cardKeyboard.setVisibility(View.VISIBLE);
                hidePopup();
                showHomeFragment();
            }
        });
//        binding.cardKeyboard.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String toSpeak=binding.txtKeyboard.getText().toString();
//                tps.speak(toSpeak,TextToSpeech.QUEUE_FLUSH,null);
//               // Toast.makeText(MainActivity.this, toSpeak, Toast.LENGTH_SHORT).show();
//            }
//        });

        binding.cardMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textToSpeechHelper.speak(binding.txtMenu.getText().toString());
            }
        });


        binding.frameClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textToSpeechHelper.speak(binding.txtClear.getText().toString());
            }
        });
        binding.frameDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textToSpeechHelper.speak(binding.txtDelete.getText().toString());
            }
        });
//        binding.cardSpeak.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String toSpeak=binding.edtSetCard.getText().toString();
//                tps.speak(toSpeak,TextToSpeech.QUEUE_FLUSH,null);
//            }
//        });


       binding.frameShare.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               shareText("This is the text to share");
           }
       });

       binding.cardMenu.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               //binding.drawerLay.openDrawer(R.layout.drawer_layout);
               binding.drawerLay.openDrawer(GravityCompat.END);
           }
       });

      drawerLayoutBinding.frameEditwords.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Log.d("bindingmsg","Edit Words");
          }
      });

      drawerLayoutBinding.linearVolumeup.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              audioManager.adjustVolume(AudioManager.ADJUST_RAISE,AudioManager.FLAG_SHOW_UI);
          }
      });

      drawerLayoutBinding.frameVolumedown.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              audioManager.adjustVolume(AudioManager.ADJUST_LOWER,AudioManager.FLAG_SHOW_UI);
          }
      });

      drawerLayoutBinding.frameVolumemute.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              audioManager.adjustVolume(AudioManager.ADJUST_MUTE, AudioManager.FLAG_SHOW_UI);
              drawerLayoutBinding.frameVolumeunmute.setVisibility(View.VISIBLE);
              drawerLayoutBinding.frameVolumemute.setVisibility(View.GONE);
          }
      });
      drawerLayoutBinding.frameVolumeunmute.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              audioManager.adjustVolume(AudioManager.ADJUST_UNMUTE,AudioManager.FLAG_SHOW_UI);
              drawerLayoutBinding.frameVolumemute.setVisibility(View.VISIBLE);
              drawerLayoutBinding.frameVolumeunmute.setVisibility(View.GONE);
          }
      });

      drawerLayoutBinding.frameSearch.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              startActivity(new Intent(getApplicationContext(),SearchActivity.class));
          }
      });


//      popupBinding.txtA.setOnClickListener(new View.OnClickListener() {
//          @Override
//          public void onClick(View view) {
//              binding.parentLinear.addView(popupBinding.txtA.getText().toString());
//          }
//      });


    }
    private void shareText(String text){
        Intent shareIntent=new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT,text);
        startActivity(Intent.createChooser(shareIntent,"Share via"));

    }
    private void initPopup(){
        View popupView = LayoutInflater.from(this).inflate(R.layout.keyboard_popup, null);
        mPopupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        mPopupWindow.setFocusable(true);
        mPopupWindow.setBackgroundDrawable(null);
        mPopupWindow.setTouchable(false);
    }
    private void showPopup() {
        if (mPopupWindow!=null){
            mPopupWindow.showAtLocation(binding.fragmentContainerView, Gravity.CENTER, 10, 110);
        }


    }

    private void hidePopup() {
        if (mPopupWindow != null && mPopupWindow.isShowing()) {
            mPopupWindow.dismiss();
        }
    }

    // Method to show the home fragment
    private void showHomeFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragmentContainerView, new HomeFragment()).commit();
    }
    private void hideFragment(){
        FragmentManager fragmentManager=getSupportFragmentManager();
        fragmentManager.beginTransaction().remove(fragmentManager.findFragmentById(R.id.fragmentContainerView)).commit();
    }
}
