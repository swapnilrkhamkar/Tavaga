package com.assignment.tavaga.urlInterface

import com.assignment.tavaga.model.Image
import io.reactivex.Single
import retrofit2.http.GET

interface APIInterface {

    @GET("photos")
    fun getImages(): Single<ArrayList<Image>>
}


