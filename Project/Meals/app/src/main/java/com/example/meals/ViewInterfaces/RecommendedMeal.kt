package com.example.meals.ViewInterfaces

import com.example.meals.Data.MealItem

interface RecommendedMeal
{
    fun displayItem(Item: MealItem)
    fun showMessage(str:String)
}