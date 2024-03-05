package ardents.in.avaz.ViewModels;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import ardents.in.avaz.Repository.ForgotPasswordRepo;

public class ForgotPasswordViewModel extends AndroidViewModel {
    private ForgotPasswordRepo forgotPasswordRepo;
    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>();

    public LiveData<Boolean> getIsLoading() {
        return isLoading;
    }
    public ForgotPasswordViewModel(@NonNull Application application) {
        super(application);
        forgotPasswordRepo=new ForgotPasswordRepo(application);
    }
    public MutableLiveData<String> forgotPassUser(Context context,String mail){
        isLoading.setValue(true);
        return forgotPasswordRepo.forgotPassword(context,mail);
    }
}
