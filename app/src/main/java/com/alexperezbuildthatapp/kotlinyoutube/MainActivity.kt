package com.alexperezbuildthatapp.kotlinyoutube

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView_main.layoutManager = LinearLayoutManager(this)
        /*recyclerView_main.adapter = MainAdapter()*/

        fetchJson()
    }

    fun fetchJson() {
        println("Attempting to Load Json Data")

        val url = "https://api.letsbuildthatapp.com/youtube/home_feed"
        //Documentation: https://square.github.io/okhttp/
        val request = Request.Builder().url(url).build()

        val client = OkHttpClient()
        //Running on background thread
        client.newCall(request).enqueue(object: Callback{
            override fun onResponse(call: Call?, response: Response?) {
                val body = response?.body()?.string()
                println(body)

                val gson = GsonBuilder().create()

                val homeFeed = gson.fromJson(body,HomeFeed::class.java)

                /*Enque is running on the background thread, only the "orginal" ui thread
                creator is able to touch its main views, therefore we must use RunOnUiThread to updare
                main view*/
                runOnUiThread {
                    recyclerView_main.adapter = MainAdapter(homeFeed)
                }

            }

            override fun onFailure(call: Call?, e: IOException?) {
                println("Failed to execute request")
            }

        })

    }
}