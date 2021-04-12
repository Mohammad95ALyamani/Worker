package com.worker.worker.network

import com.worker.worker.helpers.Constants
import com.worker.worker.model.CustomResponse
import com.worker.worker.model.Order
import com.worker.worker.model.ReportRequest
import com.worker.worker.model.User
import com.worker.worker.responses.*
import retrofit2.Call
import retrofit2.http.*

interface AppApi {
    @GET(Constants.getOrders)
    fun getOrders(
        @Query("categoryId") categoryId: Int,
        @Query("title") orderTitle: String
    ): Call<OrderResponse>


    @POST(Constants.Login)
    fun loginUser(@Body user: User): Call<User>

    @POST(Constants.SignUp)
    fun signUpUser(@Body user: User): Call<User>

    @POST(Constants.createOrder)
    fun createOrder(
        @Body order: Order,
        @Header("AccessToken") token: String
    ): Call<CustomResponse>

    @GET(Constants.getJobCategory)
    fun getCategories(): Call<CategoriesResponse>

    @GET(Constants.USER)
    fun getUserInfo(@Header("AccessToken") token: String): Call<User>

    @PUT(Constants.USER)
    fun updateUserInfo(@Header("AccessToken") token: String, @Body user: User): Call<CustomResponse>

    @PUT(Constants.USER)
    fun changeUserPassword(
        @Header("AccessToken") token: String,
        @Body user: User
    ): Call<CustomResponse>

    @POST(Constants.FOLLOWER)
    fun followUser(@Header("AccessToken") token: String, @Body user: User): Call<CustomResponse>

    @PUT(Constants.FOLLOWER)
    fun unFollowUser(@Header("AccessToken") token: String, @Body user: User): Call<CustomResponse>

    @GET(Constants.FOLLOWER)
    fun getFollowers(@Header("AccessToken") token: String): Call<FollowersResponse>

    @GET(Constants.ORDER)
    fun getHistory(@Header("AccessToken") token: String): Call<ArrayList<Order>>

    @PUT(Constants.ORDER)
    fun updateOrderInfo(@Header("AccessToken") token: String, order: Order): Call<CustomResponse>


    @GET(Constants.REPORT)
    fun getReports(@Header("AccessToken") token: String): Call<ReportResponse>

    @POST(Constants.REPORT)
    fun reportUser(
        @Header("AccessToken") token: String,
        reportRequest: ReportRequest
    ): Call<CustomResponse>

    @DELETE(Constants.ORDER)
    fun deleteOrder(
        @Header("AccessToken") token: String,
        @Query("orderId") orderId: Int
    ): Call<CustomResponse>

    @POST(Constants.ORDER)
    fun takeOrder(@Header("AccessToken") token: String, @Body order: Order): Call<CustomResponse>

    @GET(Constants.JOB_CATEGORY)
    fun getUserJobs(): Call<UserJobResponse>
}