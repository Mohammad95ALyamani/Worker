package com.worker.worker.Activity

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.worker.worker.model.User
import com.worker.worker.network.Builder
import com.worker.worker.repo.OrderRepo
import com.worker.worker.responses.UserJobResponse
import com.worker.worker.responses.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpViewModel: ViewModel() {
    lateinit var userLiveData: MutableLiveData<UserResponse>
    private  val TAG = "SignUpViewModel"
    
    fun signUpUser(user: User): MutableLiveData<UserResponse>{
        userLiveData = MutableLiveData()

        val call = Builder.service.signUpUser(user)
        call.enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful) {
                    userLiveData.value = response.body()
                } else {
                    userLiveData.value = null
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Log.d(TAG, "onFailure: "+ t.message)
                userLiveData.value = null
            }

        })



        return userLiveData
    }
    lateinit var userJobMutable: MutableLiveData<UserJobResponse>
    fun getUserJobs():MutableLiveData<UserJobResponse>{
        userJobMutable = MutableLiveData()
        val call = Builder.service.getUserJobs()
        call.enqueue(object : Callback<UserJobResponse> {
            override fun onResponse(
                call: Call<UserJobResponse>,
                response: Response<UserJobResponse>
            ) {
                if (response.isSuccessful) {
                    userJobMutable.value = response.body()
                } else {
                    userJobMutable.value = null
                }

            }

            override fun onFailure(call: Call<UserJobResponse>, t: Throwable) {
                userJobMutable.value = null
                Log.d(TAG, "onFailure: ${t.message}")
            }

        })
        return userJobMutable
    }
}