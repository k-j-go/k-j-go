package com.azunitech.cleancode.usercases

import com.azunitech.cleancode.domain.FindProductsQuery
import com.azunitech.cleancode.domain.Product
import com.azunitech.cleancode.domain.ProductGateway
import com.azunitech.cleancode.usercases.apis.FindProducts
import org.springframework.stereotype.Component
import java.util.stream.Collectors

@Component
class FindProductsImpl(val productGateway: ProductGateway?) : FindProducts {
    override fun find(request: FindProducts.Request): FindProducts.Response {
        val result = productGateway!!.findProducts(FindProductsQuery(request.nameContains))
                .stream()
                .map { it.toUseCaseResponseModel() }.collect(Collectors.toList())
        return FindProducts.Response(result)
    }
}

fun Product.toUseCaseResponseModel(): FindProducts.Response.Product {
    return FindProducts.Response.Product(id, name)
}