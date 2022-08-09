package com.mikhailapps.architecture.ui

import androidx.lifecycle.viewModelScope
import com.mikhailapps.architecture.domain.model.ProductDomainModel
import com.mikhailapps.architecture.domain.usecase.GetProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val getProductUseCase: GetProductUseCase
) : BaseViewModel() {

    private val _productData = MutableStateFlow<ProductDomainModel>()
    val productData = _productData.asStateFlow()


    fun fetch() {
        viewModelScope.launch(Dispatchers.IO) {
            getProductUseCase.execute().collectRequest(_productData) { it }
        }
    }

}