package com.worker.worker.ui.Favourite

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.worker.worker.model.CustomResponse
import com.worker.worker.model.User
import com.worker.worker.repo.UsersRepo
import com.worker.worker.responses.FollowersResponse

class FavouriteViewModel : ViewModel() {
        val repo  = UsersRepo()

    fun getFollowers(token:String):MutableLiveData<FollowersResponse>{
        return repo.getFollowing(token)
    }
    fun unFollow(token: String,user: User):MutableLiveData<CustomResponse>{
        return repo.unFollowUser(token,user)
    }

}