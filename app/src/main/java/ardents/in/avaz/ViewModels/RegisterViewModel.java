package ardents.in.avaz.ViewModels;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import ardents.in.avaz.Repository.RegisterRepo;

public class RegisterViewModel extends AndroidViewModel {
    private RegisterRepo registerRepo;
    public RegisterViewModel(@NonNull Application application) {
        super(application);
        registerRepo=new RegisterRepo(application);
    }
    public MutableLiveData<String> registerUser(Context context, String name,String mail,String passwd,String age){
        return registerRepo.register(context,name,mail,passwd,age);
    }
}
