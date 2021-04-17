package com.worker.worker.ui.EditOrder

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.adapters.AdapterViewBindingAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.worker.worker.Activity.MapsActivity
import com.worker.worker.R
import com.worker.worker.databinding.FragmentEditOrderBinding
import com.worker.worker.model.Categories
import com.worker.worker.model.Order
import java.text.SimpleDateFormat
import java.util.*

class EditOrderFragment : Fragment(), View.OnClickListener,AdapterViewBindingAdapter.OnItemSelected,
    AdapterView.OnItemSelectedListener {

    companion object {
        fun newInstance() = EditOrderFragment()
    }

    private lateinit var viewModel: EditOrderViewModel
    lateinit var editOrderBinding: FragmentEditOrderBinding
    var token = ""
    lateinit var order: Order
    lateinit var categories: ArrayList<Categories>
    var category: Categories? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        editOrderBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_edit_order, container, false)
        order = arguments?.let { EditOrderFragmentArgs.fromBundle(it).order }!!

        editOrderBinding.order = order
        category = order.categories

        val sharedPreference =
            requireContext().getSharedPreferences("general", AppCompatActivity.MODE_PRIVATE)
        token = sharedPreference.getString("token", "")!!
        return editOrderBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(EditOrderViewModel::class.java)

        editOrderBinding.editLocationButton.setOnClickListener(View.OnClickListener {
            val intent = Intent(activity, MapsActivity::class.java)
            startActivityForResult(intent, 101)
        })



        viewModel.getCategories().observe(viewLifecycleOwner, Observer { response ->
            if (response != null) {
                categories = response.categories!!
                val adapter = activity?.let {
                    ArrayAdapter(
                        it, R.layout.support_simple_spinner_dropdown_item,
                        categories.toArray()
                    )
                }
                editOrderBinding.editOrderCategoriesSpinner.adapter = adapter
            } else {
                Toast.makeText(activity, "failed to get Categories", Toast.LENGTH_SHORT).show()
            }
        })

        editOrderBinding.editOrderCategoriesSpinner.setOnItemSelectedListener(this)

        editOrderBinding.editOrderButton.setOnClickListener(this)
        editOrderBinding.editDateLayout.setOnClickListener(View.OnClickListener {
            openCalender()
        })

    }


    private fun updateOrder() {
        viewModel.updateOrder(token, order).observe(viewLifecycleOwner, Observer { response ->
            if (response != null) {
                view?.let { Navigation.findNavController(it).navigate(R.id.action_editOrderFragment_to_navigation_home) }
                Toast.makeText(activity, "Success to update Order", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(activity, "failed to update Order", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun openCalender() {
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
                editOrderBinding.editOrderDateTextView.text = sdf.format(c.time)
                order.date = sdf.format(c.time)

            },
            year,
            month,
            day
        )
        dpd.datePicker.minDate = System.currentTimeMillis()
        dpd.show()
    }

    override fun onClick(v: View?) {
        if (editOrderBinding.editOrderTitleTextInputEditText.text!!.length < 4) {
            editOrderBinding.editOrderTitleTextInputLayout.error = "Title is too short"
            return
        }
        if (editOrderBinding.editOrderDescriptionTextInputEditText.text!!.length < 4) {
            editOrderBinding.editOrderDescriptionTextInputLayout.error = "Description is too short"
            return
        }
        if (editOrderBinding.editOfferedPrice.text.isEmpty()) {
            editOrderBinding.editOfferedPrice.error = "Price should not be empty"
            return
        }
        order.title = editOrderBinding.editOrderTitleTextInputEditText.text.toString()
        order.description = editOrderBinding.editOrderDescriptionTextInputEditText.text.toString()
        order.price = editOrderBinding.editOfferedPrice.text.toString().toDouble()
        updateOrder()


    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        category = categories[position]
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == 101) {
            order.lat = data!!.getDoubleExtra("lat", 0.0).toString()
            order.lng = data.getDoubleExtra("lng", 0.0).toString()
            Toast.makeText(activity, "Location Selected", Toast.LENGTH_SHORT).show()
        }
    }
}