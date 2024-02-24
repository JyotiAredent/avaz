package ardents.in.avaz.Network;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiServices {
    @POST("register")
    Call<JsonObject> register(
            @Query("name") String name,
            @Query("email") String email,
            @Query("password") String password,
            @Query("age") String age
    );
    @POST("login")
    Call<JsonObject> login(
            @Query("email") String email,
            @Query("password") String password
    );

}
