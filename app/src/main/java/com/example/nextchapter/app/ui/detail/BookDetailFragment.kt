package com.nextchapter.app.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.nextchapter.app.R
import com.nextchapter.app.databinding.FragmentBookDetailBinding
import com.nextchapter.app.viewmodel.BookViewModel

class BookDetailFragment : Fragment() {

    private var _binding: FragmentBookDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel: BookViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentBookDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bookId = arguments?.getInt("bookId") ?: -1
        val book = viewModel.getBookById(bookId)

        if (book == null) {
            Toast.makeText(requireContext(), "Book not found", Toast.LENGTH_SHORT).show()
            findNavController().popBackStack()
            return
        }

        binding.tvDetailTitle.text = book.title
        binding.tvDetailAuthor.text = "${book.author}"
        binding.tvDetailCourseBadge.text = book.courseName
        binding.tvDetailConditionBadge.text = book.condition
        binding.tvDetailPrice.text = "R ${String.format("%.0f", book.price)}"
        binding.tvSellerName.text = book.sellerName
        binding.tvSellerLocation.text = "★★★★☆ • ${book.location}"
        binding.tvSellerAvatar.text = if (book.sellerName.isNotEmpty()) book.sellerName.first().toString() else "S"
        binding.tvLabelPosted.text = "Posted: ${book.postedDate}"
        binding.tvLabelLocation.text = "Location: ${book.location}"
        binding.tvDetailDescription.text = book.description.ifBlank { "No description provided." }

        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.btnSaveListing.setOnClickListener {
            Toast.makeText(requireContext(), "Listing saved!", Toast.LENGTH_SHORT).show()
        }

        binding.btnSendInquiry.setOnClickListener {
            findNavController().navigate(
                R.id.action_detail_to_inquiry,
                bundleOf("bookId" to book.id)
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}