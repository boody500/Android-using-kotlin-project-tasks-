package com.example.meals.Presenters

import com.example.meals.Data.MealDao
import com.example.meals.Data.MealItem
import com.example.meals.Data.MealItems
import com.example.meals.Network.RetrofitHelper
import com.example.meals.ViewInterfaces.CountryItems
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CountryItemsPresenter(private val view: CountryItems, private val dao:MealDao)
{
    suspend fun getCountryItems(country_name:String){
        GlobalScope.launch (Dispatchers.IO){

            val items = RetrofitHelper.refrofitService.ListCountryItems(country_name)
            val itemsDetailed = MealItems()
            for (i in 0..<items.meals.size){
                val temp = RetrofitHelper.refrofitService.ListFullMeal(items.meals[i].idMeal)
                itemsDetailed.meals.add(temp.meals[0])
                withContext(Dispatchers.Main){
                    view.displayItems(itemsDetailed)
                }
            }


        }
    }

    suspend fun addMeal(meal: MealItem,userID:String){
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