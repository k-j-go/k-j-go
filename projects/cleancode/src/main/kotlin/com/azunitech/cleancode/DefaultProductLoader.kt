package com.azunitech.cleancode

import com.azunitech.cleancode.domain.ProductGateway
import com.azunitech.cleancode.web.ProductRpcService
import com.github.javafaker.Faker
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class DefaultProductLoader : CommandLineRunner {
    @Autowired
    lateinit var faker: Faker

    @Autowired
    lateinit var productGateway: ProductGateway

    @Autowired
    lateinit var productRpcService: ProductRpcService

    override fun run(vararg args: String?) {
        (1..5).forEach { _ -> persist() }
    }

    private fun persist() {
        productRpcService.createProduct(
                faker.beer()
                    .name()
        )
    }
}