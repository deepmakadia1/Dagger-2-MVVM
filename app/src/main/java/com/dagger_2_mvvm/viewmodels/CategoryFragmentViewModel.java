package com.dagger_2_mvvm.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.dagger_2_mvvm.AppClass;
import com.dagger_2_mvvm.models.entity.RecipeModel;
import com.dagger_2_mvvm.repositories.RecipeRepository;

import java.util.List;

public class CategoryFragmentViewModel extends AndroidViewModel {

    private RecipeRepository recipeRepository;

    public CategoryFragmentViewModel(@NonNull Application application) {
        super(application);
        recipeRepository = AppClass.getInstance().getServiceComponent().getRecipeRepository();
    }

    public LiveData<List<RecipeModel.Recipe>> getRecipeList(String categoryName){
        return recipeRepository.getMutableLiveDataRecipesList(categoryName);
    }

    public LiveData<Boolean> getProgress(){
        return recipeRepository.getMutableProgress();
    }

}
