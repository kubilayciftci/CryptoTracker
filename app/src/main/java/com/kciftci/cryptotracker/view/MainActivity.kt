package com.kciftci.cryptotracker.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kciftci.cryptotracker.R
import com.kciftci.cryptotracker.model.CryptoModel
import com.kciftci.cryptotracker.service.CryptoAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"
    private val BASE_URL = "https://api.coingecko.com/api/v3/"
    private var cryptoModelList: ArrayList<CryptoModel>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fetchData()

    }


    private fun fetchData() {
        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val service = retrofit.create(CryptoAPI::class.java)
        val call = service.getData()

        call.enqueue(object : Callback<List<CryptoModel>> {
            override fun onResponse(call: Call<List<CryptoModel>>, response: Response<List<CryptoModel>>) {

                if (response.isSuccessful) {
                    response.body()?.let {
                        cryptoModelList = ArrayList(it)

                        for (crypto: CryptoModel in cryptoModelList!!) {
                            println("----------")
                            println(crypto.name)
                            println(crypto.price)
                            println(crypto.symbol)
                            println(crypto.image)
                        }
                    }
                }

            }

            override fun onFailure(call: Call<List<CryptoModel>>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }

}