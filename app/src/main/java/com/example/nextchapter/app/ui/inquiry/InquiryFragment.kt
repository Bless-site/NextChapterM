package com.nextchapter.app.ui.inquiry

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.nextchapter.app.databinding.FragmentInquiryBinding
import com.nextchapter.app.viewmodel.BookViewModel

class InquiryFragment : Fragment() {

    private var _binding: FragmentInquiryBinding? = null
    private val binding get() = _binding!!
    private val viewModel: BookViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentInquiryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bookId = arguments?.getInt("bookId") ?: -1
        val book = viewModel.getBookById(bookId)

        book?.let {
            binding.tvInquiryBookTitle.text = it.title
            binding.tvInquirySeller.text = "Seller: ${it.sellerName} | R ${String.format("%.0f", it.price)} | ${it.condition}"
            binding.tvInquiryPrice.text = "R ${String.format("%.0f", it.price)}"
        }

        // Quick template click listeners
        binding.tvTemplate1.setOnClickListener {
            binding.etMessage.setText("Is this still available?")
        }
        binding.tvTemplate2.setOnClickListener {
            binding.etMessage.setText("Can we meet on campus?")
        }
        binding.tvTemplate3.setOnClickListener {
            binding.etMessage.setText("Can you do a lower price?")
        }

        binding.btnBackInquiry.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.btnCancelInquiry.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.btnSendInquirySubmit.setOnClickListener {
            val message = binding.etMessage.text.toString().trim()
            if (message.isEmpty()) {
                binding.tilMessage.error = "Please enter a message"
                return@setOnClickListener
            }
            binding.tilMessage.error = null

            val sent = viewModel.sendInquiry(message)
            if (sent) {
                Toast.makeText(requireContext(), "Inquiry sent successfully!", Toast.LENGTH_SHORT).show()
                viewModel.resetInquiry()
                findNavController().popBackStack()
                findNavController().popBackStack()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}