package com.example.meals.Presenters

import com.example.meals.Data.MealDao
import com.example.meals.Data.MealItem
import com.example.meals.ViewInterfaces.MealInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MealInfoPresenter(private val view:MealInfo, private val dao: MealDao, var userID:String)
{
    suspend fun addMeal(meal: MealItem, userID:String){
        meal.userID = userID
        val result = dao.addMeal(meal)
        withContext(Dispatchers.Main){
            if (result > 0)
                view.showMessage("Added to Favorites")

            else
                view.showMessage("Meal is already in Favorites")

        }
    }
}