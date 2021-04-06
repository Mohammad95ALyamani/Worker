package com.worker.worker.ui.AddOrder

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.worker.worker.Activity.MapsActivity
import com.worker.worker.databinding.FragmentAddOrderBinding
import com.worker.worker.model.Categories
import com.worker.worker.model.Order
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.ln

class AddOrderFragment : Fragment() {


    lateinit var addOrderBinding:FragmentAddOrderBinding
    private lateinit var viewModel: AddOrderViewModel
    var lat: Double = 0.0
    var lng: Double = 0.0
    var date: String = ""
    var category: Categories? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        addOrderBinding = FragmentAddOrderBinding.inflate(layoutInflater, container, false)


        return addOrderBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AddOrderViewModel::class.java)
        addOrderBinding.addOrderButton.setOnClickListener(View.OnClickListener {
            continueToCreateOrder()
        })

        addOrderBinding.addOrderDateLayOut.setOnClickListener(View.OnClickListener {
            openCalender()
        })

        addOrderBinding.addLocationButton.setOnClickListener(
            View.OnClickListener {
                val intent = Intent(activity,MapsActivity::class.java)
                startActivityForResult(intent,101)
            }
        )
    }

    fun openMaps(){
        //TODO
    }
    fun openCalender(){
         val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(
            requireActivity(),
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                // Display Selected date in Toast
                val myFormat = "MM/dd/yyyy" //In which you need put here

                val sdf = SimpleDateFormat(myFormat, Locale.US)
                addOrderBinding.addOrderDateTextView.text = sdf.format(c.time)
                date = sdf.format(c.time)

            },
            year,
            month,
            day
        )
        dpd.datePicker.minDate = System.currentTimeMillis()
        dpd.show()
    }

    fun continueToCreateOrder(){
        if (addOrderBinding.orderTitleTextInputEditText.text!!.length < 5){
            addOrderBinding.orderTitleTextInputEditText.error = "This Field is required"
            return
        }
        if (addOrderBinding.orderDescriptionTextInputEditText.text!!.length < 5){
            addOrderBinding.orderDescriptionTextInputEditText.error = "This Field is required"
            return
        }
        if (addOrderBinding.offeredPriceAddOrderEditText.text!!.isEmpty()){
            addOrderBinding.offeredPriceAddOrderEditText.error = "This Field is required"
            return
        }
        if (lat == 0.0 || lng == 0.0 ){
            Toast.makeText(activity, "Location is required", Toast.LENGTH_SHORT).show()
            return
        }
        if (date.isEmpty()){
             Toast.makeText(activity, "Date is required", Toast.LENGTH_SHORT).show()
            return
        }
        if (category == null){
            Toast.makeText(activity, "Select Order Category", Toast.LENGTH_SHORT).show()
            return
        }
        val order = Order()
        order.title = addOrderBinding.orderTitleTextInputEditText.text.toString()
        order.description =addOrderBinding.orderDescriptionTextInputEditText.text.toString()
        order.price =  addOrderBinding.offeredPriceAddOrderEditText.text.toString().toDouble()
        order.lat = lat.toString()
        order.lng = lng.toString()
        order.categories = category
        order.date = date
        val destination = AddOrderFragmentDirections.actionAddOrderFragmentToSubmitOrderFragment(
            order
        )

        Navigation.findNavController(addOrderBinding.root).navigate(destination)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == 101){
            lat = data!!.getDoubleExtra("lat",0.0)
            lng = data.getDoubleExtra("lng",0.0)
           Toast.makeText(activity,"Location Selected", Toast.LENGTH_SHORT).show()
        }
    }
}