package com.example.meals.Presenters

import android.util.Log
import com.example.meals.Network.RetrofitHelper
import com.example.meals.ViewInterfaces.Categories
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

import kotlinx.coroutines.withContext

class CategoriesPresenter(private var view:Categories)
{
    suspend fun getCategories(){
        GlobalScope.launch(Dispatchers.IO) {
            val categories = RetrofitHelper.refrofitService.ListCategories()
            withContext(Dispatchers.Main){
                view.displayCategories(categories)
            }
        }

    }


}