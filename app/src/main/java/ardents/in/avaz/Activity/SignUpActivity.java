package ardents.in.avaz.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import ardents.in.avaz.R;
import ardents.in.avaz.Utils.Validation;
import ardents.in.avaz.ViewModels.RegisterViewModel;
import ardents.in.avaz.databinding.ActivitySignUpBinding;

public class SignUpActivity extends AppCompatActivity {
    RegisterViewModel viewModel;
    String name,mail,password,age;

    ActivitySignUpBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel= ViewModelProviders.of(this).get(RegisterViewModel.class);

        binding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUpActivity.this,LoginActivity.class));
            }
        });

        binding.register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name=binding.name.getText().toString();
                mail=binding.email.getText().toString();
                password=binding.passwd.getText().toString();
                age=binding.age.getText().toString();
                if (!Validation.validateEditText(binding.name)
                |!Validation.validateEditText(binding.email)
                |!Validation.validateEditText(binding.passwd)
                |!Validation.validateEditText(binding.age)){
                   return;
                }
                viewModel.registerUser(getApplicationContext(),name,mail,password,age);


            }
        });

    }
}