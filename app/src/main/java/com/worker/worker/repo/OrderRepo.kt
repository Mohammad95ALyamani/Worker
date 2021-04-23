package com.worker.worker.repo

import android.os.Build
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.worker.worker.model.CustomResponse
import com.worker.worker.model.HolderRequest
import com.worker.worker.model.Order
import com.worker.worker.network.Builder
import com.worker.worker.responses.CategoriesResponse
import com.worker.worker.responses.OrderResponse
import com.worker.worker.responses.UserResponse
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
    lateinit var onHold:MutableLiveData<UserResponse>

    fun getOnHoldUsers(token: String,id: Int):MutableLiveData<UserResponse>{
        onHold = MutableLiveData()
        val call = Builder.service.getOnHoldUsers(token,id)
        call.enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful && response.body()!!.status == 200) {
                    onHold.value = response.body()
                } else {
                    onHold.value = null
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                onHold.value = null
                Log.d(TAG, "onFailure: ${t.message}")
            }

        })

        return onHold
    }

    lateinit var acceptMutable:MutableLiveData<CustomResponse>

    fun acceptUser(token: String, holderRequest: HolderRequest):MutableLiveData<CustomResponse>{
        acceptMutable = MutableLiveData()
        val call = Builder.service.acceptUser(token,holderRequest)
        call.enqueue(object : Callback<CustomResponse> {
            override fun onResponse(
                call: Call<CustomResponse>,
                response: Response<CustomResponse>
            ) {
                if (response.isSuccessful && response.body()!!.status == 200) {
                    acceptMutable.value = response.body()
                } else {
                    acceptMutable.value = null
                }
            }

            override fun onFailure(call: Call<CustomResponse>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.message}")
                acceptMutable.value = null
            }

        })

        return acceptMutable
    }

    lateinit var declineMutable: MutableLiveData<CustomResponse>


    fun declineUser(token: String,userId:Int, orderId:Int):MutableLiveData<CustomResponse>{
        declineMutable = MutableLiveData()
        val call = Builder.service.declineUser(token,userId,orderId)
        call.enqueue(object : Callback<CustomResponse> {
            override fun onFailure(call: Call<CustomResponse>, t: Throwable) {
                declineMutable.value = null
                Log.d(TAG, "onFailure: ")
            }

            override fun onResponse(
                call: Call<CustomResponse>,
                response: Response<CustomResponse>
            ) {
                if (response.isSuccessful && response.body()!!.status == 200){
                     declineMutable.value = response.body()
                }else{
                     declineMutable.value = null
                }
            }

        })
        return  declineMutable
    }


}