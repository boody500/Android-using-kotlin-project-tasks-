package com.example.meals.ViewInterfaces

import com.example.meals.Data.MealItems

interface Search {
    fun displayItems(Items: MealItems)
    fun showMessage(str:String)
}