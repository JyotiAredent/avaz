package ardents.in.avaz.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import ardents.in.avaz.databinding.CategoryLayBinding;
import ardents.in.avaz.models.CategoryModel;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    Context context;
    List<CategoryModel> categoryModelList;

    public CategoryAdapter(Context context, List<CategoryModel> categoryModelList) {
        this.context = context;
        this.categoryModelList = categoryModelList;
    }
    public void updateCategoryList(List<CategoryModel> list){
        this.categoryModelList=list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        CategoryLayBinding binding=CategoryLayBinding.inflate(layoutInflater,parent,false);
        ViewHolder viewHolder=new ViewHolder(binding);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding.categoryName.setText(categoryModelList.get(position).getName());
        Glide.with(context).load(categoryModelList.get(position).getImage()).into(holder.binding.categoryImg);

    }

    @Override
    public int getItemCount() {
        if (this.categoryModelList!=null){
            return this.categoryModelList.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CategoryLayBinding binding;
        public ViewHolder(CategoryLayBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }
}
