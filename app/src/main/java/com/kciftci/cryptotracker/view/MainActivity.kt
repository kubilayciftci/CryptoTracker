package com.kciftci.cryptotracker.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kciftci.cryptotracker.R
import com.kciftci.cryptotracker.adapter.RecyclerViewAdapter
import com.kciftci.cryptotracker.model.CryptoModel
import com.kciftci.cryptotracker.service.CryptoAPI
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), RecyclerViewAdapter.Listener {

    private val TAG = "MainActivity"
    private val BASE_URL = "https://api.coingecko.com/api/v3/"
    private var cryptoModelList: ArrayList<CryptoModel>? = null
    private var recylerViewAdapter: RecyclerViewAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

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
            override fun onResponse(
                call: Call<List<CryptoModel>>,
                response: Response<List<CryptoModel>>
            ) {

                if (response.isSuccessful) {
                    response.body()?.let {
                        cryptoModelList = ArrayList(it)

                        cryptoModelList?.let {
                            recylerViewAdapter = RecyclerViewAdapter(it, this@MainActivity)
                            recyclerView.adapter = recylerViewAdapter
                        }
                    }
                }

            }

            override fun onFailure(call: Call<List<CryptoModel>>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }

    override fun onItemClickListener(cryptoModel: CryptoModel) {
        Toast.makeText(this, "Clicked:${cryptoModel.name}", Toast.LENGTH_SHORT).show()
    }

}