package com.worker.worker.ui.details

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.worker.worker.R
import com.worker.worker.databinding.DetailsFragmentBinding
import com.worker.worker.model.Order

class DetailsFragment : Fragment(), OnMapReadyCallback {

    companion object {
        fun newInstance() = DetailsFragment()
    }

    private lateinit var viewModel: DetailsViewModel
    private lateinit var detailsBinding: DetailsFragmentBinding
    lateinit var order: Order
    private  val TAG = "DetailsFragment"
    var token: String = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        detailsBinding =
            DataBindingUtil.inflate(inflater, R.layout.details_fragment, container, false)
        detailsBinding.maps.onCreate(savedInstanceState)
        detailsBinding.maps.getMapAsync(this)
        val sharedPreference =
            requireContext().getSharedPreferences("general", AppCompatActivity.MODE_PRIVATE)
        token = sharedPreference.getString("token", "")!!
        val id = sharedPreference.getInt("id", 0)
        Log.d(TAG, "onCreateView: $id , $token")
        order = arguments?.let { DetailsFragmentArgs.fromBundle(it).order }!!
        detailsBinding.isAllowed = !(id == order.publisher!!.id || token.isEmpty())


        detailsBinding.order = order
        return detailsBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailsViewModel::class.java)



        detailsBinding.takeOrderButton.setOnClickListener(
            View.OnClickListener {
                viewModel.takeOrder(token, order).observe(viewLifecycleOwner, Observer { response ->
                    if (response != null) {
                        Toast.makeText(activity, "Success", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(
                            activity,
                            "Failed To Take Order try again later",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                })

            }

        )
        detailsBinding.publisherName.setOnClickListener(View.OnClickListener { v ->
            val des = order.publisher?.let {
                DetailsFragmentDirections.actionDetailsFragmentToViewProfileFragment(
                    it
                )
            }

            Navigation.findNavController(v).navigate(des!!)
        })

        detailsBinding.maps.setOnClickListener(View.OnClickListener {

        })
    }

    override fun onResume() {
        super.onResume()
        detailsBinding.maps.onResume()
    }

    override fun onPause() {
        super.onPause()
        detailsBinding.maps.onPause()
    }

    override fun onStart() {
        super.onStart()
        detailsBinding.maps.onStart()
    }

    override fun onStop() {
        super.onStop()
        detailsBinding.maps.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        detailsBinding.maps.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        detailsBinding.maps.onLowMemory()
    }

    override fun onMapReady(maps: GoogleMap?) {
        val sydney = LatLng(order.lat.toDouble(), order.lng.toDouble())
        maps?.addMarker(MarkerOptions().position(sydney).title("Order Location"))
        maps?.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 15f))
    }

}