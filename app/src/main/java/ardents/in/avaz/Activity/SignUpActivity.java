package ardents.in.avaz.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;

import ardents.in.avaz.R;
import ardents.in.avaz.Utils.Validation;
import ardents.in.avaz.ViewModels.RegisterViewModel;
import ardents.in.avaz.databinding.ActivitySignUpBinding;

public class SignUpActivity extends AppCompatActivity {
    RegisterViewModel viewModel;
    String name,mail,password,age;

    ActivitySignUpBinding binding;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        progressDialog=new ProgressDialog(SignUpActivity.this);
        progressDialog.setMessage("Loading");


        viewModel= ViewModelProviders.of(this).get(RegisterViewModel.class);



        binding.hidePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int cursorPosition = binding.passwd.getSelectionStart();
                binding.passwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                binding.hidePassword.setVisibility(View.GONE);
                binding.showPassword.setVisibility(View.VISIBLE);
                binding.passwd.setSelection(cursorPosition);
            }
        });
        binding.showPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int cursorPosition = binding.passwd.getSelectionStart();
                binding.passwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                binding.showPassword.setVisibility(View.GONE);
                binding.hidePassword.setVisibility(View.VISIBLE);
                binding.passwd.setSelection(cursorPosition);
            }
        });

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
                }else {
                    viewModel.registerUser(getApplicationContext(),name,mail,password,age).observe(SignUpActivity.this, new Observer<String>() {
                        @Override
                        public void onChanged(String s) {
                            if (s.equals("success")){
                                Validation.dismissProgressDialog();
                            }else {
                                Validation.dismissProgressDialog();
                            }
                        }
                    });
                    Validation.showPrograssDialog(SignUpActivity.this);
                }


            }
        });

    }
}