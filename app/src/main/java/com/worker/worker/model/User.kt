package com.worker.worker.model

class User {
    var id : Int = 0
    var firstName: String = ""
    var lastName: String = ""
    var password: String = ""
    var phoneNumber: String = ""
    var token : String = ""
    var image: String = ""
    var job: UserJob? = null
}