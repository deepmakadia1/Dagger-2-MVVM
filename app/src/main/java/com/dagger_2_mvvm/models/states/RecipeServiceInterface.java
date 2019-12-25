package com.dagger_2_mvvm.models.states;


import com.dagger_2_mvvm.models.entity.CategoryModel;
import com.dagger_2_mvvm.models.entity.RecipeDetailModel;
import com.dagger_2_mvvm.models.entity.RecipeModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RecipeServiceInterface {

    @GET("categories.php")
    Call<CategoryModel> getCategories();

    @GET("filter.php")
    Call<RecipeModel> getRecipes(
            @Query("c") String categoryName
    );

    @GET("lookup.php")
    Call<RecipeDetailModel> getMeal(
            @Query("i") String mealId
    );

}
