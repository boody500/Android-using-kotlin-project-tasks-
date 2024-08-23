package com.example.meals.Data

import com.google.gson.annotations.SerializedName


data class MealCountries (

    @SerializedName("meals" ) var meals : ArrayList<Country> = arrayListOf()

)

data class Country (

    @SerializedName("strArea" ) var strArea : String? = null

)