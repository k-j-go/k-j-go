package com.azunitech.cleancode.persistent

import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.repository.query.QueryByExampleExecutor

interface ProductRepository : PagingAndSortingRepository<ProductDocument, String>, QueryByExampleExecutor<ProductDocument> {
    fun findByNameContains(nameContains: String?) : List<ProductDocument>
}