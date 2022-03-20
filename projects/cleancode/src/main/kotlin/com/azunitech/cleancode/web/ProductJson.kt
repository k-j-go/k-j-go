package com.azunitech.cleancode.web

import com.azunitech.cleancode.usercases.apis.FindProducts

data class ProductJson(val id: String, val name: String) {}

fun FindProducts.Response.toJsonList() = products.stream().map { ProductJson(it.id, it.name) }