package com.gmail.eamosse.idbdata.datasources

import android.util.Log
import com.gmail.eamosse.idbdata.api.response.*
import com.gmail.eamosse.idbdata.api.response.CategoryResponse
import com.gmail.eamosse.idbdata.api.response.MovieResponse
import com.gmail.eamosse.idbdata.api.response.PopularResponse
import com.gmail.eamosse.idbdata.api.response.PreviewResponse
import com.gmail.eamosse.idbdata.api.response.TokenResponse
import com.gmail.eamosse.idbdata.api.service.MovieService
import com.gmail.eamosse.idbdata.data.Movie
import com.gmail.eamosse.idbdata.extensions.parse
import com.gmail.eamosse.idbdata.extensions.safeCall
import com.gmail.eamosse.idbdata.utils.Result
import io.reactivex.Single

/**
 * Manipule les données de l'application en utilisant un web service
 * Cette classe est interne au module, ne peut être initialisé ou exposé aux autres composants
 * de l'application
 */
internal class OnlineDataSource(private val service: MovieService) {

    /**
     * Récupérer le token du serveur
     * @return [Result<Token>]
     * Si [Result.Succes], tout s'est bien passé
     * Sinon, une erreur est survenue
     */
    suspend fun getToken(): Result<TokenResponse> {
        return safeCall {
            val response = service.getToken()
            response.parse()
        }
    }



    suspend fun getMovieById(id:Int): Result<MovieResponse> {
        return safeCall {
            val response = service.getMovieById(id)
            response.parse()
        }
    }

    suspend fun getCategories(): Result<List<CategoryResponse.Genre>> {
        return try {
            val response = service.getCategories()
            if (response.isSuccessful) {
                Result.Succes(response.body()!!.genres)
            } else {
                Result.Error(
                    exception = Exception(),
                    message = response.message(),
                    code = response.code()
                )
            }
        } catch (e: Exception) {
            Result.Error(
                exception = e,
                message = e.message ?: "No message",
                code = -1
            )
        }
    }

suspend fun getPreviews(categoryId: Int, page: Int): Result<List<PreviewResponse.PreviewData>> {
    return try {
        val response = service.getPreviews(categoryId, page)
        if (response.isSuccessful) {
            Result.Succes(response.body()!!.previews)
        } else {
            Result.Error(
                exception = Exception(),
                message = response.message(),
                code = response.code()
            )
        }
    } catch (e: Exception) {
        Result.Error(
            exception = e,
            message = e.message ?: "No message",
            code = -1
        )
    }
}

    suspend fun getPopulars(page: Int): Result<List<PopularResponse.PopularData>> {
        return try {
            val response = service.getPopulars(page)
            if (response.isSuccessful) {
                Result.Succes(response.body()!!.populars)
            } else {
                Result.Error(
                    exception = Exception(),
                    message = response.message(),
                    code = response.code()
                )
            }
        } catch (e: Exception) {
            Result.Error(
                exception = e,
                message = e.message ?: "No message",
                code = -1
            )
        }
    }
}
