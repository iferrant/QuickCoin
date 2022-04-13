package dev.ferrant.quickcoin.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.ferrant.quickcoin.data.SearchRepository
import dev.ferrant.quickcoin.data.SearchRepositoryImpl
import dev.ferrant.quickcoin.domain.userCases.SearchCoinsUseCase
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideSearchRepository(searchRepository: SearchRepositoryImpl): SearchRepository =
        searchRepository

    @Provides
    @Singleton
    fun provideSearchCoinsUseCase(searchRepository: SearchRepository): SearchCoinsUseCase =
        SearchCoinsUseCase(searchRepository)

}
