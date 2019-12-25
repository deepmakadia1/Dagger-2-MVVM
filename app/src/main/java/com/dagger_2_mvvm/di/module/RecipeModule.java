package com.dagger_2_mvvm.di.module;

import com.dagger_2_mvvm.di.scope.UserScope;
import com.dagger_2_mvvm.models.states.RecipeServiceInterface;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class RecipeModule {

    @UserScope
    @Provides
    RecipeServiceInterface provideRecipeService(Retrofit retrofit){
        return retrofit.create(RecipeServiceInterface.class);
    }

}
