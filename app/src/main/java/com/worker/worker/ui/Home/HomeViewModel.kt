package com.worker.worker.ui.Home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.worker.worker.model.Categories
import com.worker.worker.model.Order
import com.worker.worker.repo.OrderRepo
import com.worker.worker.responses.CategoriesResponse
import com.worker.worker.responses.OrderResponse

class HomeViewModel : ViewModel() {
    val repo: OrderRepo = OrderRepo()
    fun getOrders(categoryId:Int,searchQuery:String): MutableLiveData<OrderResponse>{
        return repo.getOrders(categoryId,searchQuery)
    }

    fun getCategories():MutableLiveData<CategoriesResponse>{
        return repo.getCategories()
    }

}