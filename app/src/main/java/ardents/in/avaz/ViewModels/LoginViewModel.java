package ardents.in.avaz.ViewModels;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import ardents.in.avaz.Repository.LoginRepo;

public class LoginViewModel extends AndroidViewModel {
    private LoginRepo loginRepo;
    public LoginViewModel(@NonNull Application application) {
        super(application);
        loginRepo=new LoginRepo(application);
    }
    public MutableLiveData<String> loginUser(Context context,String mail,String password){
        return loginRepo.login(context,mail,password);
    }
}
