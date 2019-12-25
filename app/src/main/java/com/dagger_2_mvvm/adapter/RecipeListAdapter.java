package com.dagger_2_mvvm.adapter;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dagger_2_mvvm.R;
import com.dagger_2_mvvm.databinding.ItemRecipeBinding;
import com.dagger_2_mvvm.models.entity.RecipeModel;
import com.dagger_2_mvvm.ui.activity.DetailRecipeActivity;
import com.dagger_2_mvvm.util.Constants;

import java.util.List;

public class RecipeListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<RecipeModel.Recipe> meals;
    private Context context;

    public RecipeListAdapter(Context context, List<RecipeModel.Recipe> meals) {
        this.context = context;
        this.meals = meals;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_recipe, viewGroup, false);
        return new RecipeHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        RecipeHolder recipeHolder = (RecipeHolder) viewHolder;

        recipeHolder.itemRecipeBinding.setRecipe(meals.get(i));

    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    private class RecipeHolder extends RecyclerView.ViewHolder {

        ItemRecipeBinding itemRecipeBinding;

        RecipeHolder(@NonNull View itemView) {
            super(itemView);
            itemRecipeBinding = DataBindingUtil.bind(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startActivity(new Intent(context,DetailRecipeActivity.class).putExtra(Constants.MEAL_ID,meals.get(getAdapterPosition()).getIdMeal()));
                }
            });
        }
    }

}
