package com.dagger_2_mvvm.repositories;

import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.dagger_2_mvvm.AppClass;
import com.dagger_2_mvvm.models.entity.CategoryModel;
import com.dagger_2_mvvm.models.entity.RecipeDetailModel;
import com.dagger_2_mvvm.models.entity.RecipeModel;
import com.dagger_2_mvvm.models.states.RecipeServiceInterface;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecipeRepository {

    //Categories list Api call
    private ArrayList<CategoryModel.Categories> categories = new ArrayList<>();
    private MutableLiveData<List<CategoryModel.Categories>> mutableLiveDataCategories = new MutableLiveData<>();
    private MutableLiveData<Boolean> mutableProgress = new MutableLiveData<>();
    private static final String TAG = "RecipeRepository";
    @Inject
    RecipeServiceInterface recipeServiceInterface;

    @Inject
    RecipeRepository() {
    }

    public MutableLiveData<List<CategoryModel.Categories>> getMutableLiveDataCategories() {
        showProgress();
        recipeServiceInterface.getCategories().enqueue(new Callback<CategoryModel>() {
            @Override
            public void onResponse(@NonNull Call<CategoryModel> call, @NonNull Response<CategoryModel> response) {
                hideProgress();
                Log.e(TAG, call.request().url().toString());
                Log.e(TAG, new Gson().toJson(response.body()));

                CategoryModel categoryModel = response.body();

                if (categoryModel != null && categoryModel.getCategories() != null) {

                    categories = (ArrayList<CategoryModel.Categories>) categoryModel.getCategories();
                    mutableLiveDataCategories.setValue(categories);

                }

            }

            @Override
            public void onFailure(@NonNull Call<CategoryModel> call, @NonNull Throwable t) {
                hideProgress();
                t.printStackTrace();
            }
        });


        return mutableLiveDataCategories;
    }


    //Recipes list Api call
    private ArrayList<RecipeModel.Recipe> recipes = new ArrayList<>();
    private MutableLiveData<List<RecipeModel.Recipe>> listMutableLiveDataRecipes = new MutableLiveData<>();

    public MutableLiveData<List<RecipeModel.Recipe>> getMutableLiveDataRecipesList(String categoryName) {
        showProgress();
        recipeServiceInterface.getRecipes(categoryName).enqueue(new Callback<RecipeModel>() {
            @Override
            public void onResponse(@NonNull Call<RecipeModel> call, @NonNull Response<RecipeModel> response) {

                hideProgress();
                Log.e(TAG, call.request().url().toString());
                Log.e(TAG, new Gson().toJson(response.body()));

                RecipeModel recipeModel = response.body();

                if (recipeModel != null && recipeModel.getMeals() != null) {
                    recipes = (ArrayList<RecipeModel.Recipe>) recipeModel.getMeals();
                    listMutableLiveDataRecipes.setValue(recipes);
                }
            }

            @Override
            public void onFailure(@NonNull Call<RecipeModel> call, @NonNull Throwable t) {
                hideProgress();
                t.printStackTrace();
            }
        });

        return listMutableLiveDataRecipes;
    }

    //Recipe detail call
    private List<RecipeDetailModel.Meals> meals;
    private MutableLiveData<List<RecipeDetailModel.Meals>> listMutableLiveDataMeals = new MutableLiveData<>();

    public MutableLiveData<List<RecipeDetailModel.Meals>> getListMutableLiveDataMeals(String mealId) {
        showProgress();
        recipeServiceInterface.getMeal(mealId).enqueue(new Callback<RecipeDetailModel>() {
            @Override
            public void onResponse(@NonNull Call<RecipeDetailModel> call, @NonNull Response<RecipeDetailModel> response) {

                hideProgress();
                Log.e(TAG, call.request().url().toString());
                Log.e(TAG, new Gson().toJson(response.body()));

                RecipeDetailModel recipeDetailModel = response.body();

                if (recipeDetailModel != null && recipeDetailModel.getMeals() != null) {
                    meals = recipeDetailModel.getMeals();
                    listMutableLiveDataMeals.setValue(meals);
                }

            }

            @Override
            public void onFailure(@NonNull Call<RecipeDetailModel> call, @NonNull Throwable t) {
                hideProgress();
                t.printStackTrace();
            }
        });

        return listMutableLiveDataMeals;
    }

    public MutableLiveData<Boolean> getMutableProgress() {
        return mutableProgress;
    }

    private void hideProgress() {
        mutableProgress.postValue(false);
    }

    private void showProgress() {
        mutableProgress.setValue(true);
    }

}
