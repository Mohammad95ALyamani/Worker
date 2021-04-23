package com.worker.worker.helpers

import retrofit2.http.DELETE

class Constants {


    companion object {
        const val baseUrl = "http://generals.azurewebsites.net/api/"
        const val searchEndPoint = ""
        const val getJobCategory = "JobCategory"
        const val getOrders = "Order"
        const val addOrder = ""
        const val Login = "Login"
        const val SignUp = "Signup"
        const val createOrder = "CreateOrder"
        const val ENGLISH_LANG = 2
        const val ARABIC_LANG = 1
        const val Follow = "Follow"
        const val UN_FOLLOW = "Unfollow"
        const val ORDER = "Order"
        const val HISTORY = "OrderHistory"
        const val COMPLETED = "CompletedOrder"
        const val JOB_CATEGORY = "JobCategory"
        const val USER = "User"
        const val FOLLOWING = "Following"
        const val FOLLOWER = "Follower"
        const val REPORT = "Report"
        const val IS_FOLLOW = "IsFollow"
        const val UPLOAD_IMG = "UploadImage"
        const val RESERVE_ORDER = "ReserveOrder"
        const val CHECK_USER = "CheckUser"
        const val FORGET_PASSWORD = "ForgetPassword"
        const val CHANGE_PASSWORD = "ChangePassword"
        const val GET_ON_HOLD = "GetOnHold"
        const val ACCEPT = "Accept"
        const val DECLINE = "Decline"

    }
}