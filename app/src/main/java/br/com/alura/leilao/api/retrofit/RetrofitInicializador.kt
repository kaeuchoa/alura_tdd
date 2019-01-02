package br.com.alura.leilao.api.retrofit

import br.com.alura.leilao.api.retrofit.service.LeilaoService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import okhttp3.logging.HttpLoggingInterceptor.Level.BODY

class RetrofitInicializador {
    private val retrofit: Retrofit

    val leilaoService: LeilaoService
        get() = retrofit.create(LeilaoService::class.java)

    init {
        val client = configuraHttpClient()
        retrofit = Retrofit.Builder()
                .baseUrl(URL_BASE)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    private fun configuraHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = BODY
        return OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()
    }

    companion object {
        private val URL_BASE = "http://192.168.0.25:8080/"
    }

}
