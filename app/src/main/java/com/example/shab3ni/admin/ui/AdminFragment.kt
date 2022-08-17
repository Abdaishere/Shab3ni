package com.example.shab3ni.admin.ui

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
import com.example.shab3ni.user.homepage.menu.data.Product
import com.example.shab3ni.user.homepage.menu.ui.ProductAdapter
import com.maximeroussy.invitrode.WordGenerator


class AdminFragment : Fragment(R.layout.fragment_admin), ProductAdapter.OnMealListener {

    private var mealImg: ImageView? = null
    private var mealName: TextView? = null
    private var mealPrice: TextView? = null
    private var adapter: ProductAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mealImg = view.findViewById(R.id.iv_mealImg)
        mealName = view.findViewById(R.id.tv_mealName)
        mealPrice = view.findViewById(R.id.tv_mealPrice)

        val rvMeals: RecyclerView = view.findViewById(R.id.rv_adminMealsList)
        val layoutManager = GridLayoutManager(this.context, 2)
        adapter = ProductAdapter(dummyProducts(), this)
        rvMeals.adapter = adapter
        rvMeals.layoutManager = layoutManager


    }

    private fun dummyProducts(): List<Product>{
        val generator = WordGenerator()

        val products = List(100){
            val randPrice = (50..300).random().toFloat()
            val randImgSize = (100..200).random()
            val mealName = generator.newWord((5..10).random())

            val mealDesc = List((10..15).random()){
                generator.newWord((3..7).random())
            }.joinToString(" ")

            val imgUrl = "https://picsum.photos/$randImgSize"

            Product(name = mealName, description= mealDesc, price = randPrice, imageurl = imgUrl)
        }

        return products
    }

    override fun onMealClicked(position: Int) {
        val bundle = Bundle()
        bundle.putParcelable("adapter", adapter)
        bundle.putInt("meal position", position)

        parentFragmentManager.commit {
            setReorderingAllowed(true)
            replace<AdminMealDetailsFragment>(R.id.main_fragment_container, args = bundle)
            addToBackStack(null)
        }
    }
}