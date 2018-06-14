package ru.pristalovpavel.personal.friendlyjenkins.repository.web

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

enum class RetrofitSingleton{
    INSTANCE;

    val BASE_URL = "https://jenkins.altarix.ru"
    var retrofit : Retrofit

    fun init() {
        if (retrofit == null) {
                retrofit = Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
            }
    }


}