package ardents.in.avaz.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;


import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import java.util.Locale;

import ardents.in.avaz.Fragment.HomeFragment;
import ardents.in.avaz.Fragment.KeyboardFragment;
import ardents.in.avaz.R;
import ardents.in.avaz.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    private TextToSpeech tps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        FragmentManager fragmentManager=getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragmentContainerView,new HomeFragment()).commit();

        binding.cardKeyboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.cardKeyboard.setVisibility(View.GONE);
                binding.cardPictures.setVisibility(View.VISIBLE);
                FragmentManager fragmentManager=getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragmentContainerView,new KeyboardFragment()).commit();

            }
        });
        binding.cardPictures.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager=getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragmentContainerView,new HomeFragment()).commit();
                binding.cardPictures.setVisibility(View.GONE);
                binding.cardKeyboard.setVisibility(View.VISIBLE);
            }
        });


        tps=new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int result = tps.setLanguage(new Locale("en", "IN")); // Specify Indian English locale
                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Toast.makeText(MainActivity.this, "Indian English is not supported", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Text to Speech initialization failed", Toast.LENGTH_SHORT).show();
                }
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
                String toSpeak=binding.txtMenu.getText().toString();
                tps.speak(toSpeak,TextToSpeech.QUEUE_FLUSH,null);
               // Toast.makeText(MainActivity.this, toSpeak, Toast.LENGTH_SHORT).show();
            }
        });

//        binding.cardQuick.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String toSpeak=binding.txtQuick.getText().toString();
//                tps.speak(toSpeak,TextToSpeech.QUEUE_FLUSH,null);
//               // Toast.makeText(MainActivity.this, toSpeak, Toast.LENGTH_SHORT).show();
//            }
//        });
//        binding.cardStarted.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String toSpeak=binding.txtStarted.getText().toString();
//                tps.speak(toSpeak,TextToSpeech.QUEUE_FLUSH,null);
//                //Toast.makeText(MainActivity.this, toSpeak, Toast.LENGTH_SHORT).show();
//            }
//        });
//        binding.cardLanguage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String toSpeak=binding.txtLanguage.getText().toString();
//                tps.speak(toSpeak,TextToSpeech.QUEUE_FLUSH,null);
//               // Toast.makeText(MainActivity.this, toSpeak, Toast.LENGTH_SHORT).show();
//            }
//        });
//        binding.cardBasic.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String toSpeak=binding.txtBasic.getText().toString();
//                tps.speak(toSpeak,TextToSpeech.QUEUE_FLUSH,null);
//                //Toast.makeText(MainActivity.this, toSpeak, Toast.LENGTH_SHORT).show();
//            }
//        });
//        binding.cardAdvance.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String toSpeak=binding.txtAdvance.getText().toString();
//                tps.speak(toSpeak,TextToSpeech.QUEUE_FLUSH,null);
//                //Toast.makeText(MainActivity.this, toSpeak, Toast.LENGTH_SHORT).show();
//            }
//        });
//        binding.cardCore.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String toSpeak=binding.txtCore.getText().toString();
//                tps.speak(toSpeak,TextToSpeech.QUEUE_FLUSH,null);
//               // Toast.makeText(MainActivity.this, toSpeak, Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        binding.cardGoback.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String toSpeak=binding.txtGoback.getText().toString();
//                tps.speak(toSpeak,TextToSpeech.QUEUE_FLUSH,null);
//               // Toast.makeText(MainActivity.this, toSpeak, Toast.LENGTH_SHORT).show();
//            }
//        });
//        binding.cardHome.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String toSpeak=binding.txtHome.getText().toString();
//                tps.speak(toSpeak,TextToSpeech.QUEUE_FLUSH,null);
//                //Toast.makeText(MainActivity.this, toSpeak, Toast.LENGTH_SHORT).show();
//            }
//        });
//        binding.cardSearch.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String toSpeak=binding.txtSearch.getText().toString();
//                tps.speak(toSpeak,TextToSpeech.QUEUE_FLUSH,null);
//                //Toast.makeText(MainActivity.this, toSpeak, Toast.LENGTH_SHORT).show();
//            }
//        });
//        binding.cardUp.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String toSpeak=binding.txtUp.getText().toString();
//                tps.speak(toSpeak,TextToSpeech.QUEUE_FLUSH,null);
//                //Toast.makeText(MainActivity.this, toSpeak, Toast.LENGTH_SHORT).show();
//            }
//        });
//        binding.cardDown.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String toSpeak=binding.txtDown.getText().toString();
//                tps.speak(toSpeak,TextToSpeech.QUEUE_FLUSH,null);
//                //Toast.makeText(MainActivity.this, toSpeak, Toast.LENGTH_SHORT).show();
//            }
//        });


        binding.edtSetCard.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

//                if (binding.edtSetCard.getText().toString()!=null)
//                {
//                    binding.cardAction.setVisibility(View.VISIBLE);
//                }else {
//                    binding.cardAction.setVisibility(View.GONE);
//                }

//                if (charSequence!=null){
//                    binding.cardAction.setVisibility(View.VISIBLE);
//                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable==null){
                    binding.cardAction.setVisibility(View.GONE);
                }

            }
        });

        binding.cardClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String toSpeak=binding.txtClear.getText().toString();
                tps.speak(toSpeak,TextToSpeech.QUEUE_FLUSH,null);
                binding.edtSetCard.setText("");
            }
        });
        binding.cardDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String toSpeak=binding.txtDelete.getText().toString();
                tps.speak(toSpeak,TextToSpeech.QUEUE_FLUSH,null);
                String text=binding.edtSetCard.getText().toString();
                if (!text.isEmpty()){
                    String newText=text.substring(0,text.length()-1);
                    binding.edtSetCard.setText(newText);
                }

            }
        });
        binding.cardSpeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String toSpeak=binding.edtSetCard.getText().toString();
                tps.speak(toSpeak,TextToSpeech.QUEUE_FLUSH,null);
            }
        });
    }

}