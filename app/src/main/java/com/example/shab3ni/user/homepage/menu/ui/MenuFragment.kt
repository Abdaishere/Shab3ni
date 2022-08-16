package com.example.shab3ni.user.homepage.menu.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shab3ni.R
import com.example.shab3ni.user.homepage.menu.data.Meal
import com.maximeroussy.invitrode.WordGenerator

class MenuFragment : Fragment(R.layout.fragment_menu), MealAdapter.OnMealListener {

    private var mealImg: ImageView? = null
    private var mealName: TextView? = null
    private var mealPrice: TextView? = null
    private var adapter: MealAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mealImg = view.findViewById(R.id.iv_mealImg)
        mealName = view.findViewById(R.id.tv_mealName)
        mealPrice = view.findViewById(R.id.tv_mealPrice)

        val rvMeals: RecyclerView = view.findViewById(R.id.rv_menu)
        val layoutManager = GridLayoutManager(this.context, 2)
        adapter = MealAdapter(getMeals(), this)
        rvMeals.adapter = adapter
        rvMeals.layoutManager = layoutManager


    }

    fun getMeals(): List<Meal>{
        val generator = WordGenerator();

        val meals = List<Meal>(100){
            val randPrice = (50..300).random().toDouble()
            val randImgSize = (100..200).random()
            val mealName = generator.newWord((5..10).random())

            val mealDesc = List((10..15).random()){
                generator.newWord((3..7).random())
            }.joinToString(" ")

            val imgUrl = "https://picsum.photos/$randImgSize"

            Meal(name = mealName, description= mealDesc, price = randPrice, image = imgUrl)
        }

        return meals
    }

    override fun onMealClicked(position: Int) {
        val bundle = Bundle()
        bundle.putParcelable("adapter", adapter)
        bundle.putInt("meal position", position)

        parentFragment?.parentFragmentManager?.commit {
            setReorderingAllowed(true)
            replace<MealDetailsFragment>(R.id.main_fragment_container, args = bundle)
            addToBackStack(null)
        }
    }
}