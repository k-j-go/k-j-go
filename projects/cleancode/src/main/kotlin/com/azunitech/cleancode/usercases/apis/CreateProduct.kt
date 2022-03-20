package com.azunitech.cleancode.usercases.apis

/**
 * Core use cases
 */
interface CreateProduct {
    fun create(request: Request): Response

    data class Request(val name: String)

    data class Response(val id: String)
}