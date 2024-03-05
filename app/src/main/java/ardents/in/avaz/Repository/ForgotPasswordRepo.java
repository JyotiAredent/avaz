package ardents.in.avaz.Repository;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import ardents.in.avaz.Network.RetrofitClient;
import ardents.in.avaz.models.ForgotPasswordModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotPasswordRepo {
    MutableLiveData<String> forgotPassResult;

    public ForgotPasswordRepo(Application appliaction) {
        forgotPassResult=new MutableLiveData<>();
    }
    public MutableLiveData<String> forgotPassword(Context context, String email){
        RetrofitClient.getClient().forgotPass(email).enqueue(new Callback<ForgotPasswordModel>() {
            @Override
            public void onResponse(Call<ForgotPasswordModel> call, Response<ForgotPasswordModel> response) {
                try{
                    if (response.isSuccessful()){
                        ForgotPasswordModel forgotPasswordModel=response.body();
                        Log.d("ForgotPass1234","modeldata=="+forgotPasswordModel);
                        Log.d("ForgotPass1234","message=="+forgotPasswordModel.getMessage());
                        String msg=forgotPasswordModel.getMessage();
                        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
                        forgotPassResult.setValue("success");
                    }else {
                       // Toast.makeText(context, response.errorBody().toString(), Toast.LENGTH_SHORT).show();
                        Toast.makeText(context,"Failed to send password reset link", Toast.LENGTH_SHORT).show();
                        Log.d("ForgotPass1234","error==="+response.errorBody());
                        forgotPassResult.setValue("error");
                    }
                }catch (Exception e){
                    Log.d("ForgotPass1234","Exception==="+e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<ForgotPasswordModel> call, Throwable t) {
                Log.d("login1234","failed==="+t.getMessage());
            }
        });
        return forgotPassResult;
    }
}
