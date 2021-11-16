package by.huk.marsexplorer.di.module

import by.huk.marsexplorer.BuildConfig
import by.huk.network.network.crypto.SpaceService
import by.huk.network.model.Model
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Module
class NetModule {

    @Provides
    fun getModule(service: SpaceService): Model {
        return Model(service)
    }

    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level =
            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        return interceptor
    }


    @Provides
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(okHttpClient)
            .baseUrl("https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/").build()
    }


    @Provides
    fun providesGson(): Gson {
        return GsonBuilder().create()
    }

    @Provides
    fun provideNetworkService(retrofit: Retrofit): SpaceService {
        return retrofit.create(SpaceService::class.java)
    }

    @Provides
    fun getClient(interceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }


}