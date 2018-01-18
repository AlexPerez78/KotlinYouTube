package com.alexperezbuildthatapp.kotlinyoutube

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.video_row.view.*

/**
 * Created by Alex Perez on 12/28/2017.
 */

class MainAdapter(val homeFeed: HomeFeed):RecyclerView.Adapter<CustomViewHolder>(){

    //how to initiate a list
    val videoTitles = listOf("How to Bake", "How to Fish", "How to Be Fly");

    //numberofitems in our recyclerView
    override fun getItemCount(): Int {
        return homeFeed.videos.count()
    }

    //Holding our view for the application
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val cellForRow = layoutInflater.inflate(R.layout.video_row, parent, false)
        return CustomViewHolder(cellForRow)
    }

    //Where we bind our data to our listview to be shown
    override fun onBindViewHolder(holder: CustomViewHolder?, position: Int) {
        /*val videoTitle = videoTitles.get(position)*/
        val video =  homeFeed.videos[position]
        holder?.view?.textView_videoTitle?.text = video.name

        holder?.view?.textView_channelName?.text = video.channel.name

        val thumbnailImageView = holder?.view?.imageView_videoThumbnail
        Picasso.with(holder?.view?.context).load(video.imageUrl).into(thumbnailImageView)

        val channelProfileView = holder?.view?.imageView_channelProfile
        Picasso.with(holder?.view?.context).load(video.channel.profileImageUrl).into(channelProfileView)

        holder?.video = video
    }

}

//Using this class to call a custom view to show in our application
class CustomViewHolder(val view: View, var video: Video? = null): RecyclerView.ViewHolder(view){

    companion object {
        val VIDEO_TITLE_KEY = "VIDEO_TITLE"
        val VIDEO_ID_KEY = "VIDEO_ID"

    }

    //Init, used to initiate a reaction with the view
    init {
        view.setOnClickListener{
            val intent = Intent(view.context, CourseDetailActivity::class.java)

            intent.putExtra(VIDEO_TITLE_KEY, video?.name)
            intent.putExtra(VIDEO_ID_KEY, video?.id)

            view.context.startActivity(intent)
            Toast.makeText(view.context, "Clicked on Video", Toast.LENGTH_LONG).show()
        }
    }

}