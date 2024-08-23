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
import com.example.meals.Adapters.FavMealsAdapter
import com.example.meals.Adapters.ShowCategoriesAdapter
import com.example.meals.Data.MealDao
import com.example.meals.Data.MealDatabase
import com.example.meals.Data.MealItem
import com.example.meals.Presenters.CategoriesPresenter
import com.example.meals.Presenters.FavFragmentPresenter
import com.example.meals.R
import com.example.meals.ViewInterfaces.FavFragmentView
import com.example.meals.ViewInterfaces.MealDeleteButtonListener
import kotlinx.coroutines.launch


class Favorite_Fragment(var my_context:Context,var userID:String) : Fragment(),FavFragmentView,MealDeleteButtonListener {


    private lateinit var Fav_recycler_view : RecyclerView
    private lateinit var Fav_adapter: FavMealsAdapter
    private lateinit var Fav_presenter: FavFragmentPresenter

    private fun setupPresenter(){
        val dao = MealDatabase.getInstance(my_context).MealDao()
        Fav_presenter = FavFragmentPresenter(this,dao,userID)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupPresenter()
        Fav_adapter = FavMealsAdapter(arrayOf(),this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        Fav_recycler_view = view.findViewById(R.id.FavMealsRecyclerView)

        Fav_recycler_view.layoutManager = LinearLayoutManager(context,
            LinearLayoutManager.VERTICAL,false)


        Fav_recycler_view.adapter = Fav_adapter


        lifecycleScope.launch {
            Fav_presenter.displayMeals()
        }
    }

    override fun displayFavMeals(meals: Array<MealItem>) {
        Fav_adapter.dataofMeals = meals
        Fav_adapter.notifyDataSetChanged()
    }

    override fun showMessage(str: String) {
        Toast.makeText(my_context,str,Toast.LENGTH_LONG).show()
    }

    override fun onMealDeleteClick(meal: MealItem) {
        lifecycleScope.launch {
            Fav_presenter.deleteMeal(meal)
        }
    }
}