package com.worker.worker.responses

import com.worker.worker.model.UserJob

class UserJobResponse {
    var status: Int = 0
    var message:String = ""
    var jobs: ArrayList<UserJob>? = null
}