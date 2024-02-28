package ardents.in.avaz.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import ardents.in.avaz.Utils.Validation;
import ardents.in.avaz.ViewModels.LoginViewModel;
import ardents.in.avaz.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {
    LoginViewModel viewModel;
    String email, password;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityLoginBinding binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setMessage("Loading...");

        viewModel = ViewModelProviders.of(this).get(LoginViewModel.class);

        binding.txtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
                finish();
            }
        });

        binding.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = binding.loginMail.getText().toString();
                password = binding.loginPass.getText().toString();
                if (!Validation.validateEditText(binding.loginMail)
                        | !Validation.validateEditText(binding.loginPass)) {
                    return;
                }
                viewModel.getIsLoading().observe(LoginActivity.this, new Observer<Boolean>() {
                    @Override
                    public void onChanged(Boolean isLoading) {
                        if (isLoading){
                            progressDialog.show();
                        }else {
                            progressDialog.dismiss();
                        }
                    }

                });

                viewModel.loginUser(getApplicationContext(),email,password);
            }
        });


    }
}