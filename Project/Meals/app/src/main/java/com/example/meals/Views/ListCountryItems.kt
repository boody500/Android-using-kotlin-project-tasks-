package com.example.meals.Views

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.meals.Adapters.ListCountryItemsAdapter
import com.example.meals.Data.MealDatabase
import com.example.meals.Data.MealItem
import com.example.meals.Data.MealItems
import com.example.meals.Presenters.CountryItemsPresenter
import com.example.meals.R
import com.example.meals.ViewInterfaces.CountryItems
import com.example.meals.ViewInterfaces.MealFavButtonListener
import kotlinx.coroutines.launch


class ListCountryItems(var CountryName:String,var my_context: Context,var userID:String) : Fragment(),CountryItems,MealFavButtonListener {

    private lateinit var country_items_presenter: CountryItemsPresenter
    private lateinit var country_items_recycler_view : RecyclerView
    private lateinit var country_items_adapter: ListCountryItemsAdapter
    private lateinit var back: Button


    private fun setupPresenter(){
        val dao = MealDatabase.getInstance(my_context).MealDao()
        country_items_presenter = CountryItemsPresenter(this,dao)

        country_items_adapter = ListCountryItemsAdapter(MealItems(),my_context,parentFragmentManager,this,userID)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupPresenter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_country_items, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        back = view.findViewById(R.id.backbutton)
        back.setOnClickListener {
            val pf1 = parentFragmentManager

            val pf_transaction1 = pf1.beginTransaction()
            pf_transaction1.replace(R.id.Frame_Layout,Home_Fragment(my_context,userID)).commit()
        }


        country_items_recycler_view = view.findViewById(R.id.ListCountryItems_View)

        country_items_recycler_view.layoutManager = LinearLayoutManager(context,
            LinearLayoutManager.VERTICAL,false)


        country_items_recycler_view.adapter = country_items_adapter


        lifecycleScope.launch {
            country_items_presenter.getCountryItems(CountryName)
        }
    }

    override fun displayItems(Items: MealItems) {
        country_items_adapter.dataofMealCountryItems = Items
        country_items_adapter.notifyDataSetChanged()
    }

    override fun onMealFavClick(meal: MealItem) {
        meal.userID = userID
        lifecycleScope.launch {
            country_items_presenter.addMeal(meal,userID)

        }
    }

    override fun showMessage(str: String) {
        Toast.makeText(my_context,str, Toast.LENGTH_SHORT).show()

    }

}