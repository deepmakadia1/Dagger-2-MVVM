package com.dagger_2_mvvm.di.module;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    private String base_url;

    public NetworkModule(String base_url) {
        this.base_url = base_url;
    }

    @Provides
    @Singleton
    GsonConverterFactory provideGsonConvertFactory(Gson gson) {
        return GsonConverterFactory.create(gson);
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(GsonConverterFactory gsonConverterFactory) {
        return new Retrofit.Builder()
                .baseUrl(base_url)
                .addConverterFactory(gsonConverterFactory)
                .build();
    }

    @Provides  // Dagger will only look for methods annotated with @Provides
    @Singleton
    Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        return gsonBuilder.serializeNulls().create();
    }
}
