package com.worker.worker.repo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.worker.worker.model.Categories
import com.worker.worker.model.Order
import com.worker.worker.network.Builder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OrderRepo {
    private lateinit var categories: MutableLiveData<ArrayList<Categories>>
    lateinit var orders: MutableLiveData<ArrayList<Order>>
    private val TAG = "OrderRepo"

    fun getCategories(): MutableLiveData<ArrayList<Categories>> {
        categories = MutableLiveData()
        val call = Builder.service.getCategories()
        call.enqueue(object : Callback<ArrayList<Categories>> {
            override fun onResponse(
                call: Call<ArrayList<Categories>>,
                response: Response<ArrayList<Categories>>
            ) {
                if (response.isSuccessful) {
                    categories.value = response.body()
                } else {
                    categories.value = null
                }

            }

            override fun onFailure(call: Call<ArrayList<Categories>>, t: Throwable) {
                categories.value = null
                Log.d(TAG, "onFailure: " + t.message)
            }

        })
        return categories
    }

    fun getOrders(categoryId: Int, searchQuery: String): MutableLiveData<ArrayList<Order>> {
        orders = MutableLiveData()

        val call = Builder.service.getOrders(categoryId, searchQuery)
        call.enqueue(object
            : Callback<ArrayList<Order>> {
            override fun onResponse(
                call: Call<ArrayList<Order>>,
                response: Response<ArrayList<Order>>
            ) {
                if (response.isSuccessful) {
                    orders.value = response.body()
                } else {
                    orders.value = null
                }

            }

            override fun onFailure(call: Call<ArrayList<Order>>, t: Throwable) {
                orders.value = null
                Log.d(TAG, "onFailure: " + t.message)
            }

        })



        return orders
    }




}