package com.worker.worker.ui.Profile

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.worker.worker.R
import com.worker.worker.databinding.FragmentProfileBinding
import com.worker.worker.model.User
import com.worker.worker.model.UserImage
import java.io.ByteArrayOutputStream
import java.io.IOException


class ProfileFragment : Fragment() {

    companion object {
        fun newInstance() = ProfileFragment()
    }

    private lateinit var viewModel: ProfileViewModel
    lateinit var profileBinding: FragmentProfileBinding
    var token = ""
    var image: String = ""
    lateinit var user: User
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        profileBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)
        disable()
        val sharedPreference =
            requireContext().getSharedPreferences("general", AppCompatActivity.MODE_PRIVATE)
        token = sharedPreference.getString("token", "")!!
        user = arguments?.let { ProfileFragmentArgs.fromBundle(it).user }!!

        profileBinding.user = user
        activity?.let {
            Glide.with(it).asBitmap().load(user.image).into(profileBinding.profileImageView)
        }
        profileBinding.profileImageView
        profileBinding.profileImageView.setOnClickListener {

            intentToFileManger()
        }
        profileBinding.profileEditImageView.setOnClickListener {
            enableEditing()
            profileBinding.isEditing = true
        }
        profileBinding.doeEdititng.setOnClickListener {
            profileBinding.isEditing = false
            disable()
            updateUserInfo()
        }

        return profileBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)

        viewModel.getFollowers(token).observe(viewLifecycleOwner, { response ->
            if (response != null) {
                profileBinding.orderCount2.text = response.followers!!.size.toString()
            } else {
                Toast.makeText(activity, "failed to get followers", Toast.LENGTH_SHORT).show()
            }

        })

//        viewModel.getCompleted(token , 0).observe(viewLifecycleOwner, Observer { res ->
//            if (res != null){
//                 profileBinding.orderCount.text = res.orders!!.size.toString()
//            }else{
//                  Toast.makeText(activity, "failed to get orders", Toast.LENGTH_SHORT).show()
//            }
//
//        })


    }

    private fun intentToFileManger() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        val mimeTypes = arrayOf(

            "image/*"
        )
        intent.type = "*/*"
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
        intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes)
        startActivityForResult(
            intent,
            102
        )
    }

    private fun bitMapToString(bitmap: Bitmap): String? {
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos)
        val b = baos.toByteArray()
        return Base64.encodeToString(b, Base64.DEFAULT)
    }

    private fun updateUserInfo() {
        if (profileBinding.userPersonalName.text.toString().length < 4) {
            profileBinding.userPersonalName.error = "Name Too short"
            return
        }
        if (profileBinding.userPersonalEmail.text.toString().length < 4) {
            profileBinding.userPersonalEmail.error = "Name Too Short"
            return
        }
        user.firstName = profileBinding.userPersonalName.text.toString()
        user.lastName = profileBinding.userPersonalEmail.text.toString()
        viewModel.updateUserInfo(token, user).observe(viewLifecycleOwner, { response ->
            if (response != null) {
                Toast.makeText(activity, "Success to update", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(activity, "failed to update", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun enableEditing() {
        profileBinding.userPersonalName.isClickable = true
        profileBinding.userPersonalEmail.isClickable = true
        profileBinding.userPersonalName.isEnabled = true
        profileBinding.userPersonalEmail.isEnabled = true


    }

    private fun disable() {
        profileBinding.userPersonalName.isClickable = false
        profileBinding.userPersonalEmail.isClickable = false
        profileBinding.userPersonalPhone.isClickable = false
        profileBinding.userPersonalName.isEnabled = false
        profileBinding.userPersonalEmail.isEnabled = false
        profileBinding.userPersonalPhone.isEnabled = false
    }

    private fun updateUserImage(token: String, userImage: UserImage) {
        viewModel.updateImage(token, userImage).observe(viewLifecycleOwner, { res ->
            if (res != null) {
                activity?.let {
                    Glide.with(it).asBitmap().load(res.images!![0])
                        .into(profileBinding.profileImageView)
                }
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
                profileBinding.profileImageView.setImageURI(data.data!!)

                val returnUri: Uri = data.data!!
                var bitmapImage: Bitmap? = null
                try {
                    bitmapImage =
                        MediaStore.Images.Media.getBitmap(activity?.contentResolver, returnUri)
                    profileBinding.profileImageView.setImageBitmap(bitmapImage)

                    val uimg = UserImage()
                    uimg.imagebase64 = bitMapToString(bitmapImage)!!

                    updateUserImage(token, uimg)

                } catch (e: IOException) {
                    e.printStackTrace()
                }


            }
        }
    }
}
