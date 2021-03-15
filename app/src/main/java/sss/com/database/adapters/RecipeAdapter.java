package sss.com.database.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import sss.com.database.R;
import sss.com.database.models.Result;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeViewHolder> {

    private List<Result> results;

    public void setList(List<Result> results) {
        this.results = results;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //create view list container
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe, parent, false);
        return new RecipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
        holder.onBind(results.get(position));
    }

    @Override
    public int getItemCount() {
        return results.size();
    }
}
