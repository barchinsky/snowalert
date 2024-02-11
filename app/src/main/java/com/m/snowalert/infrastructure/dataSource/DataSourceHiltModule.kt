package com.m.snowalert.infrastructure.dataSource

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class DataSourceHiltModule {

    @Binds
    abstract fun bindAlertLocalDataSource(alertDataSourceImpl: AlertInfoLocalDataSourceImpl): AlertInfoLocalDataSource
}