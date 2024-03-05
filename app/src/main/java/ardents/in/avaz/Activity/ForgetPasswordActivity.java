package ardents.in.avaz.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;

import ardents.in.avaz.R;
import ardents.in.avaz.Utils.Validation;
import ardents.in.avaz.ViewModels.ForgotPasswordViewModel;
import ardents.in.avaz.databinding.ActivityForgetPasswordBinding;

public class ForgetPasswordActivity extends AppCompatActivity {
    ActivityForgetPasswordBinding binding;
    ForgotPasswordViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityForgetPasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel= ViewModelProviders.of(this).get(ForgotPasswordViewModel.class);




        binding.btnResetpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mail=binding.forgotMail.getText().toString();
                if (!Validation.validateEditText(binding.forgotMail)){
                    return;
                }
                else {
                    viewModel.forgotPassUser(ForgetPasswordActivity.this,mail).observe(ForgetPasswordActivity.this, new Observer<String>() {
                        @Override
                        public void onChanged(String s) {
                            if (s.equals("success")){
                                Validation.dismissProgressDialog();
                            } else {
                                Validation.dismissProgressDialog();
                            }
                        }
                    });
                    Validation.showPrograssDialog(ForgetPasswordActivity.this);

                }
                binding.forgotMail.setText("");
            }
        });
    }
}