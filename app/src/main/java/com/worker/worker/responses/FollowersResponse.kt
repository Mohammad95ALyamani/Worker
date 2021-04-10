package com.worker.worker.responses

import com.worker.worker.model.User

class FollowersResponse {
    var status : Int = 0
    var message: String = ""
    var followers: ArrayList<User>? = null
}