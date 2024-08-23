package com.example.meals.Presenters

import com.example.meals.Data.MealDao
import com.example.meals.Data.MealItem
import com.example.meals.Network.RetrofitHelper
import com.example.meals.ViewInterfaces.FavFragmentView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavFragmentPresenter(private val view:FavFragmentView, private val dao:MealDao,var userID:String) {
    private lateinit var meals:Array<MealItem>
    suspend fun displayMeals(){
            meals = dao.getMeals(userID)

            withContext(Dispatchers.Main){
                if (meals.size == 0)
                    view.showMessage("No Meals were Added to Display")
                else
                    view.displayFavMeals(meals)
            }
        }


    suspend fun deleteMeal(meal:MealItem){
        val result = dao.deleteMeal(meal)
        meals = dao.getMeals(userID)
        withContext(Dispatchers.Main){
            if(result == 1){
                view.displayFavMeals(meals)
                view.showMessage("Deleted From Favorites")

            }


            else
                view.showMessage("error while deleting")
        }
    }
}
