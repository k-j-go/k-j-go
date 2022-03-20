package com.azunitech.cleancode.persistent

import com.azunitech.cleancode.domain.FindProductsQuery
import com.azunitech.cleancode.domain.Product
import com.azunitech.cleancode.domain.ProductGateway
import org.springframework.stereotype.Component
import java.util.stream.Collectors

@Component
class ProductGatewayImpl(private val productRepository: ProductRepository? = null) : ProductGateway {
    override fun create(product: Product): String {
        val saved = productRepository!!.save(product.toNewDocument())
        return saved.businessKey
    }

    override fun findProducts(query: FindProductsQuery): List<Product> {
        return productRepository!!.findByNameContains(query.nameContains).stream().map { it.toDomain() }
                .collect(Collectors.toList())
    }
}

fun Product.toNewDocument(): ProductDocument {
    return ProductDocument(null, id, name);
}