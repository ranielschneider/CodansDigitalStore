package com.ranielschneider.codansdigitalstore.features.products.data

import com.ranielschneider.codansdigitalstore.features.products.domain.Product
import com.ranielschneider.codansdigitalstore.features.products.domain.ProductRepository
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val api: ProductApi
) : ProductRepository {

    override suspend fun getProducts(): List<Product> {
        return api.getProducts().products.map { dto ->
            Product(
                id = dto.id,
                title = dto.title,
                description = dto.description,
                price = dto.price,
                thumbnail = dto.thumbnail
            )
        }
    }
}