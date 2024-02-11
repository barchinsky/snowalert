package com.m.snowalert.infrastructure.repository

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RepositoryHiltModule {

    @Binds
    abstract fun bindAlertInfoRepository(repoImpl: AlertInfoRepositoryImpl): AlertInfoRepository
}