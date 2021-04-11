package com.worker.worker.repo

import android.os.Build
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.worker.worker.model.CustomResponse
import com.worker.worker.model.Order
import com.worker.worker.network.Builder
import com.worker.worker.responses.CategoriesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OrderRepo {
    private lateinit var categories: MutableLiveData<CategoriesResponse>
    lateinit var orders: MutableLiveData<ArrayList<Order>>
    private val TAG = "OrderRepo"

    fun getCategories(): MutableLiveData<CategoriesResponse> {
        categories = MutableLiveData()
        val call = Builder.service.getCategories()
        call.enqueue(object : Callback<CategoriesResponse> {
            override fun onResponse(
                call: Call<CategoriesResponse>,
                response: Response<CategoriesResponse>
            ) {
                if (response.isSuccessful) {
                    categories.value = response.body()
                } else {
                    categories.value = null
                }

            }

            override fun onFailure(call: Call<CategoriesResponse>, t: Throwable) {
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

    lateinit var takeOrderMutable: MutableLiveData<CustomResponse>


    fun takeOrder(token:String,order: Order): MutableLiveData<CustomResponse>{
        takeOrderMutable = MutableLiveData()
        val call = Builder.service.takeOrder(token, order)
        call.enqueue(object : Callback<CustomResponse> {
            override fun onResponse(
                call: Call<CustomResponse>,
                response: Response<CustomResponse>
            ) {
                if (response.isSuccessful) {
                    takeOrderMutable.value = response.body()
                } else {
                    takeOrderMutable.value = null
                }
            }

            override fun onFailure(call: Call<CustomResponse>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.message}")
                takeOrderMutable.value = null
            }

        })
        return takeOrderMutable
    }
    lateinit var deleteOrderMutable:MutableLiveData<CustomResponse>
    fun deleteOrder(token:String,order: Order):MutableLiveData<CustomResponse>{
        deleteOrderMutable = MutableLiveData()
        val call = Builder.service.deleteOrder(token,order.id)
        call.enqueue(object : Callback<CustomResponse> {
            override fun onResponse(
                call: Call<CustomResponse>,
                response: Response<CustomResponse>
            ) {
                if (response.isSuccessful) {
                    deleteOrderMutable.value = response.body()
                } else {
                    deleteOrderMutable.value = null
                }
            }

            override fun onFailure(call: Call<CustomResponse>, t: Throwable) {
                deleteOrderMutable.value = null
                Log.d(TAG, "onFailure: ${t.message}")
            }

        })
        return deleteOrderMutable
    }

    lateinit var updateOrder: MutableLiveData<CustomResponse>

    fun updateOrder(token: String,order: Order):MutableLiveData<CustomResponse>{
        updateOrder = MutableLiveData()
        
        val call = Builder.service.updateOrderInfo(token, order)
        call.enqueue(object : Callback<CustomResponse> {
            override fun onResponse(
                call: Call<CustomResponse>,
                response: Response<CustomResponse>
            ) {
                if (response.isSuccessful) {
                    updateOrder.value = response.body()
                } else {
                    updateOrder.value = null
                }
            }

            override fun onFailure(call: Call<CustomResponse>, t: Throwable) {
                updateOrder.value = null
                Log.d(TAG, "onFailure: ${t.message}")
            }

        })
        
        return updateOrder
    }
    lateinit var historyMutable: MutableLiveData<ArrayList<Order>>
    
    fun getUserHistory(token:String):MutableLiveData<ArrayList<Order>>{
        historyMutable = MutableLiveData()
        
        val call = Builder.service.getHistory(token)
        call.enqueue(object : Callback<ArrayList<Order>> {
            override fun onResponse(
                call: Call<ArrayList<Order>>,
                response: Response<ArrayList<Order>>
            ) {
                if (response.isSuccessful) {
                    historyMutable.value = response.body()
                } else {
                    historyMutable.value = null
                }
            }

            override fun onFailure(call: Call<ArrayList<Order>>, t: Throwable) {
                historyMutable.value = null
                Log.d(TAG, "onFailure: ${t.message}")
            }

        })
        
        
        return historyMutable
    }


}