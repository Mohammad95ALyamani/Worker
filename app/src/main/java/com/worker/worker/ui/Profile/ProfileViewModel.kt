package com.worker.worker.ui.Profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.worker.worker.model.CustomResponse
import com.worker.worker.model.User
import com.worker.worker.model.UserImage
import com.worker.worker.repo.OrderRepo
import com.worker.worker.repo.UsersRepo
import com.worker.worker.responses.FollowersResponse
import com.worker.worker.responses.ImageResponse
import com.worker.worker.responses.OrderResponse

class ProfileViewModel : ViewModel() {
    val repo = UsersRepo()
    val repoOrder = OrderRepo()
    fun updateUserInfo(token:String , user:User):MutableLiveData<CustomResponse>{
        return repo.updateUserInfo(token, user)
    }

    fun updateImage(token:String,userImage: UserImage):MutableLiveData<ImageResponse>{
        return repo.updateImage(token,userImage)
    }

    fun getFollowers(token: String,id: Int):MutableLiveData<FollowersResponse>{
        return repo.getFollower(token,id)
    }

    fun getDoneOrder(token: String):MutableLiveData<OrderResponse>{
        return repoOrder.getUserHistory(token)
    }

    fun getCompleted(token: String , id :Int):MutableLiveData<OrderResponse>{
        return repoOrder.getCompleted(token, id)
    }
}