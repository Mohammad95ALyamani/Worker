package com.worker.worker.ui.Verification

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.worker.worker.R

class VerificationCodeFragment : Fragment() {

    companion object {
        fun newInstance() = VerificationCodeFragment()
    }

    private lateinit var viewModel: VerificationCodeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_verification_code, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(VerificationCodeViewModel::class.java)
        // TODO: Use the ViewModel
    }

}