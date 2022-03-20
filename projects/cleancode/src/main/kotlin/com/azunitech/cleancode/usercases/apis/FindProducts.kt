package com.azunitech.cleancode.usercases.apis

/**
 * Usecase interface
 */
interface FindProducts {
    fun find(request: Request): Response

    data class Request(val nameContains: String?)

    data class Response(val products: List<Product>) {
        data class Product(val id: String, val name: String)
    }
}