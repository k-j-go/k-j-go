package com.azunitech.cleancode.web

import com.azunitech.cleancode.usercases.apis.CreateProduct
import com.azunitech.cleancode.usercases.apis.FindProducts
import com.googlecode.jsonrpc4j.JsonRpcService
import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImpl
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import java.util.stream.Collectors


const val PRODUCT_RPC_PATH = "/rpc/product"

@JsonRpcService(PRODUCT_RPC_PATH)
interface ProductRpcService {
    fun findProducts(nameStartsWith: String?): List<ProductJson>
    fun createProduct(name: String): String
}

@JsonRpcService(PRODUCT_RPC_PATH)
@Component
class ProductRpcServiceImpl(val findProducts: FindProducts,
                            val createProduct: CreateProduct) : ProductRpcService {

    override fun createProduct(name: String): String {
        return createProduct.create(CreateProduct.Request(name)).id;
    }

    @GetMapping
    override fun findProducts(nameStartsWith: String?): List<ProductJson> {
        return findProducts.find(FindProducts.Request(nameStartsWith)).toJsonList().collect(Collectors.toList())
    }
}