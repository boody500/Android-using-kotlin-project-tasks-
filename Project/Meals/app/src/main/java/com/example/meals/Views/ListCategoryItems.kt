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
import com.example.meals.Adapters.ListCategoryItemsAdapter
import com.example.meals.Data.MealDatabase
import com.example.meals.Data.MealItem
import com.example.meals.Data.MealItems
import com.example.meals.Presenters.CategoryItemsPresenter
import com.example.meals.R
import com.example.meals.ViewInterfaces.CategoryItems
import com.example.meals.ViewInterfaces.MealFavButtonListener
import kotlinx.coroutines.launch


class ListCategoryItems(var CategoryName:String,var my_context:Context,var userID:String) : Fragment(),CategoryItems,MealFavButtonListener {
    private lateinit var category_items_presenter:CategoryItemsPresenter
    private lateinit var category_items_recycler_view : RecyclerView
    private lateinit var category_items_adapter: ListCategoryItemsAdapter
    private lateinit var back:Button

    private fun setupPresenter(){
        val dao = MealDatabase.getInstance(my_context).MealDao()
        category_items_presenter = CategoryItemsPresenter(this,dao)

        category_items_adapter = ListCategoryItemsAdapter(MealItems(),my_context,parentFragmentManager,this,userID)
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
        return inflater.inflate(R.layout.fragment_list_items, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        back = view.findViewById(R.id.backbutton)
        back.setOnClickListener {
            val pf = parentFragmentManager
            val pf_transaction = pf.beginTransaction()
            pf_transaction.replace(R.id.Frame_Layout,Home_Fragment(my_context,userID))
            //pf_transaction.addToBackStack(null)
            pf_transaction.commit()

        }


        category_items_recycler_view = view.findViewById(R.id.ListCountryItems_View)

        category_items_recycler_view.layoutManager = LinearLayoutManager(context,
            LinearLayoutManager.VERTICAL,false)


        category_items_recycler_view.adapter = category_items_adapter


        lifecycleScope.launch {
            category_items_presenter.getCategoryItems(CategoryName)
        }
    }

    override fun displayItems(Items: MealItems) {
        category_items_adapter.dataofMealCategoryItems = Items
        category_items_adapter.notifyDataSetChanged()
    }

    override fun onMealFavClick(meal: MealItem) {
        lifecycleScope.launch {
            category_items_presenter.addMeal(meal,userID)
        }
    }

    override fun showMessage(str: String) {
        Toast.makeText(my_context,str,Toast.LENGTH_SHORT).show()
    }
}