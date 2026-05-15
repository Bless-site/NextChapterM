package com.nextchapter.app.ui.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.nextchapter.app.R
import com.nextchapter.app.adapter.BookAdapter
import com.nextchapter.app.databinding.FragmentSearchBinding
import com.nextchapter.app.viewmodel.BookViewModel

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private val viewModel: BookViewModel by activityViewModels()
    private lateinit var adapter: BookAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = BookAdapter { book ->
            findNavController().navigate(
                R.id.action_search_to_detail,
                bundleOf("bookId" to book.id)
            )
        }

        binding.rvSearchResults.layoutManager = LinearLayoutManager(requireContext())
        binding.rvSearchResults.adapter = adapter

        viewModel.searchResults.observe(viewLifecycleOwner) { results ->
            adapter.submitList(results)
            val count = results.size
            binding.tvResultsCount.text = "$count result${if (count != 1) "s" else ""} found"
            binding.tvNoResults.visibility = if (results.isEmpty()) View.VISIBLE else View.GONE
            binding.rvSearchResults.visibility = if (results.isEmpty()) View.GONE else View.VISIBLE
        }

        // Show all books initially
        viewModel.searchBooks("")

        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.searchBooks(s.toString())
            }
            override fun afterTextChanged(s: Editable?) {}
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}