package com.worker.worker.model

import java.io.Serializable

class Order : Serializable {
    var id : Int = 0
    var title: String = ""
    var description: String = ""
    var date: String = ""
    var price: Double = 0.0
    var lng: String = ""
    var lat: String = ""
    var categories: Categories? = null
    var completedBy: User? = null
    var publisher:User? = null
    var orderStatus: OrderStatus? = null
}