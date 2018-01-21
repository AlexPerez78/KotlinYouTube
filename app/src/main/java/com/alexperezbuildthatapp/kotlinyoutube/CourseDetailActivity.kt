package com.alexperezbuildthatapp.kotlinyoutube

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.gson.GsonBuilder
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.course_lesson_row.view.*
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
        recyclerView_main.layoutManager = LinearLayoutManager(this)

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

                runOnUiThread{
                    recyclerView_main.adapter = CourseDetailAdapter(courseLessons)
                }
                /*println("body " + body)*/
            }

            override fun onFailure(call: Call?, e: IOException?) {

            }
        })

    }

    private class CourseDetailAdapter(val courseLessons: Array<CourseLesson>): RecyclerView.Adapter<CourseLessonViewHolder>(){

        override fun getItemCount(): Int {
            return courseLessons.size
        }

        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CourseLessonViewHolder {
            val layoutInflater = LayoutInflater.from(parent?.context)
            val customView = layoutInflater.inflate(R.layout.course_lesson_row, parent, false)

            return CourseLessonViewHolder(customView)
        }

        override fun onBindViewHolder(holder: CourseLessonViewHolder?, position: Int) {
            val courseLesson = courseLessons.get(position)

            holder?.customView?.textView_course_lesson_title?.text = courseLesson.name
            holder?.customView?.textView_duration?.text = courseLesson.duration

            val imageView = holder?.customView?.imageView_course_lesson_thumbnail
            Picasso.with(holder?.customView?.context).load(courseLesson.imageUrl).into(imageView)

            holder?.courseLesson = courseLesson
        }
    }

    class CourseLessonViewHolder(val customView: View, var courseLesson: CourseLesson? = null): RecyclerView.ViewHolder(customView){

        companion object {
            val COURSE_LESSON_LINK_KEY = "COURSE_LESSON_LINK"
        }

        init {
            customView.setOnClickListener {
                Toast.makeText(customView.context,"Show Webview",Toast.LENGTH_SHORT).show()

                val intent = Intent(customView.context, CourseLessonActivity::class.java)

                intent.putExtra(COURSE_LESSON_LINK_KEY,courseLesson?.link)
                customView.context.startActivity(intent)
            }
        }
    }
}
