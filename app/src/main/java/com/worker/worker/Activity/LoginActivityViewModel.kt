package com.worker.worker.Activity

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.worker.worker.model.User
import com.worker.worker.network.Builder
import com.worker.worker.responses.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivityViewModel : ViewModel() {
    private lateinit var userLiveData: MutableLiveData<UserResponse>
    private  val TAG = "LoginActivityViewModel"
    fun loginUser (user: User): MutableLiveData<UserResponse>{
        userLiveData = MutableLiveData()
       val call:Call<UserResponse> =  Builder.service.loginUser(user)
        call.enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.body()!!.status==200) {
                    userLiveData.value = response.body()
                } else {
                    userLiveData.value = null
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                userLiveData.value = null
                Log.d(TAG, "onFailure: "+ t.message)
            }

        })



        return userLiveData
    }
}