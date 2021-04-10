package com.worker.worker.ui.EditOrder

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.worker.worker.model.CustomResponse
import com.worker.worker.model.Order
import com.worker.worker.repo.OrderRepo
import com.worker.worker.responses.CategoriesResponse

class EditOrderViewModel : ViewModel() {
    val repo = OrderRepo()
   fun updateOrder(token:String, order: Order):MutableLiveData<CustomResponse>{
       return repo.updateOrder(token, order)
   }

    fun getCategories():MutableLiveData<CategoriesResponse>{
        return repo.getCategories()
    }
}