package com.atilsamancioglu.cryptocrazycompose.di

import MealRepo
import com.atilsamancioglu.cryptocrazycompose.servis.MealApi
import com.atilsamancioglu.cryptocrazycompose.util.Consteans
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providesMealRepo(api: MealApi)=MealRepo(api)

    @Singleton
    @Provides
    fun providesCategoryMealList():MealApi{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Consteans.BASE_URL).build().create(MealApi::class.java)
    }
}