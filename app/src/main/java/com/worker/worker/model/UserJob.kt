package com.worker.worker.model

import java.io.Serializable

class UserJob: Serializable {
    var id: Int = 0
    var name: String = ""
    var description: String = ""

    override fun toString(): String {
        return name
    }
}