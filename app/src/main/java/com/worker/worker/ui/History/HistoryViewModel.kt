package com.worker.worker.ui.History

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.worker.worker.model.Order
import com.worker.worker.repo.OrderRepo
import com.worker.worker.responses.OrderResponse

class HistoryViewModel : ViewModel() {
    val repo = OrderRepo()
    fun getHistory(token:String):MutableLiveData<OrderResponse>{
        return repo.getUserHistory(token)
    }
}