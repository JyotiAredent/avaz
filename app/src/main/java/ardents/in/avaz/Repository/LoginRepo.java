package ardents.in.avaz.Repository;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.google.gson.JsonObject;

import ardents.in.avaz.Activity.MainActivity;
import ardents.in.avaz.Network.RetrofitClient;
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
        RetrofitClient.getClient().login(mail,password).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                try {
                    if (response.isSuccessful()){
                        Log.d("login","successful==="+response.body().toString());
                        JsonObject jsonObject=response.body();
                        String token=jsonObject.get("token").getAsString();
                        Log.d("login","token==="+token);
                        if (token!=null){
                            Intent intent = new Intent(context, MainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(intent);
                        }
//                        else {
//                            JsonObject jsonObject1=response.body();
//                            String error=jsonObject1.get("error").getAsString();
//                            Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
//                        }

                    }else {
//                        JsonObject jsonObject1=response.body();
//                        String error=jsonObject1.get("error").getAsString();
//                        Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
                        Toast.makeText(context, "Unauthorised", Toast.LENGTH_SHORT).show();
                       // Log.d("login","unsuccessful==="+response.errorBody().toString());
                    }
                }catch (Exception e){
                    Log.d("login","Exception==="+e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.d("login","Failed==="+t.getMessage());

            }
        });

        return loginresult;
    }
}
