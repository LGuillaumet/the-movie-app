package com.gmail.eamosse.idbdata.api.service

import com.gmail.eamosse.idbdata.api.response.*
import com.gmail.eamosse.idbdata.api.response.CategoryResponse
import com.gmail.eamosse.idbdata.api.response.MovieResponse
import com.gmail.eamosse.idbdata.api.response.PopularResponse
import com.gmail.eamosse.idbdata.api.response.PreviewResponse
import com.gmail.eamosse.idbdata.api.response.TokenResponse
import com.gmail.eamosse.idbdata.data.Movie
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

internal interface MovieService {
    @GET("authentication/token/new")
    suspend fun getToken(): Response<TokenResponse>

    @GET("genre/movie/list")
    suspend fun getCategories(): Response<CategoryResponse>

    @GET("discover/movie")
    suspend fun getPreviews(@Query("with_genres") categoryId:Int, @Query("page") page:Int): Response<PreviewResponse>

    @GET("movie/popular")
    suspend fun getPopulars(@Query("page") page:Int): Response<PopularResponse>

    @GET("movie/{movie}")
    suspend fun getMovieById(@Path("movie") movie:Int): Response<MovieResponse>
}