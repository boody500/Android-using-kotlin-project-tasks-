package com.example.meals.Network

import com.example.meals.Data.MealCategories
import com.example.meals.Data.MealCountries
import com.example.meals.Data.MealItem
import com.example.meals.Data.MealItems
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Services {
    @GET("categories.php")
    suspend fun ListCategories():MealCategories

    @GET("filter.php")
    suspend fun ListCategoryItems(@Query("c") Category:String):MealItems

    @GET("list.php")
    suspend fun ListCountries(@Query("a") Country:String):MealCountries

    @GET("filter.php")
    suspend fun ListCountryItems(@Query("a") Country: String): MealItems

    @GET("lookup.php")
    suspend fun ListFullMeal(@Query("i") MealID:String): MealItems


    @GET("filter.php")
    suspend fun SearchByIngradiant(@Query("i") ingradiant:String):MealItems

    @GET("random.php")
    suspend fun getRandomMeal():MealItems
}