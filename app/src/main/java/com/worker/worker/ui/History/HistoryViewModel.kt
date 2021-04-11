package com.worker.worker.ui.History

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.worker.worker.model.Order
import com.worker.worker.repo.OrderRepo

class HistoryViewModel : ViewModel() {
    val repo = OrderRepo()
    fun getHistory(token:String):MutableLiveData<ArrayList<Order>>{
        return repo.getUserHistory(token)
    }
}