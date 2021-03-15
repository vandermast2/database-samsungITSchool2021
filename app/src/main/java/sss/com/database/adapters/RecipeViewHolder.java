package sss.com.database.adapters;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import sss.com.database.R;
import sss.com.database.models.Result;

public class RecipeViewHolder extends RecyclerView.ViewHolder {
    private TextView txtTitle;
    public RecipeViewHolder(@NonNull View itemView) {
        super(itemView);

        txtTitle = itemView.findViewById(R.id.txtTitle);
    }

    public void onBind(Result recipe){
        txtTitle.setText(recipe.getTitle());
    }
}
