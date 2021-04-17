package com.worker.worker.repo

import android.os.Build
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.worker.worker.model.CustomResponse
import com.worker.worker.model.Order
import com.worker.worker.network.Builder
import com.worker.worker.responses.CategoriesResponse
import com.worker.worker.responses.OrderResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OrderRepo {
    private lateinit var categories: MutableLiveData<CategoriesResponse>
    lateinit var orders: MutableLiveData<OrderResponse>
    private val TAG = "OrderRepo"

    fun getCategories(): MutableLiveData<CategoriesResponse> {
        categories = MutableLiveData()
        val call = Builder.service.getCategories()
        call.enqueue(object : Callback<CategoriesResponse> {
            override fun onResponse(
                call: Call<CategoriesResponse>,
                response: Response<CategoriesResponse>
            ) {
                if (response.body()!!.status==200) {
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

    fun getOrders(categoryId: Int, searchQuery: String): MutableLiveData<OrderResponse> {
        orders = MutableLiveData()

        val call = Builder.service.getOrders(categoryId, searchQuery)
        call.enqueue(object
            : Callback<OrderResponse> {
            override fun onResponse(
                call: Call<OrderResponse>,
                response: Response<OrderResponse>
            ) {
                if (response.body()!!.status==200) {
                    orders.value = response.body()
                } else {
                    orders.value = null
                }

            }

            override fun onFailure(call: Call<OrderResponse>, t: Throwable) {
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
                if (response.body()!!.status==200) {
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
                if (response.body()!!.status==200) {
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
                if (response.body()!!.status==200) {
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
    lateinit var historyMutable: MutableLiveData<OrderResponse>
    
    fun getUserHistory(token:String):MutableLiveData<OrderResponse>{
        historyMutable = MutableLiveData()
        
        val call = Builder.service.getHistory(token)
        call.enqueue(object : Callback<OrderResponse> {
            override fun onResponse(
                call: Call<OrderResponse>,
                response: Response<OrderResponse>
            ) {
                if (response.body()!!.status==200) {
                    historyMutable.value = response.body()
                } else {
                    historyMutable.value = null
                }
            }

            override fun onFailure(call: Call<OrderResponse>, t: Throwable) {
                historyMutable.value = null
                Log.d(TAG, "onFailure: ${t.message}")
            }

        })
        
        
        return historyMutable
    }

    lateinit var completedMutable:MutableLiveData<OrderResponse>

    fun getCompleted(token: String,id:Int):MutableLiveData<OrderResponse>{
        completedMutable = MutableLiveData()
        val call = Builder.service.getCompletedOrder(token, id)
        call.enqueue(object : Callback<OrderResponse> {
            override fun onResponse(call: Call<OrderResponse>, response: Response<OrderResponse>) {
                    if (response.body()!!.status == 200){
                        completedMutable.value = response.body()
                    }else {
                        completedMutable.value = null
                    }
            }

            override fun onFailure(call: Call<OrderResponse>, t: Throwable) {
                completedMutable.value = null
                Log.d(TAG, "onFailure: ${t.message}")
            }

        })
        return  completedMutable
    }


}