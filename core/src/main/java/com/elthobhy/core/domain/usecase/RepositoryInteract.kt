package com.elthobhy.core.domain.usecase

import androidx.lifecycle.LiveData
import com.elthobhy.core.domain.model.Domain
import com.elthobhy.core.domain.repository.CatalogNewsDataSource
import com.elthobhy.core.utils.vo.Resource
import kotlinx.coroutines.flow.Flow

class RepositoryInteract(private val catalogNewsDataSource: CatalogNewsDataSource): UseCase {
    override fun getHeadline(): Flow<Resource<List<Domain>>> = catalogNewsDataSource.getTopHeadlines()

    override fun getSearch(q: String): Flow<List<Domain>> = catalogNewsDataSource.getSearch(q)
    override fun getDetail(content: String): LiveData<Domain> = catalogNewsDataSource.getDetailTopHeadlines(content)

}