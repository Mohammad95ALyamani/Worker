package com.worker.worker.ui.AddOrder


import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.worker.worker.model.Order
import com.worker.worker.network.Builder
import com.worker.worker.responses.CreateOrderResponse
import com.worker.worker.responses.OrderResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddOrderViewModel : ViewModel() {
    lateinit var orderMutable:MutableLiveData<CreateOrderResponse>
    private  val TAG = "AddOrderViewModel"
    fun createOrder(order: Order,token:String): MutableLiveData<CreateOrderResponse>{
        orderMutable = MutableLiveData()
        var call = Builder.service.createOrder(order,token)
        call.enqueue(object : Callback<CreateOrderResponse> {
            override fun onResponse(
                call: Call<CreateOrderResponse>,
                response: Response<CreateOrderResponse>
            ) {
                if (response.isSuccessful){
                    orderMutable.value = response.body()
                }else{
                    orderMutable.value = null
                }
            }

            override fun onFailure(call: Call<CreateOrderResponse>, t: Throwable) {
                orderMutable.value = null
                Log.d(TAG, "onFailure: "+ t.message)

            }


        })
        return orderMutable
    }
}