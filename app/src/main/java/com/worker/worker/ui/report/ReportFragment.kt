package com.worker.worker.ui.report

import android.os.Bundle
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
import com.worker.worker.R
import com.worker.worker.adapter.ReportAdapter
import com.worker.worker.databinding.ReportFragmentBinding
import com.worker.worker.lis.OnClickRecyclerItem
import com.worker.worker.model.Report
import com.worker.worker.model.ReportRequest
import com.worker.worker.model.User

class ReportFragment : Fragment(), OnClickRecyclerItem {

    companion object {
        fun newInstance() = ReportFragment()
    }

    private lateinit var viewModel: ReportViewModel
    private lateinit var binding: ReportFragmentBinding
    var token = ""
    lateinit var user:User


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.report_fragment, container, false)
        val sharedPreference =
            requireContext().getSharedPreferences("general", AppCompatActivity.MODE_PRIVATE)
        token = sharedPreference.getString("token", "")!!
        user = arguments?.let { ReportFragmentArgs.fromBundle(it).user }!!
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ReportViewModel::class.java)

        viewModel.getReports(token).observe(viewLifecycleOwner, { res ->
            if (res != null) {
                setUpReportsRecyclerView(res.report!!)
            } else {
                Toast.makeText(activity, "Failed to get Reports", Toast.LENGTH_SHORT).show()
            }

        })

    }

    private fun setUpReportsRecyclerView(reports: ArrayList<Report>) {
        binding.reportsRecyclerView.adapter = ReportAdapter(reports, this)
    }

    override fun onclick(o: Any) {
        val report = o as Report
        var reportRequest = ReportRequest()
        reportRequest.reportId = report.id
        reportRequest.userId = user.id
            viewModel.reportUser(token,reportRequest).observe(viewLifecycleOwner, Observer { res ->
                if (res != null){
                    view?.let { Navigation.findNavController(it).popBackStack() }
                }else {
                    Toast.makeText(activity,"Please try Again Later",Toast.LENGTH_SHORT).show()
                }

            })

    }

}