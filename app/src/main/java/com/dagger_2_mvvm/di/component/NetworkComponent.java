package com.dagger_2_mvvm.di.component;

import com.dagger_2_mvvm.di.module.AppModule;
import com.dagger_2_mvvm.di.module.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

@Singleton
@Component(modules = {AppModule.class, NetworkModule.class})
public interface NetworkComponent {

    Retrofit provideRetrofit();

}
