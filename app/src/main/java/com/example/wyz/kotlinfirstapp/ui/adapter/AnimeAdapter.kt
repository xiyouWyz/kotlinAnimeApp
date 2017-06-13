package com.example.wyz.kotlinfirstapp.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.wyz.kotlinfirstapp.R
import com.example.wyz.kotlinfirstapp.domain.model.AnimeInfo
import kotlinx.android.synthetic.main.item_anime_info.view.*
/**
 * Created by WYZ on 2017/6/13.
 */
class AnimeAdapter(var data:List<AnimeInfo> =ArrayList())
    : RecyclerView.Adapter<AnimeAdapter.AnimeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): AnimeViewHolder {
        val itemView=LayoutInflater.from(parent?.context).inflate(R.layout.item_anime_info,parent,false)
        return  AnimeViewHolder(itemView)
    }
    override fun getItemCount(): Int =data.size

    override fun onBindViewHolder(holder: AnimeViewHolder, position: Int) {
            bindView(holder.itemView,position)

    }

    private fun  bindView(itemView: View, position: Int) {
        val animeInfo=data[position]
        itemView.tv_title.text=animeInfo.title_news
        itemView.tv_time.text=animeInfo.updateName
        if(position%2==0){
            itemView.container.setBackgroundResource(R.color.alpha_grey)
        }
        itemView.container.setOnClickListener {  }

    }

    class AnimeViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {

    }
    fun refreshData(newsData:List<AnimeInfo>){
        data=newsData
        notifyDataSetChanged()
    }
}