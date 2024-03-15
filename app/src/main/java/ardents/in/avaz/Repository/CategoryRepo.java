package ardents.in.avaz.Repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import ardents.in.avaz.Network.RetrofitClient;
import ardents.in.avaz.models.CategoryModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryRepo {
    MutableLiveData<List<CategoryModel>> allCategoriesList;
    List<CategoryModel> categoryList;

    public CategoryRepo(Application application) {
        allCategoriesList=new MutableLiveData<>();
        categoryList=new ArrayList<>();
    }

    public  MutableLiveData<List<CategoryModel>> category(){
        RetrofitClient.getClient().category().enqueue(new Callback<CategoryModel>() {
            @Override
            public void onResponse(Call<CategoryModel> call, Response<CategoryModel> response) {
                try{
                    Log.d("categorydata","successful"+response.body());
                    if (response.isSuccessful()){
                        Log.d("categorydata","successful"+response.body());
                        CategoryModel categoryModel=response.body();
                        Log.d("categorydata","CategoryModel=="+categoryModel.getName());
                        //allCategoriesList.postValue();
                    }else {
                        Log.d("categorydata","api calling unsuccessful");
                    }
                }catch (Exception e){
                    Log.d("categorydata","Exception=="+e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<CategoryModel> call, Throwable t) {
                Log.d("categorydata","Failed=="+t.getMessage());
            }
        });
        return allCategoriesList;
    }
}
