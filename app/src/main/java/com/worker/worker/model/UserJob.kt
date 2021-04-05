package com.worker.worker.model

class UserJob {
    var id: Int = 0
    var name: String = ""
    var description: String = ""

    override fun toString(): String {
        return name
    }
}