package com.azunitech.cleancode.domain

/**
 * Gateway is used to restract the domain object create and find
 */
interface ProductGateway {
    fun findProducts(query: FindProductsQuery): List<Product>
    fun create(product: Product): String
}

data class FindProductsQuery(val nameContains: String?)