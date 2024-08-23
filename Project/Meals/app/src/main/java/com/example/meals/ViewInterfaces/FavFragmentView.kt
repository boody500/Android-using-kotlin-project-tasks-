package com.example.meals.ViewInterfaces

import com.example.meals.Data.MealItem

interface FavFragmentView
{
    fun displayFavMeals(meals:Array<MealItem>)
    fun showMessage(str:String)
}