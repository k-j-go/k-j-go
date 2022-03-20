package com.azunitech.cleancode.usercases.apis

interface ConsumeEvent{
    fun notice( events: ProductCreatedEvent)
    data class ProductCreatedEvent( var id: String)
}