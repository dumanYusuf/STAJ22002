package com.atilsamancioglu.cryptocrazycompose.servis

import com.atilsamancioglu.cryptocrazycompose.model.MealListCategory
import com.atilsamancioglu.cryptocrazycompose.util.Resource
import retrofit2.http.GET

interface MealApi {

    //https://www.themealdb.com/api/json/v1/1/categories.php

    @GET("api/json/v1/1/categories.php")
    suspend fun getMealCategoryList():List<MealListCategory>

}