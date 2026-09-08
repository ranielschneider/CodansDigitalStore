package com.ranielschneider.codansdigitalstore.core.di

import com.ranielschneider.codansdigitalstore.features.cart.data.CartApi
import com.ranielschneider.codansdigitalstore.features.cart.data.CartRepositoryImpl
import com.ranielschneider.codansdigitalstore.features.cart.domain.CartRepository
import com.ranielschneider.codansdigitalstore.features.posts.data.PostApi
import com.ranielschneider.codansdigitalstore.features.posts.domain.repository.PostRepository
import com.ranielschneider.codansdigitalstore.features.posts.domain.repository.PostRepositoryImpl
import com.ranielschneider.codansdigitalstore.features.products.data.ProductApi
import com.ranielschneider.codansdigitalstore.features.products.data.ProductRepositoryImpl
import com.ranielschneider.codansdigitalstore.features.products.domain.ProductRepository
import com.ranielschneider.codansdigitalstore.features.users.data.UserApi
import com.ranielschneider.codansdigitalstore.features.users.domain.repository.UserRepository
import com.ranielschneider.codansdigitalstore.features.users.domain.repository.UserRepositoryImpl
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

        @Provides
        @Singleton
        fun provideCartApi(
            retrofit: Retrofit
        ): CartApi {
            return retrofit.create(CartApi::class.java)
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
    ): UserRepository

    @Binds
    abstract fun bindCartRepository(
        repositoryImpl: CartRepositoryImpl
    ): CartRepository
}