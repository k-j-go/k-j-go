package com.azunitech.cleancode.usercases.apis

interface NotifyEvent{
    fun notice( events: ProductCreatedEvent)
    data class ProductCreatedEvent( val id: String)
}