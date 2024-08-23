package com.example.meals.Views

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.meals.Data.MealDatabase
import com.example.meals.Data.MealItem
import com.example.meals.Presenters.MealInfoPresenter
import com.example.meals.R
import com.example.meals.ViewInterfaces.MealFavButtonListener
import com.example.meals.ViewInterfaces.MealInfo
import kotlinx.coroutines.launch


class MealInfo(var meal:MealItem,var my_context:Context,var userID:String) : Fragment() ,MealInfo,MealFavButtonListener{
    private lateinit var my_presenter:MealInfoPresenter
    private lateinit var webView:WebView
    private lateinit var ing:TextView
    private lateinit var info:TextView
    private lateinit var favbtn:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dao = MealDatabase.getInstance(my_context).MealDao()
        my_presenter = MealInfoPresenter(this,dao,userID)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_meal_info, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ing = view.findViewById(R.id.ingradiants)
        info = view.findViewById(R.id.info)
        favbtn = view.findViewById(R.id.FavButton)
        webView = view.findViewById(R.id.webView)

        if (meal.strYoutube != null){
            webView.settings.javaScriptEnabled = true
            webView.settings.domStorageEnabled = true
            webView.settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW

            webView.webViewClient = WebViewClient()
            var vidID = meal.strYoutube.toString()
            vidID = vidID.substring(vidID.indexOf('=')+1,vidID.length)
            Log.i("TAG","onViewCreated: ${vidID}")
            val iframeHtml = """
            <html>
            <body>
            <iframe width="100%" height="100%" 
                src="https://www.youtube.com/embed/${vidID}" 
                frameborder="0" 
                allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" 
                allowfullscreen>
            </iframe>
            </body>
            </html>
        """.trimIndent()

            webView.loadData(iframeHtml, "text/html", "UTF-8")
        }


        var str:String = ""
        str += (meal.strIngredient1 + ' ' + meal.strMeasure1 + ", ")
        str += (meal.strIngredient2 + ' '+meal.strMeasure2 + ", ")
        str += (meal.strIngredient3 + ' '+meal.strMeasure3 + ",\n")
        str += (meal.strIngredient4 + ' '+meal.strMeasure4 + ", ")
        str += (meal.strIngredient5 + ' '+meal.strMeasure5 + ", ")
        str += (meal.strIngredient6 + ' '+meal.strMeasure6 + ",\n")
        str += (meal.strIngredient7 + ' '+meal.strMeasure7 + ", ")
        str += (meal.strIngredient8 + ' '+meal.strMeasure8 + ", ")
        str += (meal.strIngredient9 + ' '+meal.strMeasure9 + ",\n")
        str += (meal.strIngredient10 + ' '+meal.strMeasure10)

        ing.text = str
        info.text = meal.strInstructions



        if (userID != "guest"){
            favbtn.setOnClickListener {
                onMealFavClick(meal)
            }
        }

        else{
            favbtn.visibility = View.INVISIBLE
        }
    }

    override fun showMessage(str: String) {
        Toast.makeText(my_context,str,Toast.LENGTH_SHORT).show()
    }

    override fun onMealFavClick(meal: MealItem) {
        lifecycleScope.launch{
            my_presenter.addMeal(meal,userID)
        }

    }
}