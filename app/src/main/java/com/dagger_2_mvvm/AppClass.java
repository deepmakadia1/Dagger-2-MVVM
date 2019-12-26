package com.dagger_2_mvvm;

import android.app.Application;

import com.dagger_2_mvvm.di.component.DaggerNetworkComponent;
import com.dagger_2_mvvm.di.component.DaggerServiceComponent;
import com.dagger_2_mvvm.di.component.NetworkComponent;
import com.dagger_2_mvvm.di.component.ServiceComponent;
import com.dagger_2_mvvm.di.module.RecipeModule;
import com.dagger_2_mvvm.di.module.NetworkModule;
import com.dagger_2_mvvm.util.Constants;

public class AppClass extends Application {

    private ServiceComponent serviceComponent;
    public static AppClass appClass;

    @Override
    public void onCreate() {
        super.onCreate();
        appClass = this;
        NetworkComponent networkComponent = DaggerNetworkComponent.builder()
                .networkModule(new NetworkModule(Constants.BASE_URL))
                .build();

        serviceComponent = DaggerServiceComponent.builder()
                .networkComponent(networkComponent)
                .recipeModule(new RecipeModule())
                .build();

    }

    public static AppClass getInstance() {
        if (appClass == null) {
            appClass = new AppClass();
        }
        return appClass;
    }

    public ServiceComponent getServiceComponent() {
        return serviceComponent;
    }

}
