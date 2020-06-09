package com.example.tinkoffsimplenews.appmodel

import com.example.tinkoffsimplenews.datapojo.NewsPreviewsResultPOJO
import com.example.tinkoffsimplenews.datapojo.NewsResultPOJO
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

object TinkoffApiService {
    private val service: APIService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.tinkoff.ru/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            //.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        service = retrofit.create(APIService::class.java)
    }

    fun loadNewsPreviews() : Response<NewsPreviewsResultPOJO?>{
        return service.getNewsPreviews().execute()
    }

    fun loadNews(newsId: Long): Response<NewsResultPOJO?> {
        return service.getNews(newsId).execute()
    }

    interface APIService {
        @GET("https://api.tinkoff.ru/v1/news/")
        fun getNewsPreviews(): Call<NewsPreviewsResultPOJO?>

        @GET("https://api.tinkoff.ru/v1/news_content/")
        fun getNews(@Query("id")newsId:Long) : Call<NewsResultPOJO?>
    }
}