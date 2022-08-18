package com.example.shab3ni.user.homepage.editPage.ui

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.shab3ni.R
import com.example.shab3ni.user.homepage.editPage.api.adminApi
import com.example.shab3ni.user.homepage.editPage.data.CategoryAdd
import com.example.shab3ni.user.homepage.editPage.data.ProductAdd
import com.example.shab3ni.user.homepage.menu.data.Category
import com.example.shab3ni.user.homepage.menu.data.Product
import com.example.shab3ni.user.homepage.userProfile.data.CurrentUser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditPageFragment : Fragment(R.layout.additem_tab_fragment) {

    private lateinit var prodName: EditText
    private lateinit var imageurl: EditText
    private lateinit var disc: EditText
    private lateinit var price: EditText
    private lateinit var catId: EditText
    private lateinit var btnAddProd: Button
    private lateinit var evCatName: EditText
    private lateinit var btnAddCat: Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        prodName = view.findViewById(R.id.name)
        imageurl = view.findViewById(R.id.imageurl)
        disc = view.findViewById(R.id.disc)
        price = view.findViewById(R.id.price)
        catId = view.findViewById(R.id.cat_id)
        btnAddProd = view.findViewById(R.id.btn_addProd)

        btnAddProd.setOnClickListener {
            var errorCount = 0
            var error = ""
            if (prodName.text.isEmpty()) {
                errorCount++
                error = "Product Name is Empty"
            }
            if (imageurl.text.isEmpty()) {
                errorCount++
                error = "Image Link Name is Empty"
            }
            if (disc.text.isEmpty()) {
                errorCount++
                error = "Product Description is Empty"
            }
            if (price.text.isEmpty()) {
                errorCount++
                error = "Product Price is Empty"
            }
            if (catId.text.isEmpty()) {
                errorCount++
                error = "Product Category ID is Empty"
            }
            when (errorCount) {
                0 -> {
                    addProduct(
                        ProductAdd(
                            prodName.text.toString(),
                            imageurl.text.toString(),
                            price.text.toString().toInt(),
                            disc.text.toString(),
                            catId.text.toString().toInt()
                        )
                    )
                }
                1 -> {
                    Toast.makeText(context, error, Toast.LENGTH_SHORT)
                        .show()
                }
                else -> Toast.makeText(
                    context,
                    "Please Fill All The Empty Areas",
                    Toast.LENGTH_SHORT
                )
                    .show()
            }
        }

        evCatName = view.findViewById(R.id.ev_catName)
        btnAddCat = view.findViewById(R.id.btn_addCat)

        btnAddCat.setOnClickListener {

            if (evCatName.text.isNotEmpty()) {
                addCategory(CategoryAdd(evCatName.text.toString()))
            } else
                Toast.makeText(context, "Category Name is Empty", Toast.LENGTH_SHORT)
                    .show()
        }

        animation()
    }

    private fun animation() {
        // translationX
        prodName.translationX = 800f
        prodName.alpha = 0f
        prodName.animate().translationX(0f).alpha(1f).setDuration(500).setStartDelay(100)
            .start()

        imageurl.translationX = 800f
        imageurl.alpha = 0f
        imageurl.animate().translationX(0f).alpha(1f).setDuration(500).setStartDelay(200)
            .start()

        disc.translationX = 800f
        disc.alpha = 0f
        disc.animate().translationX(0f).alpha(1f).setDuration(500).setStartDelay(400)
            .start()

        price.translationX = 800f
        price.alpha = 0f
        price.animate().translationX(0f).alpha(1f).setDuration(500).setStartDelay(500)
            .start()

        catId.translationX = 800f
        catId.alpha = 0f
        catId.animate().translationX(0f).alpha(1f).setDuration(500).setStartDelay(600)
            .start()

        btnAddProd.translationX = 800f
        btnAddProd.alpha = 0f
        btnAddProd.animate().translationX(0f).alpha(1f).setDuration(500).setStartDelay(800)
            .start()

        // translationY
        evCatName.translationY = 800f
        evCatName.alpha = 0f
        evCatName.animate().translationY(0f).alpha(1f).setDuration(500).setStartDelay(850)
            .start()

        btnAddCat.translationY = 800f
        btnAddCat.alpha = 0f
        btnAddCat.animate().translationY(0f).alpha(1f).setDuration(500).setStartDelay(900)
            .start()
    }

    private fun addCategory(categoryAdd: CategoryAdd) {
        val call = adminApi.addCategory("Bearer " + CurrentUser.getToken(), categoryAdd)
        call.enqueue(object : Callback<Category> {
            override fun onResponse(call: Call<Category>, response: Response<Category>) {
                Toast.makeText(
                    context,
                    "Category ${response.body()?.name} Created Successfully",
                    Toast.LENGTH_SHORT
                )
                    .show()
            }

            override fun onFailure(call: Call<Category>, t: Throwable) {
                Toast.makeText(context, "Error: Task was Unsuccessfully", Toast.LENGTH_SHORT)
                    .show()
                call.cancel()
            }
        })
    }

    private fun addProduct(productAdd: ProductAdd) {
        val call = adminApi.addProduct("Bearer " + CurrentUser.getToken(), productAdd)
        call.enqueue(object : Callback<Product> {
            override fun onResponse(call: Call<Product>, response: Response<Product>) {
                Toast.makeText(
                    context,
                    "Product ${response.body()?.name} Created Successfully",
                    Toast.LENGTH_SHORT
                )
                    .show()
            }

            override fun onFailure(call: Call<Product>, t: Throwable) {
                Toast.makeText(context, "Error: Task was Unsuccessfully", Toast.LENGTH_SHORT)
                    .show()
                call.cancel()
            }
        })
    }

}