package com.alexperezbuildthatapp.kotlinyoutube

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.video_row.view.*

/**
 * Created by Alex Perez on 12/28/2017.
 */

class MainAdapter:RecyclerView.Adapter<CustomViewHolder>(){

    //how to initiate a list
    val videoTitles = listOf("How to Bake", "How to Fish", "How to Be Fly");

    //numberofitems in our recyclerView
    override fun getItemCount(): Int {
        return videoTitles.size
    }

    //Holding our view for the application
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val cellForRow = layoutInflater.inflate(R.layout.video_row, parent, false)
        return CustomViewHolder(cellForRow)
    }

    //Where we bind our data to our listview to be shown
    override fun onBindViewHolder(holder: CustomViewHolder?, position: Int) {
        val videoTitle = videoTitles[position]
        holder?.view?.textView_videoTitle?.text = videoTitle
    }

}

//Using this class to call a custom view to show in our application
class CustomViewHolder(val view: View): RecyclerView.ViewHolder(view){

}
