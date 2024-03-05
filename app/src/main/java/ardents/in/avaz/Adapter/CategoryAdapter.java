package ardents.in.avaz.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import ardents.in.avaz.databinding.CategoryLayBinding;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    Context context;
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

    }

    @Override
    public int getItemCount() {
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
