package com.worker.worker.ui.AddOrder


import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.worker.worker.model.CustomResponse
import com.worker.worker.model.Order
import com.worker.worker.network.Builder
import com.worker.worker.repo.OrderRepo
import com.worker.worker.responses.CategoriesResponse
import com.worker.worker.responses.CreateOrderResponse
import com.worker.worker.responses.OrderResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddOrderViewModel : ViewModel() {
    lateinit var orderMutable:MutableLiveData<CustomResponse>
    val repo = OrderRepo()
    private  val TAG = "AddOrderViewModel"
    fun createOrder(order: Order,token:String): MutableLiveData<CustomResponse>{
        orderMutable = MutableLiveData()
        val call = Builder.service.createOrder(order,token)
        call.enqueue(object : Callback<CustomResponse> {
            override fun onResponse(
                call: Call<CustomResponse>,
                response: Response<CustomResponse>
            ) {
                if (response.isSuccessful){
                    orderMutable.value = response.body()
                }else{
                    orderMutable.value = null
                }
            }

            override fun onFailure(call: Call<CustomResponse>, t: Throwable) {
                orderMutable.value = null
                Log.d(TAG, "onFailure: "+ t.message)

            }


        })
        return orderMutable
    }

    lateinit var categoriesMutable: MutableLiveData<CategoriesResponse>
    fun getCategories():MutableLiveData<CategoriesResponse>{
        categoriesMutable = MutableLiveData()
        val call = Builder.service.getCategories()
        call.enqueue(object : Callback<CategoriesResponse> {
            override fun onResponse(
                call: Call<CategoriesResponse>,
                response: Response<CategoriesResponse>
            ) {
                if (response.isSuccessful) {
                    categoriesMutable.value = response.body()
                } else {
                    categoriesMutable.value = null
                }
            }

            override fun onFailure(call: Call<CategoriesResponse>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.message}")
                categoriesMutable.value = null
            }

        })
        return categoriesMutable
    }

}