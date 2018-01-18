package com.alexperezbuildthatapp.kotlinyoutube

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException
import java.util.*

/**
 * Created by Alex Perez on 1/10/2018.
 */

class CourseDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        //recyclerView_main.setBackgroundColor(Color.RED)
        recyclerView_main.layoutManager = LinearLayoutManager(this)
        recyclerView_main.adapter = CourseDetailAdapter()

        //Changing nav bar title by getting data from other intent object
        val navBarTitle = intent.getStringExtra(CustomViewHolder.VIDEO_TITLE_KEY)
        supportActionBar?.title = navBarTitle

        fetchJSON()

    }

    public fun fetchJSON(){

        val videoID = intent.getIntExtra(CustomViewHolder.VIDEO_ID_KEY, -1)
        val courseDetailURL = "https://api.letsbuildthatapp.com/youtube/course_detail?id=" + videoID

        val client = OkHttpClient()
        val request = Request.Builder().url(courseDetailURL).build()
        client.newCall(request).enqueue(object: Callback{

            override fun onResponse(call: Call?, response: Response?) {
                val body = response?.body()?.string()
                val gson = GsonBuilder().create()
                val courseLessons = gson.fromJson(body,Array<CourseLesson>:: class.java)

                /*println("body " + body)*/
            }

            override fun onFailure(call: Call?, e: IOException?) {

            }
        })

    }

    private class CourseDetailAdapter: RecyclerView.Adapter<CourseLessonViewHolder>(){

        override fun getItemCount(): Int {
            return 4
        }

        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CourseLessonViewHolder {
            val layoutInflater = LayoutInflater.from(parent?.context)
            val customView = layoutInflater.inflate(R.layout.course_lesson_row, parent, false)

            /*val redView = View(parent?.context)
            redView.setBackgroundColor(Color.BLUE)
            redView.minimumHeight = 50*/
            return CourseLessonViewHolder(customView)
        }

        override fun onBindViewHolder(holder: CourseLessonViewHolder?, position: Int) {

        }
    }

    private class CourseLessonViewHolder(val customView: View): RecyclerView.ViewHolder(customView){
    }
}
