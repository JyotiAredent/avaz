package ardents.in.avaz.Repository;

import android.app.Application;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.JsonObject;

import ardents.in.avaz.Activity.MainActivity;
import ardents.in.avaz.Network.RetrofitClient;
import ardents.in.avaz.Utils.SharedPrefManager;
import ardents.in.avaz.models.LoginModel;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginRepo {
    MutableLiveData<String> loginresult;

    public LoginRepo(Application application) {
        loginresult=new MutableLiveData<>();
    }
    public MutableLiveData<String> login(Context context,String mail,String password){

        RetrofitClient.getClient().login(mail,password).enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                try{
                    if (response.isSuccessful()){
                        LoginModel loginModel=response.body();
                        if (loginModel!=null){
                            SharedPrefManager.getInstance(context).setToken(loginModel.getToken());
                            Log.d("login","token login=="+loginModel.getToken());
                           // Log.d("login","token login=="+SharedPrefManager.getInstance(context).getToken());
                            Intent intent = new Intent(context, MainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(intent);
                        }

                    }else {
                        Toast.makeText(context, "Unauthorised", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Log.d("login","Exception==="+e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {
                Log.d("login","Failed==="+t.getMessage());
            }
        });
        return loginresult;
    }
}
