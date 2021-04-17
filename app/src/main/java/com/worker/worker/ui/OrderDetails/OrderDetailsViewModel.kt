package com.worker.worker.ui.OrderDetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.worker.worker.model.CustomResponse
import com.worker.worker.model.Order
import com.worker.worker.repo.OrderRepo

class OrderDetailsViewModel : ViewModel() {
    val repo = OrderRepo()
    fun deleteOrder(token:String,order: Order):MutableLiveData<CustomResponse>{
        return repo.deleteOrder(token,order)
    }
}