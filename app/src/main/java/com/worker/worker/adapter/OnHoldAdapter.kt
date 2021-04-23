package com.worker.worker.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.worker.worker.R
import com.worker.worker.databinding.CustomUserTekeOrderBinding
import com.worker.worker.lis.DeleteItem
import com.worker.worker.lis.OnClickRecyclerItem
import com.worker.worker.model.User
import com.worker.worker.ui.OrderDetails.OrderDetailsFragmentDirections

class OnHoldAdapter(var users: ArrayList<User>, var onClickLis: OnClickRecyclerItem,var deleteItem: DeleteItem) :
    RecyclerView.Adapter<OnHoldAdapter.OnHoldViewHolder>() {
    lateinit var layoutInflater: LayoutInflater
    lateinit var listItemBinding: CustomUserTekeOrderBinding



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnHoldViewHolder {
        layoutInflater = LayoutInflater.from(parent.context)

        listItemBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.custom_user_teke_order, parent, false)

        return OnHoldViewHolder(listItemBinding)
    }

    override fun onBindViewHolder(holder: OnHoldViewHolder, position: Int) {

        holder.bindViews(users[position])
        Log.d("onHold", "onBindViewHolder: ${users[position].phoneNumber}")
        holder.binding.acceptImage.setOnClickListener {
            onClickLis.onclick(users[position])
        }
        holder.itemView.setOnClickListener(View.OnClickListener { v ->
            val des = OrderDetailsFragmentDirections.actionOrderDetailsFragmentToViewProfileFragment(users[position])
            Navigation.findNavController(v).navigate(des)
        })
        holder.binding.declineImage.setOnClickListener {

            deleteItem.oncDelete(users[position])
            users.remove(users[position])
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int {
        return users.size
    }
     class OnHoldViewHolder(customOrderBinding: CustomUserTekeOrderBinding) :
        RecyclerView.ViewHolder(customOrderBinding.root) {

        val binding = customOrderBinding
        fun bindViews(user: User) {
            binding.user = user
            binding.executePendingBindings()
        }
    }
}