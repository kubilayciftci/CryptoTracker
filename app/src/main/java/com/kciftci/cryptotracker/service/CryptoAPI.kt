package com.kciftci.cryptotracker.service

import com.kciftci.cryptotracker.model.CryptoModel
import retrofit2.Call
import retrofit2.http.GET

interface CryptoAPI {

    @GET("coins/markets?vs_currency=usd&order=market_cap_desc&per_page=100&page=1&sparkline=false")
    fun getData(): Call<List<CryptoModel>>


}