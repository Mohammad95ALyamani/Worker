package com.worker.worker.repo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.worker.worker.model.CustomResponse
import com.worker.worker.model.ReportRequest
import com.worker.worker.model.User
import com.worker.worker.model.UserImage
import com.worker.worker.network.Builder
import com.worker.worker.responses.FollowersResponse
import com.worker.worker.responses.ReportResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UsersRepo {
    private val TAG = "UsersRepo"
    lateinit var followersMutable: MutableLiveData<FollowersResponse>
    lateinit var followUserMutable: MutableLiveData<CustomResponse>
    lateinit var reportResponseMutable: MutableLiveData<ReportResponse>
    lateinit var reportUserMutable: MutableLiveData<CustomResponse>
    fun getFollowers(token: String): MutableLiveData<FollowersResponse> {
        followersMutable = MutableLiveData()

        val call: Call<FollowersResponse> = Builder.service.getFollowers(token)
        call.enqueue(object : Callback<FollowersResponse> {
            override fun onResponse(
                call: Call<FollowersResponse>,
                response: Response<FollowersResponse>
            ) {
                if (response.isSuccessful) {
                    followersMutable.value = response.body()
                } else {
                    followersMutable.value = null
                }
            }

            override fun onFailure(call: Call<FollowersResponse>, t: Throwable) {
                followersMutable.value = null
                Log.d(TAG, "onFailure: ${t.message}")
            }

        })

        return followersMutable
    }


    fun followUser(token: String, user: User): MutableLiveData<CustomResponse> {
        followUserMutable = MutableLiveData()
        val call = Builder.service.followUser(token, user)
        call.enqueue(object : Callback<CustomResponse> {
            override fun onResponse(
                call: Call<CustomResponse>,
                response: Response<CustomResponse>
            ) {
                if (response.isSuccessful) {
                    followUserMutable.value = response.body()
                } else {
                    followUserMutable.value = null
                }
            }

            override fun onFailure(call: Call<CustomResponse>, t: Throwable) {
                followUserMutable.value = null
                Log.d(TAG, "onFailure: ${t.message}")
            }

        })

        return followUserMutable
    }

    fun unFollowUser(token: String, user: User): MutableLiveData<CustomResponse> {
        followUserMutable = MutableLiveData()
        val call = Builder.service.unFollowUser(token, user)
        call.enqueue(object : Callback<CustomResponse> {
            override fun onResponse(
                call: Call<CustomResponse>,
                response: Response<CustomResponse>
            ) {
                if (response.isSuccessful) {
                    followUserMutable.value = response.body()
                } else {
                    followUserMutable.value = null
                }
            }

            override fun onFailure(call: Call<CustomResponse>, t: Throwable) {
                followUserMutable.value = null
                Log.d(TAG, "onFailure: ${t.message}")
            }

        })

        return followUserMutable
    }

    fun getReports(token: String): MutableLiveData<ReportResponse> {
        reportResponseMutable = MutableLiveData()
        val call = Builder.service.getReports(token)
        call.enqueue(object : Callback<ReportResponse> {
            override fun onResponse(
                call: Call<ReportResponse>,
                response: Response<ReportResponse>
            ) {
                if (response.isSuccessful) {
                    reportResponseMutable.value = response.body()
                } else {
                    reportResponseMutable.value = null
                }
            }

            override fun onFailure(call: Call<ReportResponse>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.message}")
                reportResponseMutable.value = null
            }

        })

        return reportResponseMutable
    }

    fun reportUser(token: String, reportRequest: ReportRequest): MutableLiveData<CustomResponse> {
        reportUserMutable = MutableLiveData()
        val call = Builder.service.reportUser(token, reportRequest)
        call.enqueue(object : Callback<CustomResponse> {
            override fun onResponse(
                call: Call<CustomResponse>,
                response: Response<CustomResponse>
            ) {
                if (response.isSuccessful) {
                    reportUserMutable.value = response.body()
                } else {
                    reportUserMutable.value = null
                }

            }

            override fun onFailure(call: Call<CustomResponse>, t: Throwable) {
                reportUserMutable.value = null
                Log.d(TAG, "onFailure: ${t.message}")
            }

        })

        return reportUserMutable
    }

    lateinit var userMutable: MutableLiveData<User>

    fun getUserInfo(token: String): MutableLiveData<User> {
        userMutable = MutableLiveData()
        val call = Builder.service.getUserInfo(token)

        call.enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful) {
                    userMutable.value = response.body()
                } else {
                    userMutable.value = null
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                userMutable.value = null
                Log.d(TAG, "onFailure: ${t.message}")
            }

        })
        return userMutable
    }

    lateinit var updateUserInfo: MutableLiveData<CustomResponse>
    fun updateUserInfo(token: String, user: User): MutableLiveData<CustomResponse> {
        updateUserInfo = MutableLiveData()

        val call = Builder.service.updateUserInfo(token, user)
        call.enqueue(object : Callback<CustomResponse> {
            override fun onResponse(
                call: Call<CustomResponse>,
                response: Response<CustomResponse>
            ) {
                if (response.isSuccessful) {
                    updateUserInfo.value = response.body()
                } else {
                    updateUserInfo.value = null
                }
            }

            override fun onFailure(call: Call<CustomResponse>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.message}")
                updateUserInfo.value = null
            }

        })
        return updateUserInfo
    }

    lateinit var updateUserImageMutable: MutableLiveData<CustomResponse>

    fun updateImage(token: String, image: UserImage): MutableLiveData<CustomResponse> {
        updateUserImageMutable = MutableLiveData()

        val call = Builder.service.updateUserImage(token, image)
        call.enqueue(object : Callback<CustomResponse> {
            override fun onResponse(
                call: Call<CustomResponse>,
                response: Response<CustomResponse>
            ) {
                if (response.isSuccessful) {
                    updateUserImageMutable.value = response.body()
                } else {
                    updateUserImageMutable.value = null
                }
            }

            override fun onFailure(call: Call<CustomResponse>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.message}")
                updateUserImageMutable.value = null
            }
        })


        return updateUserImageMutable

    }
}