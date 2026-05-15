package com.nextchapter.app.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputLayout
import com.nextchapter.app.R
import com.nextchapter.app.databinding.FragmentRegistrationBinding
import com.nextchapter.app.model.User
import com.nextchapter.app.viewmodel.BookViewModel

class RegistrationFragment : Fragment() {

    private var _binding: FragmentRegistrationBinding? = null
    private val binding get() = _binding!!
    private val viewModel: BookViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentRegistrationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnRegister.setOnClickListener {
            if (validateForm()) {
                val user = User(
                    fullName = binding.etFullName.text.toString().trim(),
                    email = binding.etEmail.text.toString().trim(),
                    institution = binding.etInstitution.text.toString().trim()
                )
                viewModel.registerUser(user)
                findNavController().navigate(R.id.action_registration_to_home)
            }
        }

        binding.btnLogin.setOnClickListener {
            // For MVP: treat login same as registration (navigate to home)
            val name = binding.etFullName.text.toString().trim()
            val email = binding.etEmail.text.toString().trim()
            if (name.isNotBlank() && email.isNotBlank()) {
                val user = User(fullName = name, email = email, institution = "")
                viewModel.registerUser(user)
                findNavController().navigate(R.id.action_registration_to_home)
            } else {
                Toast.makeText(requireContext(), "Please enter your name and email to log in", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun validateForm(): Boolean {
        var isValid = true

        val fullName = binding.etFullName.text.toString().trim()
        val email = binding.etEmail.text.toString().trim()
        val password = binding.etPassword.text.toString()
        val institution = binding.etInstitution.text.toString().trim()

        isValid = validateField(binding.tilFullName, fullName, "Please enter your full name") && isValid
        isValid = validateEmail(binding.tilEmail, email) && isValid
        isValid = validatePassword(binding.tilPassword, password) && isValid
        isValid = validateField(binding.tilInstitution, institution, "Please enter your institution") && isValid

        return isValid
    }

    private fun validateField(layout: TextInputLayout, value: String, errorMsg: String): Boolean {
        return if (value.isEmpty()) {
            layout.error = errorMsg
            false
        } else {
            layout.error = null
            true
        }
    }

    private fun validateEmail(layout: TextInputLayout, email: String): Boolean {
        return when {
            email.isEmpty() -> {
                layout.error = getString(R.string.error_required)
                false
            }
            !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                layout.error = getString(R.string.error_email)
                false
            }
            else -> {
                layout.error = null
                true
            }
        }
    }

    private fun validatePassword(layout: TextInputLayout, password: String): Boolean {
        return when {
            password.isEmpty() -> {
                layout.error = getString(R.string.error_required)
                false
            }
            password.length < 8 -> {
                layout.error = getString(R.string.error_password)
                false
            }
            else -> {
                layout.error = null
                true
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}