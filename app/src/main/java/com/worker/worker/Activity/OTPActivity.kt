package com.worker.worker.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.mukesh.OnOtpCompletionListener
import com.worker.worker.R
import com.worker.worker.databinding.ActivityOTPBinding

class OTPActivity : AppCompatActivity() {
    lateinit var otpBinding: ActivityOTPBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        otpBinding = DataBindingUtil.setContentView(
            this, R.layout.activity_sign_up
        )


        otpBinding.otpView.setOtpCompletionListener {object : OnOtpCompletionListener{
            override fun onOtpCompleted(otp: String?) {

            }

        } }
    }
}