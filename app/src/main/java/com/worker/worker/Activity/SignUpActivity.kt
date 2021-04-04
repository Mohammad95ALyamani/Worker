package com.worker.worker.Activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.worker.worker.R
import com.worker.worker.databinding.ActivitySignUpBinding
import com.worker.worker.model.User
import com.worker.worker.model.UserJob
import java.util.concurrent.TimeUnit

class SignUpActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignUpBinding
    lateinit var auth: FirebaseAuth
    lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    var job: UserJob? = null
    lateinit var signUpViewModel: SignUpViewModel
    private val TAG = "SignUpActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this, R.layout.activity_sign_up
        )
        auth = FirebaseAuth.getInstance()
        auth.useAppLanguage()
        signUpViewModel = ViewModelProvider(this).get(SignUpViewModel::class.java)
    }

    fun signUpUser(v: View) {
        val user = User()

        if (binding.firstNameTietSginUp.text!!.length < 3) {
            binding.firstNameTietSginUp.error = "This Field is Require"
            return
        }
        if (binding.lastNameTietSginUp.text!!.length < 3) {
            binding.lastNameTietSginUp.error = "This Field is Require"
            return
        }
        if (binding.phoneTietSignUp.length() != 10) {
            binding.phoneTietSignUp.error = "Incorrect Phone number"
            return
        }
        if (binding.passwordTietSginUp.text!!.length < 8) {
            binding.passwordTietSginUp.error = "Password is too short"

            return
        }
        if (binding.confirmPasswordTietSginUp.text!!.length < 8) {
            binding.confirmPasswordTextInputLayoutSignUp.error = "Password is too short"
            return
        }
        if (binding.passwordTietSginUp.text.toString() != binding.confirmPasswordTietSginUp.text.toString()) {
            binding.passwordTietSginUp.error = "Password Doesn't Match"
            binding.confirmPasswordTietSginUp.error = "Password Doesn't Match"
            return
        }
        if (job == null) {
            binding.userJobTextLayout.error = "Job is Require! "
        }
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(binding.phoneTietSignUp.text.toString())       // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(this)                 // Activity (for callback binding)
            .setCallbacks(callbacks)          // OnVerificationStateChangedCallbacks
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)




        //Todo
    }

    fun setUpCallBacks(){
        callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
            override fun onVerificationCompleted(p0: PhoneAuthCredential) {

            }

            override fun onVerificationFailed(p0: FirebaseException) {

            }

            override fun onCodeSent(p0: String, p1: PhoneAuthProvider.ForceResendingToken) {
                super.onCodeSent(p0, p1)
            }

        }
    }
}