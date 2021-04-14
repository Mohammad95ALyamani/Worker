package com.worker.worker.ui.Profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.worker.worker.model.CustomResponse
import com.worker.worker.model.User
import com.worker.worker.model.UserImage
import com.worker.worker.repo.UsersRepo
import com.worker.worker.responses.FollowersResponse

class ProfileViewModel : ViewModel() {
    val repo = UsersRepo()
    fun updateUserInfo(token:String , user:User):MutableLiveData<CustomResponse>{
        return repo.updateUserInfo(token, user)
    }

    fun updateImage(token:String,userImage: UserImage):MutableLiveData<CustomResponse>{
        return repo.updateImage(token,userImage)
    }

    fun getFollowers(token: String):MutableLiveData<FollowersResponse>{
        return repo.getFollowers(token)
    }
}