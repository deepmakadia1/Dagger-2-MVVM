package com.dagger_2_mvvm.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.dagger_2_mvvm.AppClass;
import com.dagger_2_mvvm.models.entity.CategoryModel;
import com.dagger_2_mvvm.repositories.RecipeRepository;

import java.util.List;



public class MainActivityViewModel extends AndroidViewModel {

    private RecipeRepository recipeRepository;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        recipeRepository = AppClass.getInstance().getServiceComponent().getRecipeRepository();
    }

    public LiveData<List<CategoryModel.Categories>> getCategories(){
        return recipeRepository.getMutableLiveDataCategories();
    }

    public LiveData<Boolean> getProgress(){
        return recipeRepository.getMutableProgress();
    }

}
