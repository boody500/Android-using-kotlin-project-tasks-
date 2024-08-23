package com.example.meals.Presenters

import android.view.View
import com.example.meals.Network.RetrofitHelper
import com.example.meals.ViewInterfaces.Country
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CountryPresenter(private var view:Country)
{
    suspend fun getCountries(){
        GlobalScope.launch (Dispatchers.IO){

            val countries = RetrofitHelper.refrofitService.ListCountries("list")
            withContext(Dispatchers.Main){
                view.displayCountries(countries)
            }
        }
    }


}