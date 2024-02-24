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
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterRepo {
    MutableLiveData<String> registerresult;

    public RegisterRepo(Application application) {
        registerresult=new MutableLiveData<>();
    }

    public MutableLiveData<String> register(Context context,String name,String mail,String passwd,String age){
        RetrofitClient.getClient().register(name,mail,passwd,age).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                try{
                    if (response.isSuccessful()){
                        Log.d("register","Successful======"+response.body().toString());
                        JsonObject jsonObject=response.body();
                        String message=jsonObject.get("message").getAsString();
                        Log.d("register","register"+message);
                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(context, LoginActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);

                    }else {
                        //Log.d("register","Unsuccessful======"+response.errorBody().toString());
//                        JsonObject jsonObject=response.body();
//                        String error=jsonObject.get("error").getAsString();
//                        Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
                        Toast.makeText(context, "This email already has been taken", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Log.d("register","Exception======"+e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.d("register","failed======"+t.getMessage());

            }
        });
        return registerresult;
    }
}
