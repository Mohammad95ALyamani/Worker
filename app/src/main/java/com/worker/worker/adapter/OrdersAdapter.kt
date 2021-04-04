package com.worker.worker.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.worker.worker.model.Order

class OrdersAdapter(
    private var orders: ArrayList<Order>, private var context: Context
) : RecyclerView.Adapter<OrdersAdapter.OrdersViewHolder>() {




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrdersViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: OrdersViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return orders.size
    }


     class OrdersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}