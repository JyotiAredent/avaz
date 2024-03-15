package ardents.in.avaz.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;
import java.util.Locale;

import ardents.in.avaz.Activity.MainActivity;
import ardents.in.avaz.Adapter.CategoryAdapter;
import ardents.in.avaz.R;
import ardents.in.avaz.ViewModels.CategoryViewModel;
import ardents.in.avaz.databinding.FragmentHomeBinding;
import ardents.in.avaz.models.CategoryModel;

public class HomeFragment extends Fragment {
    private TextToSpeech tps;
    FragmentHomeBinding binding;
    CategoryViewModel viewModel;
    List<CategoryModel> categoryModelList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentHomeBinding.inflate(inflater,container,false);
        CategoryAdapter adapter=new CategoryAdapter(getContext(),categoryModelList);
        binding.categoryRecycler.setAdapter(adapter);


        viewModel= ViewModelProviders.of(HomeFragment.this).get(CategoryViewModel.class);

         tps=new TextToSpeech(requireContext(), new TextToSpeech.OnInitListener() {
             @Override
             public void onInit(int status) {
                 if (status == TextToSpeech.SUCCESS) {
                     int result = tps.setLanguage(new Locale("en", "IN")); // Specify Indian English locale
                     if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                         Toast.makeText(getContext(), "Indian English is not supported", Toast.LENGTH_SHORT).show();
                     }
                 } else {
                     Toast.makeText(getContext(), "Text to Speech initialization failed", Toast.LENGTH_SHORT).show();
                 }
             }
         });

//        binding.cardQuick.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String toSpeak=binding.txtQuick.getText().toString();
//                speakText(toSpeak);
//               // Toast.makeText(MainActivity.this, toSpeak, Toast.LENGTH_SHORT).show();
//            }
//        });
//        binding.cardStarted.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String toSpeak=binding.txtStarted.getText().toString();
//                speakText(toSpeak);
//                //Toast.makeText(MainActivity.this, toSpeak, Toast.LENGTH_SHORT).show();
//            }
//        });
//        binding.cardLanguage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String toSpeak=binding.txtLanguage.getText().toString();
//                speakText(toSpeak);
//               // Toast.makeText(MainActivity.this, toSpeak, Toast.LENGTH_SHORT).show();
//            }
//        });
//        binding.cardBasic.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String toSpeak=binding.txtBasic.getText().toString();
//                speakText(toSpeak);
//                //Toast.makeText(MainActivity.this, toSpeak, Toast.LENGTH_SHORT).show();
//            }
//        });
//        binding.cardAdvance.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String toSpeak=binding.txtAdvance.getText().toString();
//                speakText(toSpeak);
//                //Toast.makeText(MainActivity.this, toSpeak, Toast.LENGTH_SHORT).show();
//            }
//        });
//        binding.cardCore.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String toSpeak=binding.txtCore.getText().toString();
//                speakText(toSpeak);
//               // Toast.makeText(MainActivity.this, toSpeak, Toast.LENGTH_SHORT).show();
//            }
//        });

        binding.cardGoback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String toSpeak=binding.txtGoback.getText().toString();
                speakText(toSpeak);
               // Toast.makeText(MainActivity.this, toSpeak, Toast.LENGTH_SHORT).show();
            }
        });
        binding.cardHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String toSpeak=binding.txtHome.getText().toString();
                speakText(toSpeak);
                //Toast.makeText(MainActivity.this, toSpeak, Toast.LENGTH_SHORT).show();
            }
        });
        binding.cardSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String toSpeak=binding.txtSearch.getText().toString();
                speakText(toSpeak);
                //Toast.makeText(MainActivity.this, toSpeak, Toast.LENGTH_SHORT).show();
            }
        });
        binding.cardUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String toSpeak=binding.txtUp.getText().toString();
                speakText(toSpeak);
                //Toast.makeText(MainActivity.this, toSpeak, Toast.LENGTH_SHORT).show();
            }
        });
        binding.cardDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String toSpeak=binding.txtDown.getText().toString();
                speakText(toSpeak);
                //Toast.makeText(MainActivity.this, toSpeak, Toast.LENGTH_SHORT).show();
            }
        });

        viewModel.categoryresult().observe(getViewLifecycleOwner(), new Observer<List<CategoryModel>>() {
            @Override
            public void onChanged(List<CategoryModel> list) {
                if (list!=null){
                    Log.d("categorydata","==============="+list);
                    categoryModelList=list;
                    adapter.updateCategoryList(categoryModelList);
                }
            }
        });




        return binding.getRoot();
    }
    private void speakText(String text) {
        if (tps != null && !text.isEmpty()) {
            tps.speak(text, TextToSpeech.QUEUE_FLUSH, null);
        }
    }

}