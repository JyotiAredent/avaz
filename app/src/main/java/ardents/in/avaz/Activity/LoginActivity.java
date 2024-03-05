package ardents.in.avaz.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
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




        binding.hidePasswd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int cursorPosition = binding.loginPass.getSelectionStart();
                binding.loginPass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                binding.hidePasswd.setVisibility(View.GONE);
                binding.showPasswd.setVisibility(View.VISIBLE);
                binding.loginPass.setSelection(cursorPosition);
            }
        });
        binding.showPasswd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int cursorPosition = binding.loginPass.getSelectionStart();
                binding.loginPass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                binding.showPasswd.setVisibility(View.GONE);
                binding.hidePasswd.setVisibility(View.VISIBLE);
                binding.loginPass.setSelection(cursorPosition);
            }
        });

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
                }else {
                    viewModel.loginUser(getApplicationContext(),email,password).observe(LoginActivity.this, new Observer<String>() {
                        @Override
                        public void onChanged(String s) {
                            if (s.equals("success")){
                                Validation.dismissProgressDialog();
                            }else {
                                Validation.dismissProgressDialog();
                            }
                        }
                    });
                    Validation.showPrograssDialog(LoginActivity.this);
                }

            }
        });

        binding.forgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a=new Intent(LoginActivity.this, ForgetPasswordActivity.class);
                startActivity(a);
            }
        });


    }
}