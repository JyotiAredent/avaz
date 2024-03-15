package ardents.in.avaz.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import ardents.in.avaz.Repository.CategoryRepo;
import ardents.in.avaz.models.CategoryModel;

public class CategoryViewModel extends AndroidViewModel {
    private CategoryRepo categoryRepo;
    public CategoryViewModel(@NonNull Application application) {
        super(application);
        categoryRepo=new CategoryRepo(application);
    }
    public MutableLiveData<List<CategoryModel>> categoryresult(){
        return categoryRepo.category();
    }
}
