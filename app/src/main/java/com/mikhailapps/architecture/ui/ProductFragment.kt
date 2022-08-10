package com.mikhailapps.architecture.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.mikhailapps.architecture.R
import com.mikhailapps.architecture.databinding.FragmentFirstBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductFragment : BaseDataDrivenFragment() {

    private val productViewModel: ProductViewModel by viewModels()


    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }

    override fun observeData() {
        productViewModel.productData.collectUIState(
            onSuccess = {
                Toast.makeText(requireActivity(), "Fetched $it!!.name", Toast.LENGTH_SHORT).show()
            },
            onError = {

            }
        )
    }

    override fun fetchData(force: Boolean) {
        productViewModel.fetch()
    }

    override fun setUi() {

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}