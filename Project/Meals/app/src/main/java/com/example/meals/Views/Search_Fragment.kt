package com.example.meals.Views

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.meals.Adapters.SearchAdapter
import com.example.meals.Data.MealDatabase
import com.example.meals.Data.MealItem
import com.example.meals.Data.MealItems
import com.example.meals.Presenters.SearchPresenter
import com.example.meals.R
import com.example.meals.ViewInterfaces.MealFavButtonListener
import com.example.meals.ViewInterfaces.Search
import kotlinx.coroutines.launch


class Search_Fragment(var my_context:Context,var userID:String) : Fragment(), Search,MealFavButtonListener{

    private lateinit var my_adapter :SearchAdapter
    private lateinit var my_view:RecyclerView
    private lateinit var my_presenter: SearchPresenter
    private lateinit var search: SearchView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dao = MealDatabase.getInstance(my_context).MealDao()
        my_presenter = SearchPresenter(this,dao,userID)
        my_adapter = SearchAdapter(MealItems(),my_context,parentFragmentManager,this,userID)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        my_view = view.findViewById(R.id.SearchRecyclerView)
        my_view.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        my_view.adapter = my_adapter

        search = view.findViewById(R.id.searchView)


        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                lifecycleScope.launch {
                    Log.i("TAG", "onViewCreated: ${search.query}")
                    my_presenter.getItems(search.query.toString())
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })




    }

    override fun onMealFavClick(meal: MealItem) {
        lifecycleScope.launch {
            my_presenter.addMeal(meal,userID)

        }
    }

    override fun displayItems(Items: MealItems) {
        my_adapter.dataofMealItems = Items
        my_adapter.notifyDataSetChanged()
    }

    override fun showMessage(str: String) {
        Toast.makeText(context,str,Toast.LENGTH_SHORT).show()
    }

}