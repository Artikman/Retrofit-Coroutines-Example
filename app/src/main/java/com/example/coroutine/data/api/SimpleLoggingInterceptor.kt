package com.example.coroutine.data.api

import okhttp3.Interceptor
import okhttp3.Interceptor.Chain
import okhttp3.Request
import okhttp3.Response

class SimpleLoggingInterceptor : Interceptor {

    /**
     * Поддержка перехватчиков/interceptors для обработки заголовков запросов, например,
     * для работы с токенами авторизации в заголовке Authorization.
     */
    override fun intercept(chain: Chain): Response {
        val original: Request = chain.request()

        val request: Request = original.newBuilder()
            .header("Accept", "application/json")
            .header("Authorization", "auth-token")
            .method(original.method(), original.body())
            .build()
        return chain.proceed(request)
    }
}