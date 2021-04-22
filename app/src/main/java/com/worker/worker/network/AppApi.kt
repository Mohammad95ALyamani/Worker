package com.worker.worker.network

import com.worker.worker.helpers.Constants
import com.worker.worker.model.*
import com.worker.worker.responses.*
import retrofit2.Call
import retrofit2.http.*

interface AppApi {
    @GET(Constants.getOrders)
    fun getOrders(
        @Query("categoryId") categoryId: Int,
        @Query("title") orderTitle: String
    ): Call<OrderResponse>

    @GET(Constants.CHECK_USER)
    fun checkUser(@Query("phoneNum") phone: String): Call<CustomResponse>

    @POST(Constants.Login)
    fun loginUser(@Body user: User): Call<UserResponse>

    @POST(Constants.SignUp)
    fun signUpUser(@Body user: User): Call<UserResponse>

    @POST(Constants.ORDER)
    fun createOrder(
        @Body order: Order,
        @Header("AccessToken") token: String
    ): Call<OrderResponse>

    @GET(Constants.getJobCategory)
    fun getCategories(): Call<CategoriesResponse>

    @GET(Constants.USER)
    fun getUserInfo(@Header("AccessToken") token: String): Call<UserResponse>

    @PUT(Constants.USER)
    fun updateUserInfo(@Header("AccessToken") token: String, @Body user: User): Call<CustomResponse>



    @PUT(Constants.FORGET_PASSWORD)
    fun forgetPassword(
        @Body change: User
    ): Call<CustomResponse>

    @PUT(Constants.CHANGE_PASSWORD)
    fun changeUserPassword(
        @Header("AccessToken") token: String,
        @Body change: ChangePassword
    ): Call<CustomResponse>

    @POST(Constants.Follow)
    fun followUser(@Header("AccessToken") token: String, @Body user: User): Call<CustomResponse>

    @DELETE(Constants.UN_FOLLOW)
    fun unFollowUser(@Header("AccessToken") token: String, @Query("id") user: Int): Call<CustomResponse>

    @GET(Constants.FOLLOWING)
    fun getFollowing(@Header("AccessToken") token: String): Call<FollowersResponse>

    @GET(Constants.FOLLOWER)
    fun getFollowers(@Header("AccessToken") token: String,@Query("id")id: Int): Call<FollowersResponse>

    @GET(Constants.HISTORY)
    fun getHistory(@Header("AccessToken") token: String): Call<OrderResponse>

    @PUT(Constants.ORDER)
    fun updateOrderInfo(@Header("AccessToken") token: String, @Body order: Order): Call<CustomResponse>


    @GET(Constants.REPORT)
    fun getReports(@Header("AccessToken") token: String): Call<ReportResponse>

    @GET(Constants.COMPLETED)
    fun getCompletedOrder(@Header("AccessToken") token: String, @Query("id")id:Int):Call<OrderResponse>

    @POST(Constants.REPORT)
    fun reportUser(
        @Header("AccessToken") token: String,
        reportRequest: ReportRequest
    ): Call<CustomResponse>

    @DELETE(Constants.ORDER)
    fun deleteOrder(
        @Header("AccessToken") token: String,
        @Query("id") orderId: Int
    ): Call<CustomResponse>

    @PUT(Constants.RESERVE_ORDER)
    fun takeOrder(@Header("AccessToken") token: String, @Body order: Order): Call<CustomResponse>

    @GET(Constants.JOB_CATEGORY)
    fun getUserJobs(): Call<UserJobResponse>

    @POST(Constants.UPLOAD_IMG)
    fun updateUserImage(@Header("AccessToken") token: String,@Body uimg: UserImage): Call<ImageResponse>

    @GET(Constants.IS_FOLLOW)
    fun isFollowing(@Header("AccessToken") token: String,@Query("id") userId:Int): Call<CustomResponse>
}