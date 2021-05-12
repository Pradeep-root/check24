package com.pradeep.check24.di

import android.content.Context
import com.pradeep.check24.repository.ProductProductRepositoryImp
import com.pradeep.check24.repository.ProductRepository
import com.pradeep.check24.data.network.ProductApi
import com.pradeep.check24.utils.ConnectivityInterceptor
import com.pradeep.check24.utils.Constants.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.internal.connection.ConnectInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providesOkHttp(@ApplicationContext context : Context) = OkHttpClient.Builder()
        .addInterceptor(ConnectivityInterceptor(context))
        .build()


    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient) = Retrofit
        .Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()


    @Singleton
    @Provides
    fun providesProductApi(retrofit: Retrofit) = retrofit.create(ProductApi::class.java)


    @Singleton
    @Provides
    fun providesProductRepository(productApi: ProductApi) = ProductProductRepositoryImp(productApi) as ProductRepository
}