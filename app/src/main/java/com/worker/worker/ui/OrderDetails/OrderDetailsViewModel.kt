package com.worker.worker.ui.OrderDetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.worker.worker.model.CustomResponse
import com.worker.worker.model.HolderRequest
import com.worker.worker.model.Order
import com.worker.worker.repo.OrderRepo
import com.worker.worker.responses.UserResponse

class OrderDetailsViewModel : ViewModel() {
    val repo = OrderRepo()
    fun deleteOrder(token:String,order: Order):MutableLiveData<CustomResponse>{
        return repo.deleteOrder(token,order)
    }
    fun getOnHoldOrder(token: String,id:Int):MutableLiveData<UserResponse>{
        return repo.getOnHoldUsers(token,id)
    }

    fun acceptUser(token: String,holderRequest: HolderRequest):MutableLiveData<CustomResponse>{
        return repo.acceptUser(token, holderRequest)
    }

    fun declineUser(token: String,userId:Int,orderId:Int):MutableLiveData<CustomResponse>{
        return repo.declineUser(token, userId, orderId)
    }
}