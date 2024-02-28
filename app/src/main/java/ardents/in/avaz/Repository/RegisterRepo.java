package ardents.in.avaz.Repository;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.google.gson.JsonObject;

import ardents.in.avaz.Activity.LoginActivity;
import ardents.in.avaz.Activity.MainActivity;
import ardents.in.avaz.Network.RetrofitClient;
import ardents.in.avaz.models.RegisterModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterRepo {
    MutableLiveData<String> registerresult;

    public RegisterRepo(Application application) {
        registerresult=new MutableLiveData<>();
    }

    public MutableLiveData<String> register(Context context,String name,String mail,String passwd,String age){

        RetrofitClient.getClient().register(name,mail,passwd,age).enqueue(new Callback<RegisterModel>() {
            @Override
            public void onResponse(Call<RegisterModel> call, Response<RegisterModel> response) {
                try {
                    if (response.isSuccessful()){
                        RegisterModel registerModel=response.body();
                        if (registerModel!=null){
                            Log.d("register","register"+registerModel.getMessage());
                            Toast.makeText(context, registerModel.getMessage(), Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(context, LoginActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(intent);
                        }
                    }else {
                        Toast.makeText(context, "This email already has been taken", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Log.d("register","Exception======"+e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<RegisterModel> call, Throwable t) {
                Log.d("register","failed======"+t.getMessage());
            }
        });

        return registerresult;
    }
}
