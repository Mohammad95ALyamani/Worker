package com.worker.worker.ui.Home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.worker.worker.model.Categories
import com.worker.worker.model.Order
import com.worker.worker.repo.OrderRepo

class HomeViewModel : ViewModel() {
    val repo: OrderRepo = OrderRepo()
    fun getOrders(categoryId:Int,searchQuery:String): MutableLiveData<ArrayList<Order>>{
        return repo.getOrders(categoryId,searchQuery)
    }

    fun getCategories():MutableLiveData<ArrayList<Categories>>{
        return repo.getCategories()
    }

}