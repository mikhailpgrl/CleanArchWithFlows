package com.mikhailapps.architecture.ui.product

import com.mikhailapps.architecture.domain.model.ProductDomainModel
import com.mikhailapps.architecture.domain.usecase.GetRemoteProductUseCaseImpl
import com.mikhailapps.architecture.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val getProductUseCase: GetRemoteProductUseCaseImpl
) : BaseViewModel() {

    private val _productData = MutableStateFlow<List<ProductDomainModel>>()
    val productData = _productData.asStateFlow()


    fun fetch() {
        getProductUseCase.invoke().collectRequest(_productData)
    }

}