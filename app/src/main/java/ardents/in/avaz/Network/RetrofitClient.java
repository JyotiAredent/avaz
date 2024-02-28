package ardents.in.avaz.Network;



import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    public static ApiServices getClient(){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://awaz.ardents.co.in/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(ApiServices.class);

    }
}
