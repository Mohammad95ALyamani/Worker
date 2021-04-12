package com.worker.worker.Activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import com.worker.worker.R
import com.worker.worker.databinding.ActivityResetPasswordOTPBinding
import com.worker.worker.helpers.LocalHelper
import java.util.concurrent.TimeUnit

class ResetPasswordOTPActivity : AppCompatActivity() {
    lateinit var binding:ActivityResetPasswordOTPBinding
     lateinit var auth: FirebaseAuth
    lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    private var storedVerificationId: String = ""
     private var resendToken: PhoneAuthProvider.ForceResendingToken? = null
     var phone = ""
    private  val TAG = "ResetPasswordOTPActivit"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this, R.layout.activity_reset_password_o_t_p
        )
        phone = intent.getStringExtra("phone")!!
        auth = FirebaseAuth.getInstance()
        auth.useAppLanguage()
        setUpCallBacks()
        val phone = intent.getStringExtra("phone")
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phone)       // Phone number to verify
            .setTimeout(120L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(this)                 // Activity (for callback binding)
            .setCallbacks(callbacks)          // OnVerificationStateChangedCallbacks
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    fun VerifyResetOTP(view: View) {
         if (storedVerificationId != "") {
                val credential = PhoneAuthProvider.getCredential(
                    storedVerificationId,
                    binding.resetOtp.text.toString()
                )
                signInWithPhoneAuthCredential(credential)
            }


    }

    private fun setUpCallBacks() {
        callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(phoneAuthCredential: PhoneAuthCredential) {

                signInWithPhoneAuthCredential(phoneAuthCredential)

            }

            override fun onVerificationFailed(p0: FirebaseException) {
                Log.e(TAG, "onVerificationFailed: " + p0.message)
            }

            override fun onCodeSent(
                verificationId: String,
                token: PhoneAuthProvider.ForceResendingToken
            ) {
                super.onCodeSent(verificationId, token)
                Log.d(TAG, "onCodeSent: " + "code sent")
                storedVerificationId = verificationId
                resendToken = token
            }

        }
    }
    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithCredential:success")
                    val intent = Intent(this,ResetPasswordOTPActivity::class.java)
                    intent.putExtra("phone",phone)
                    startActivity(intent)

                } else {
                    // Sign in failed, display a message and update the UI
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        // The verification code entered was invalid
                        binding.resetOtp.error = "The verification code entered was invalid"

                    }
                    // Update UI
                }
            }
    }

     override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(LocalHelper.onAttach(newBase!!))
    }
}