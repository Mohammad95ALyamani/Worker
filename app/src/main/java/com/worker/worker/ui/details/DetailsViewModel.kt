package com.worker.worker.ui.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.worker.worker.model.CustomResponse
import com.worker.worker.model.Order
import com.worker.worker.repo.OrderRepo

class DetailsViewModel : ViewModel() {
    val repo = OrderRepo()

    fun takeOrder(token: String , order: Order):MutableLiveData<CustomResponse>{
        return repo.takeOrder(token, order)
    }
}