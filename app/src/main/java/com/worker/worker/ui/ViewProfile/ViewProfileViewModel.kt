package com.worker.worker.ui.ViewProfile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.worker.worker.model.CustomResponse
import com.worker.worker.model.User
import com.worker.worker.repo.OrderRepo
import com.worker.worker.repo.UsersRepo
import com.worker.worker.responses.FollowersResponse
import com.worker.worker.responses.OrderResponse

class ViewProfileViewModel : ViewModel() {
    val repo = UsersRepo()
    val repoOrder = OrderRepo()
    fun followUser(token:String,user:User):MutableLiveData<CustomResponse>{
        return repo.followUser(token, user)
    }

    fun unFollowUser(token:String,user:User):MutableLiveData<CustomResponse>{
        return repo.unFollowUser(token, user)
    }

    fun isFollowing(token: String, userId:Int):MutableLiveData<CustomResponse>{
        return repo.isFollowing(token, userId)
    }
    fun getFollower(token: String, userId:Int):MutableLiveData<FollowersResponse>{
        return repo.getFollower(token,userId)
    }

    fun getCompleted(token: String , id :Int):MutableLiveData<OrderResponse>{
        return repoOrder.getCompleted(token, id)
    }
}