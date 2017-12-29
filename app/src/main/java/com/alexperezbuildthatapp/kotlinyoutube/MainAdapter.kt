package com.alexperezbuildthatapp.kotlinyoutube

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup

/**
 * Created by Alex Perez on 12/28/2017.
 */

class MainAdapter:RecyclerView.Adapter<customViewHolder>(){

    //numberofitems
    override fun getItemCount(): Int {
        return 1
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): customViewHolder {
        return parent
    }

    override fun onBindViewHolder(holder: customViewHolder?, position: Int) {

    }

}

class customViewHolder(v: View): RecyclerView.ViewHolder(v){

}
