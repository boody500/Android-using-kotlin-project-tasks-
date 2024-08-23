package com.example.meals.Presenters

import android.util.Log
import com.example.meals.Data.MealDao
import com.example.meals.Data.MealItem
import com.example.meals.Data.MealItems
import com.example.meals.Network.RetrofitHelper
import com.example.meals.ViewInterfaces.Search
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchPresenter(private var view:Search, private val dao: MealDao, var userID:String)
{
    suspend fun getItems(ingradiant_name:String){
        GlobalScope.launch (Dispatchers.IO){

            if (ingradiant_name != ""){
                val items = RetrofitHelper.refrofitService.SearchByIngradiant(ingradiant_name)
                Log.i("lll", "getItems: ${items.meals}")
                if (items.meals.isNullOrEmpty()){
                    Log.i("lll", "getItems: pop")
                    withContext(Dispatchers.Main){
                        view.showMessage("ingredient not Found")

                    }

                }
                else{
                    val itemsDetailed = MealItems()
                    for (i in 0..<items.meals.size){
                        val Meal = RetrofitHelper.refrofitService.ListFullMeal(items.meals[i].idMeal)
                        itemsDetailed.meals.add(Meal.meals[0])
                        withContext(Dispatchers.Main){
                            view.displayItems(itemsDetailed)
                        }
                    }
                }


            }


        }
    }

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