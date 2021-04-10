package com.worker.worker.ui.OrderDetails

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.worker.worker.R
import com.worker.worker.databinding.FragmentOrderDetailsBinding
import com.worker.worker.model.Order

class OrderDetailsFragment : Fragment() {

    companion object {
        fun newInstance() = OrderDetailsFragment()
    }

    private lateinit var viewModel: OrderDetailsViewModel
    lateinit var orderDetailsBinding: FragmentOrderDetailsBinding
    lateinit var order:Order
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        orderDetailsBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_order_details,container,false)
        order = arguments?.let { OrderDetailsFragmentArgs.fromBundle(it).order }!!
        return orderDetailsBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(OrderDetailsViewModel::class.java)

        orderDetailsBinding.order = order

        orderDetailsBinding.completeLayout.setOnClickListener(View.OnClickListener { v ->
            if (order.completedBy != null){
                val des = OrderDetailsFragmentDirections.actionOrderDetailsFragmentToViewProfileFragment(order.completedBy!!)
                Navigation.findNavController(v).navigate(des)
            }


        })

    }

}