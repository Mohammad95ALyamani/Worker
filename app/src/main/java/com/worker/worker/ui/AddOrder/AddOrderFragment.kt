package com.worker.worker.ui.AddOrder

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.adapters.AdapterViewBindingAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.worker.worker.Activity.MapsActivity
import com.worker.worker.R
import com.worker.worker.databinding.FragmentAddOrderBinding
import com.worker.worker.model.Categories
import com.worker.worker.model.Order
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.ln

class AddOrderFragment : Fragment(), AdapterViewBindingAdapter.OnItemSelected,
    AdapterView.OnItemSelectedListener {


    lateinit var addOrderBinding:FragmentAddOrderBinding
    private lateinit var viewModel: AddOrderViewModel
    var lat: Double = 0.0
    var lng: Double = 0.0
    var date: String = ""
    var category: Categories? = null
    lateinit var categories: ArrayList<Categories>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        addOrderBinding = DataBindingUtil.inflate(layoutInflater,R.layout.fragment_add_order, container, false)
        categories = ArrayList()

        return addOrderBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AddOrderViewModel::class.java)

        viewModel.getCategories().observe(viewLifecycleOwner, androidx.lifecycle.Observer { response ->
            if (response != null){
                categories = response.categories!!
                 val adapter = activity?.let { ArrayAdapter(it,
                     R.layout.support_simple_spinner_dropdown_item, categories.toArray()) }
                addOrderBinding.categoriesSpinner.adapter = adapter
            }else {
                 Toast.makeText(activity, "failed to get categories", Toast.LENGTH_SHORT).show()
            }

        })
        addOrderBinding.categoriesSpinner.setOnItemSelectedListener(this)
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


    private fun openCalender(){
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

    private fun continueToCreateOrder(){
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

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        category = categories[position]
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        //TODO
    }
}