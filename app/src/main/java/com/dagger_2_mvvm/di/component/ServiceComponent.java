package com.dagger_2_mvvm.di.component;


import com.dagger_2_mvvm.di.module.RecipeModule;
import com.dagger_2_mvvm.di.scope.UserScope;
import com.dagger_2_mvvm.repositories.RecipeRepository;
import com.dagger_2_mvvm.viewmodels.CategoryFragmentViewModel;
import com.dagger_2_mvvm.viewmodels.DetailRecipeActivityViewModel;


import dagger.Component;

@UserScope
@Component(dependencies = NetworkComponent.class, modules = {RecipeModule.class})
public interface ServiceComponent {

    RecipeRepository getRecipeRepository();

}
