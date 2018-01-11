package com.alexperezbuildthatapp.kotlinyoutube

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_main.*

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
