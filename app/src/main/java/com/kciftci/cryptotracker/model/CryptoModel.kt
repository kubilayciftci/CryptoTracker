package com.kciftci.cryptotracker.model

import com.google.gson.annotations.SerializedName

data class CryptoModel(
        val name:String,
        val symbol:String,
        @SerializedName("current_price")
        val price:Double,
        val image:String
        )
