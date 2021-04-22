package com.worker.worker.ui.OrderDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
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
    lateinit var order: Order
    var token = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        orderDetailsBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_order_details, container, false)
        order = arguments?.let { OrderDetailsFragmentArgs.fromBundle(it).order }!!
        val sharedPreference =
            requireContext().getSharedPreferences("general", AppCompatActivity.MODE_PRIVATE)
        token = sharedPreference.getString("token", "")!!
        if (order.completedBy != null ) {

            orderDetailsBinding.isCompleted = true

        }


        orderDetailsBinding.editImageView.setOnClickListener(View.OnClickListener { v ->
            val des =
                OrderDetailsFragmentDirections.actionOrderDetailsFragmentToEditOrderFragment(order)
            Navigation.findNavController(v).navigate(des)
        })
        val builder = AlertDialog.Builder(requireContext())
        val alertDialog: AlertDialog = builder.create()
        orderDetailsBinding.deleteImageView.setOnClickListener(View.OnClickListener {
            val alertDialog = android.app.AlertDialog.Builder(requireContext())

            //set title for alert dialog
            alertDialog.setTitle("Delete")
            //set message for alert dialog
            alertDialog.setMessage("are you sure you want to delete this order")
            alertDialog.setIcon(R.drawable.ic_delete)

            //performing positive action
            alertDialog.setPositiveButton("yes") { _, _ ->
                deleteOrder()
            }

            alertDialog.setNegativeButton("no") { _, _ ->


            }


            // Create the AlertDialog

            // Set other dialog properties
            alertDialog.setCancelable(true)
            alertDialog.show()

        })
        return orderDetailsBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(OrderDetailsViewModel::class.java)

        orderDetailsBinding.order = order

        orderDetailsBinding.completeLayout.setOnClickListener(View.OnClickListener { v ->
            if (order.completedBy != null) {
                val des =
                    OrderDetailsFragmentDirections.actionOrderDetailsFragmentToViewProfileFragment(
                        order.completedBy!!
                    )
                Navigation.findNavController(v).navigate(des)
            }


        })

    }

    fun deleteOrder() {
        viewModel.deleteOrder(token, order).observe(viewLifecycleOwner, Observer { response ->
            if (response != null) {
                view?.let { Navigation.findNavController(it).navigate(R.id.action_orderDetailsFragment_to_navigation_home) }
                Toast.makeText(activity, "Success to delete Order", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(activity, "Failed to delete Order Try Again", Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }

}