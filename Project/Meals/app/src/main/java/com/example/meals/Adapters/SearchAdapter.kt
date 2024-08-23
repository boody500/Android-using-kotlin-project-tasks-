package com.example.meals.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.SearchView
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.meals.Adapters.ListCountryItemsAdapter.myviewholder
import com.example.meals.Data.MealItems
import com.example.meals.R
import com.example.meals.ViewInterfaces.MealFavButtonListener
import com.example.meals.Views.MealInfo

class SearchAdapter(var dataofMealItems: MealItems, var my_context: Context, var fragmentManager: FragmentManager, var mealListener: MealFavButtonListener, var userID:String): RecyclerView.Adapter<SearchAdapter.myviewholder>() {
    class myviewholder (var row: View) : RecyclerView.ViewHolder(row) {
        val thumbnail = row.findViewById<ImageView>(R.id.Item_thumbnail)
        val name = row.findViewById<TextView>(R.id.ItemName)
        val fav_button = row.findViewById<Button>(R.id.favbutton)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myviewholder {
        var layout = LayoutInflater.from(parent.context).inflate(R.layout.listitems,parent,false)
        return SearchAdapter.myviewholder(layout)
    }

    override fun getItemCount(): Int {
        return dataofMealItems.meals.size
    }

    override fun onBindViewHolder(holder: myviewholder, position: Int) {
        val meal = dataofMealItems.meals.get(position)
        holder.name.text = meal.strMeal.toString()

        Glide.with(holder.row.context)
            .load(meal.strMealThumb)
            .into(holder.thumbnail)



        if (userID != "guest"){
            holder.fav_button.setOnClickListener {
                mealListener.onMealFavClick(meal)

            }
        }

        else{
            holder.fav_button.visibility = View.INVISIBLE
        }

        holder.row.setOnClickListener{
            val fm = fragmentManager.beginTransaction()
            fm.replace(R.id.Frame_Layout, MealInfo(meal,my_context,userID)).commit()
        }
    }

}