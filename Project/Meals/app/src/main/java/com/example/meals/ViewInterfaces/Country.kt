package com.example.meals.ViewInterfaces

import com.example.meals.Data.MealCategories
import com.example.meals.Data.MealCountries

interface Country {
    fun displayCountries(countries: MealCountries)
}