package com.ranielschneider.codansdigitalstore.core.di

import com.ranielschneider.codansdigitalstore.features.products.data.ProductApi
import com.ranielschneider.codansdigitalstore.features.products.data.ProductRepositoryImpl
import com.ranielschneider.codansdigitalstore.features.products.domain.ProductRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    companion object {

        @Provides
        @Singleton
        fun provideRetrofit(): Retrofit {
            return Retrofit.Builder()
                .baseUrl("https://dummyjson.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        @Provides
        @Singleton
        fun provideProductApi(
            retrofit: Retrofit
        ): ProductApi {
            return retrofit.create(ProductApi::class.java)
        }
    }

    @Binds
    abstract fun bindProductRepository(
        repository: ProductRepositoryImpl
    ): ProductRepository
}