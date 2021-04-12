package com.worker.worker.model

import java.io.Serializable

class ChangePassword : Serializable {
    var phoneNumber: String = ""
    var newPassword:String = ""
    var oldPassword:String = ""
}