package com.ranielschneider.codansdigitalstore.core.di

import com.ranielschneider.codansdigitalstore.features.products.data.ProductApi
import com.ranielschneider.codansdigitalstore.features.users.data.UserApi
import com.ranielschneider.codansdigitalstore.features.products.data.ProductRepositoryImpl
import com.ranielschneider.codansdigitalstore.features.products.domain.ProductRepository
import com.ranielschneider.codansdigitalstore.features.users.domain.repository.UserRepositoryImpl
import com.ranielschneider.codansdigitalstore.features.users.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import com.ranielschneider.codansdigitalstore.features.posts.data.PostApi
import com.ranielschneider.codansdigitalstore.features.posts.domain.repository.PostRepository
import com.ranielschneider.codansdigitalstore.features.posts.domain.repository.PostRepositoryImpl


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
        fun provideUserApi(
            retrofit: Retrofit
        ): UserApi {
            return retrofit.create(UserApi::class.java)
        }

        @Provides
        @Singleton
        fun provideProductApi(
            retrofit: Retrofit
        ): ProductApi {
            return retrofit.create(ProductApi::class.java)
        }
        @Provides
        @Singleton
        fun providePostApi(
            retrofit: Retrofit
        ): PostApi {
            return retrofit.create(PostApi::class.java)
        }
    }

    @Binds
    abstract fun bindPostRepository(
        repository: PostRepositoryImpl
    ): PostRepository

    @Binds
    abstract fun bindProductRepository(
        repository: ProductRepositoryImpl
    ): ProductRepository
    @Binds
    abstract fun bindUserRepository(
        repositoryImpl: UserRepositoryImpl
    ) : UserRepository
}