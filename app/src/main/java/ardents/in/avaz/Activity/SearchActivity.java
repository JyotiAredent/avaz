package ardents.in.avaz.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import ardents.in.avaz.R;
import ardents.in.avaz.databinding.ActivitySearchBinding;

public class SearchActivity extends AppCompatActivity {
    ActivitySearchBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivitySearchBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}