package com.mikhailapps.architecture.ui.product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.mikhailapps.architecture.ViewBindingHolder
import com.mikhailapps.architecture.ViewBindingHolderImpl
import com.mikhailapps.architecture.databinding.FragmentFirstBinding
import com.mikhailapps.architecture.ui.base.BaseDataDrivenFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductFragment : BaseDataDrivenFragment(),
    ViewBindingHolder<FragmentFirstBinding> by ViewBindingHolderImpl() {

    private val productViewModel: ProductViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = initBinding(FragmentFirstBinding.inflate(layoutInflater), this)


    override fun fetchData(force: Boolean) {

    }

    override fun observeData() {
        productViewModel.productData.collectUIState(
            onProgress = {

            },
            onSuccess = {
                Toast.makeText(requireActivity(), "Fetched $it!!.name", Toast.LENGTH_SHORT)
                    .show()
            },
            onError = {

            }
        )
    }

    override fun setUi() {
        requiredBinding.buttonFirst.setOnClickListener {
            productViewModel.fetch()
            //findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }
}