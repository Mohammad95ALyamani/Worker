package com.worker.worker.Activity

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import com.mukesh.OnOtpCompletionListener
import com.worker.worker.R
import com.worker.worker.databinding.ActivityOTPBinding
import com.worker.worker.model.User
import java.util.concurrent.TimeUnit

class OTPActivity : AppCompatActivity() {
    lateinit var otpBinding: ActivityOTPBinding
    lateinit var auth: FirebaseAuth
    lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    private val TAG = "OTPActivity"
     lateinit var signUpViewModel: SignUpViewModel
    private var storedVerificationId: String = ""
    lateinit var user: User
    private var resendToken: PhoneAuthProvider.ForceResendingToken? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        otpBinding = DataBindingUtil.setContentView(
            this, R.layout.activity_sign_up
        )
        auth = FirebaseAuth.getInstance()
        auth.useAppLanguage()
        setUpCallBacks()
        signUpViewModel = ViewModelProvider(this).get(SignUpViewModel::class.java)
         user = intent.getSerializableExtra("user") as User

        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(user.phoneNumber)       // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(this)                 // Activity (for callback binding)
            .setCallbacks(callbacks)          // OnVerificationStateChangedCallbacks
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)

        otpBinding.otpView.setOtpCompletionListener {
            object : OnOtpCompletionListener {
                override fun onOtpCompleted(otp: String?) {
                    val credential = PhoneAuthProvider.getCredential(storedVerificationId, otp!!)
                    signInWithPhoneAuthCredential(credential)
                }

            }
        }
    }


    fun setUpCallBacks() {
        callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(phoneAuthCredential: PhoneAuthCredential) {
                otpBinding.otpView.isActivated = false
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
            }

        }
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithCredential:success")
                        signUpUserData()

                } else {
                    // Sign in failed, display a message and update the UI
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        // The verification code entered was invalid
                        otpBinding.otpView.error = "The verification code entered was invalid"
                        otpBinding.otpView.isActivated = true
                    }
                    // Update UI
                }
            }
    }


    fun signUpUserData(){
        signUpViewModel.signUpUser(user).observe(this, Observer { userResponse ->
            if (userResponse != null){
                saveUserToken(userResponse.token)
                Toast.makeText(this,"Success",Toast.LENGTH_SHORT).show()
            }else {
                 Toast.makeText(this,"failed",Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun saveUserToken(token:String){
        val sharedPreference = getSharedPreferences("general", MODE_PRIVATE)
        val editor = sharedPreference.edit()
        editor.putString("token", token)
        editor.apply()
    }

}