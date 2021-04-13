package com.worker.worker.ui.Profile

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Base64
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
import com.worker.worker.R
import com.worker.worker.databinding.FragmentProfileBinding
import com.worker.worker.model.User
import com.worker.worker.model.UserImage
import java.io.ByteArrayOutputStream
import java.io.FileNotFoundException
import java.io.IOException
import java.io.InputStream

class ProfileFragment : Fragment() {

    companion object {
        fun newInstance() = ProfileFragment()
    }

    private lateinit var viewModel: ProfileViewModel
    lateinit var profileBinding: FragmentProfileBinding
    var token = ""
    var image: String = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        profileBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)

        val sharedPreference =
            requireContext().getSharedPreferences("general", AppCompatActivity.MODE_PRIVATE)
        token = sharedPreference.getString("token", "")!!
         val user = arguments?.let { ProfileFragmentArgs.fromBundle(it).user }

        profileBinding.user = user

        profileBinding.profileImageView.setOnClickListener(View.OnClickListener {
            intentToFileManger()
        })
        return profileBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        // TODO: Use the ViewModel
    }

    private fun intentToFileManger() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        val mimeTypes = arrayOf(
            "application/pdf",
            "image/*",
            "application/vnd.openxmlformats-officedocument.wordprocessingml.document",
            "application/msword",
            "application/vnd.ms-excel"
        )
        intent.type = "*/*"
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
        intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes)
        startActivityForResult(
            intent,
            102
        )
    }

    private fun convertToBase64(uri: Uri): String? {
        var `in`: InputStream? = null
        try {
            `in` = activity?.contentResolver?.openInputStream(uri)
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }
        val byteBuffer = ByteArrayOutputStream()
        val bufferSize = 1024
        val buffer = ByteArray(bufferSize)
        var len = 0
        while (true) {
            try {
                if (`in`!!.read(buffer).also { len = it } == -1) break
            } catch (e: IOException) {
                e.printStackTrace()
            }
            byteBuffer.write(buffer, 0, len)
        }
        val bytes = byteBuffer.toByteArray()
        Log.d("data", "onActivityResult: bytes size=" + bytes.size)
        Log.d(
            "data",
            "onActivityResult: Base64string=" + Base64.encodeToString(bytes, Base64.DEFAULT)
        )
        return Base64.encodeToString(bytes, Base64.DEFAULT)
    }

    fun updateUserInfo(user: User) {
        viewModel.updateUserInfo(token, user).observe(viewLifecycleOwner, Observer { response ->
            if (response != null) {
                Toast.makeText(activity, "Success to update", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(activity, "failed to update", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun updateUserImage(token: String, userImage: UserImage) {
        viewModel.updateImage(token, userImage).observe(viewLifecycleOwner, Observer { res ->
            if (res != null) {
                Toast.makeText(activity, "Success to update", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(activity, "failed to update", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 102) {
            if (resultCode == RESULT_OK && data != null) {
                val uri: Uri = data.data!!
                val uimg = UserImage()
                uimg.imagebase64 = convertToBase64(uri)!!

                updateUserImage(token, uimg)
            }
        }
    }
}
