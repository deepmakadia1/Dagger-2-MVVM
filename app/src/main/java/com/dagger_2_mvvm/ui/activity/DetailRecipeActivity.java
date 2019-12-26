package com.dagger_2_mvvm.ui.activity;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.dagger_2_mvvm.R;
import com.dagger_2_mvvm.databinding.ActivityDetailRecipeBinding;
import com.dagger_2_mvvm.models.entity.RecipeDetailModel;
import com.dagger_2_mvvm.util.Constants;
import com.dagger_2_mvvm.viewmodels.DetailRecipeActivityViewModel;

import java.util.List;

public class DetailRecipeActivity extends AppCompatActivity {

    private ActivityDetailRecipeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Activity activity = this;
        binding = DataBindingUtil.setContentView(activity, R.layout.activity_detail_recipe);

        DetailRecipeActivityViewModel detailRecipeActivityViewModel = ViewModelProviders.of(this).get(DetailRecipeActivityViewModel.class);

        detailRecipeActivityViewModel.getProgress().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                if (aBoolean != null && aBoolean) {
                    binding.scrollView.setVisibility(View.GONE);
                    binding.progress.setVisibility(View.VISIBLE);
                } else {
                    binding.scrollView.setVisibility(View.VISIBLE);
                    binding.progress.setVisibility(View.GONE);
                }
            }
        });

        detailRecipeActivityViewModel.getMeals(getIntent().getStringExtra(Constants.MEAL_ID)).observe(this, new Observer<List<RecipeDetailModel.Meals>>() {
            @Override
            public void onChanged(@Nullable List<RecipeDetailModel.Meals> meals) {
                if (meals != null) {
                    binding.setMeal(meals.get(0));
                }
            }
        });


    }
}
