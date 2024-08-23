package com.example.meals.Views

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.meals.Adapters.RecommednedMealAdapter
import com.example.meals.Adapters.ShowCategoriesAdapter
import com.example.meals.Adapters.ShowCountriesAdapter
import com.example.meals.Data.MealCategories
import com.example.meals.Data.MealCountries
import com.example.meals.Data.MealDao
import com.example.meals.Data.MealDatabase
import com.example.meals.Data.MealItem
import com.example.meals.Data.MealItems
import com.example.meals.Presenters.CategoriesPresenter
import com.example.meals.Presenters.CountryPresenter
import com.example.meals.Presenters.RecommendedMealPresenter
import com.example.meals.R
import com.example.meals.ViewInterfaces.Categories
import com.example.meals.ViewInterfaces.Country
import com.example.meals.ViewInterfaces.MealFavButtonListener
import com.example.meals.ViewInterfaces.RecommendedMeal
import kotlinx.coroutines.launch

class Home_Fragment(var my_context: Context,var userID:String) : Fragment(),Categories, Country,RecommendedMeal,MealFavButtonListener {

    //for categories
    private lateinit var categories_recycler_view :RecyclerView
    private lateinit var categories_adapter:ShowCategoriesAdapter
    private lateinit var categories_presenter:CategoriesPresenter


    //for countries
    private lateinit var countries_recycler_view :RecyclerView
    private lateinit var countries_adapter:ShowCountriesAdapter
    private lateinit var countries_presenter:CountryPresenter


    //for random meal
    private lateinit var random_recycler_view :RecyclerView
    private lateinit var random_adapter:RecommednedMealAdapter
    private lateinit var random_presenter:RecommendedMealPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        categories_presenter = CategoriesPresenter(this)
        categories_adapter = ShowCategoriesAdapter(MealCategories(arrayListOf()),parentFragmentManager,my_context,userID)

        countries_presenter = CountryPresenter(this)
        countries_adapter = ShowCountriesAdapter(MealCountries(arrayListOf()),parentFragmentManager,my_context,userID)

        val dao = MealDatabase.getInstance(my_context).MealDao()
        random_presenter = RecommendedMealPresenter(this,dao,userID)
        random_adapter = RecommednedMealAdapter(MealItem("",""),parentFragmentManager,my_context,this,userID)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //for categories
        categories_recycler_view = view.findViewById(R.id.CategoriesRecyclerView)
        categories_recycler_view.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        categories_recycler_view.adapter = categories_adapter

        //for countries
        countries_recycler_view = view.findViewById(R.id.CountryRecyclerView)
        countries_recycler_view.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        countries_recycler_view.adapter = countries_adapter

        //for random meal
        random_recycler_view = view.findViewById(R.id.RecommendedMealRecyclerView)
        random_recycler_view.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        random_recycler_view.adapter = random_adapter


        lifecycleScope.launch {
            categories_presenter.getCategories()
            countries_presenter.getCountries()
            random_presenter.getRandomMeal()
        }
    }





    //for categories
    override fun displayCategories(categories: MealCategories) {
        categories_adapter.dataofCategories = categories
        categories_adapter.notifyDataSetChanged()
    }

    //for countries
    override fun displayCountries(countries: MealCountries) {
        countries_adapter.dataofCountries = countries
        countries_adapter.notifyDataSetChanged()
    }


    //for random meal
    override fun displayItem(Item: MealItem) {
        random_adapter.meal = Item
        random_adapter.notifyDataSetChanged()
    }

    override fun showMessage(str: String) {
        Toast.makeText(context,str,Toast.LENGTH_SHORT).show()
    }

    override fun onMealFavClick(meal: MealItem) {
        lifecycleScope.launch {
            random_presenter.addMeal(meal,userID)

        }
    }


}