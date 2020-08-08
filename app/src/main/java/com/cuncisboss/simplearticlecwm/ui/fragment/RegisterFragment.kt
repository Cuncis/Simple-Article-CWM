package com.cuncisboss.simplearticlecwm.ui.fragment

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import at.favre.lib.crypto.bcrypt.BCrypt
import com.cuncisboss.simplearticlecwm.R
import com.cuncisboss.simplearticlecwm.util.Constants.EMAIL_PREF
import com.cuncisboss.simplearticlecwm.util.Constants.PASS_PREF
import com.cuncisboss.simplearticlecwm.util.Constants.USERNAME_PREF
import com.cuncisboss.simplearticlecwm.util.PasswordHelper
import com.cuncisboss.simplearticlecwm.util.PasswordHelper.generateHashedPass
import com.cuncisboss.simplearticlecwm.util.PasswordHelper.toMD5
import com.google.android.material.snackbar.Snackbar
import com.toxicbakery.bcrypt.Bcrypt
import kotlinx.android.synthetic.main.fragment_register.*
import org.koin.android.ext.android.inject


class RegisterFragment : Fragment() {

    private val sharePref by inject<SharedPreferences>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_register_now.setOnClickListener {
            if (et_username.text.toString().isEmpty()) {
                et_username.error = "Fill the username field correctly"
            } else if (et_email.text.toString().isEmpty()) {
                et_email.error = "Fill the email field correctly"
            } else if (et_password.text.toString().isEmpty()) {
                et_password.error = "Fill the password field correctly"
            } else if (et_re_password.text.toString().isEmpty()) {
                et_re_password.error = "Fill the confirm password field correctly"
            } else if (!PasswordHelper.isValid(et_re_password.text.toString(), generateHashedPass(et_password.text.toString()))) {
                Snackbar.make(requireView(), "Password not matches!", Snackbar.LENGTH_SHORT).show()
            } else {
                sharePref.edit()
                    .putString(USERNAME_PREF, et_username.text.toString())
                    .putString(EMAIL_PREF, et_email.text.toString())
                    .putString(PASS_PREF, generateHashedPass(et_password.text.toString()))
                    .apply()
                findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
            }
        }
        btn_back_to_login.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }
    }

}