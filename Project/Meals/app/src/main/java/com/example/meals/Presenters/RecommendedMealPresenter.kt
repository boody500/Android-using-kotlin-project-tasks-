package com.example.meals.Presenters

import com.example.meals.Data.MealDao
import com.example.meals.Data.MealItem
import com.example.meals.Data.MealItems
import com.example.meals.Network.RetrofitHelper
import com.example.meals.ViewInterfaces.RecommendedMeal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RecommendedMealPresenter(private var view:RecommendedMeal, private val dao:MealDao,userID:String) {

    suspend fun getRandomMeal() {
        GlobalScope.launch(Dispatchers.IO) {
            val items = RetrofitHelper.refrofitService.getRandomMeal()
            withContext(Dispatchers.Main) {
                view.displayItem(items.meals[0])
            }
        }


    }


    suspend fun addMeal(meal: MealItem, userID: String) {
        meal.userID = userID
        val result = dao.addMeal(meal)
        withContext(Dispatchers.Main) {
            if (result > 0)
                view.showMessage("Added to Favorites")
            else
                view.showMessage("Meal is already in Favorites")
        }
    }
}