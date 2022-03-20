package com.azunitech.cleancode.usercases

import com.azunitech.cleancode.domain.Product
import com.azunitech.cleancode.domain.ProductGateway
import com.azunitech.cleancode.usercases.apis.CreateProduct
import com.azunitech.cleancode.usercases.apis.NotifyEvent
import org.springframework.stereotype.Component
import java.util.*

@Component
class CreateProductImpl(val productGateway: ProductGateway?, val raiser: NotifyEvent) : CreateProduct {
    override fun create(request: CreateProduct.Request): CreateProduct.Response {
        val id = productGateway!!.create(request.toDomain())
        raiser.notice(NotifyEvent.ProductCreatedEvent(id))
        return CreateProduct.Response(id)
    }
}

fun CreateProduct.Request.toDomain(): Product {
    return Product(UUID.randomUUID().toString(), name)
}