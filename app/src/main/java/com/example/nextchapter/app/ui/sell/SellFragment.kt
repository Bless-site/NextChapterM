package com.nextchapter.app.ui.sell

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.nextchapter.app.R
import com.nextchapter.app.databinding.FragmentSellBinding
import com.nextchapter.app.model.Book
import com.nextchapter.app.viewmodel.BookViewModel

class SellFragment : Fragment() {

    private var _binding: FragmentSellBinding? = null
    private val binding get() = _binding!!
    private val viewModel: BookViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentSellBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSubmitListing.setOnClickListener {
            if (validateForm()) {
                val condition = when (binding.rgCondition.checkedRadioButtonId) {
                    R.id.rb_new -> "New"
                    R.id.rb_good -> "Good"
                    R.id.rb_fair -> "Fair"
                    R.id.rb_poor -> "Poor"
                    else -> "Good"
                }

                val book = Book(
                    id = viewModel.getNextId(),
                    title = binding.etBookTitle.text.toString().trim(),
                    author = binding.etAuthor.text.toString().trim(),
                    courseName = binding.etCourseName.text.toString().trim(),
                    price = binding.etPrice.text.toString().toDoubleOrNull() ?: 0.0,
                    condition = condition,
                    description = binding.etDescription.text.toString().trim(),
                    sellerName = viewModel.currentUser.value?.fullName ?: "Campus Student",
                    location = viewModel.currentUser.value?.institution ?: "Campus",
                    postedDate = "Just now"
                )

                viewModel.addBook(book)
                Toast.makeText(requireContext(), "Listing added successfully!", Toast.LENGTH_SHORT).show()
                clearForm()
                findNavController().navigate(R.id.homeFragment)
            }
        }
    }

    private fun validateForm(): Boolean {
        var isValid = true
        val title = binding.etBookTitle.text.toString().trim()
        val author = binding.etAuthor.text.toString().trim()
        val course = binding.etCourseName.text.toString().trim()
        val price = binding.etPrice.text.toString().trim()

        if (title.isEmpty()) { binding.tilBookTitle.error = getString(R.string.error_required); isValid = false } else binding.tilBookTitle.error = null
        if (author.isEmpty()) { binding.tilAuthor.error = getString(R.string.error_required); isValid = false } else binding.tilAuthor.error = null
        if (course.isEmpty()) { binding.tilCourseName.error = getString(R.string.error_required); isValid = false } else binding.tilCourseName.error = null
        if (price.isEmpty()) { binding.tilPrice.error = getString(R.string.error_price); isValid = false }
        else if (price.toDoubleOrNull() == null || price.toDouble() <= 0) { binding.tilPrice.error = getString(R.string.error_price); isValid = false }
        else binding.tilPrice.error = null

        return isValid
    }

    private fun clearForm() {
        binding.etBookTitle.text?.clear()
        binding.etAuthor.text?.clear()
        binding.etCourseName.text?.clear()
        binding.etPrice.text?.clear()
        binding.etDescription.text?.clear()
        binding.rgCondition.check(R.id.rb_good)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}