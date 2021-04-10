package com.worker.worker.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.worker.worker.R
import com.worker.worker.databinding.CustomOrderBinding
import com.worker.worker.model.Order
import com.worker.worker.ui.History.HistoryFragmentDirections
import com.worker.worker.ui.Home.HomeFragmentDirections

class OrdersAdapter(
    private var orders: ArrayList<Order>, private var context: Context, private var fragment: Int
) : RecyclerView.Adapter<OrdersAdapter.OrdersViewHolder>() {


    lateinit var layoutInflater: LayoutInflater
    lateinit var listItemBinding: CustomOrderBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrdersViewHolder {

        layoutInflater = LayoutInflater.from(parent.context)

        listItemBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.custom_order, parent, false)

        return OrdersViewHolder(listItemBinding)
    }

    override fun onBindViewHolder(holder: OrdersViewHolder, position: Int) {
        holder.bindViews(orders[position])
        holder.itemView.setOnClickListener(View.OnClickListener { v ->
            if (fragment == 0) {
                val des =
                    HomeFragmentDirections.actionNavigationHomeToDetailsFragment(orders[position])
                Navigation.findNavController(v).navigate(des)
            } else {
                val des =
                    HistoryFragmentDirections.actionNavigationHistoryToOrderDetailsFragment(orders[position])
                Navigation.findNavController(v).navigate(des)
            }
        })
    }

    override fun getItemCount(): Int {
        return orders.size
    }


    class OrdersViewHolder(customOrderBinding: CustomOrderBinding) :
        RecyclerView.ViewHolder(customOrderBinding.root) {
        val binding = customOrderBinding
        fun bindViews(order: Order) {
            binding.order = order
            binding.executePendingBindings()
        }
    }
}