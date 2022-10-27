package dev.ferrant.quickcoin.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.ferrant.core.data.repository.SearchRepository
import dev.ferrant.core.data.repository.SearchRepositoryImpl
import dev.ferrant.quickcoin.domain.userCases.SearchCoinsUseCase
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideSearchRepository(searchRepository: dev.ferrant.core.data.repository.SearchRepositoryImpl): dev.ferrant.core.data.repository.SearchRepository =
        searchRepository

    @Provides
    @Singleton
    fun provideSearchCoinsUseCase(searchRepository: dev.ferrant.core.data.repository.SearchRepository): SearchCoinsUseCase =
        SearchCoinsUseCase(searchRepository)

}
