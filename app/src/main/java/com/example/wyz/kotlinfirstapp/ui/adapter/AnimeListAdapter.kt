package com.example.wyz.kotlinfirstapp.ui.adapter

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.wyz.kotlinfirstapp.R
import com.example.wyz.kotlinfirstapp.domain.model.AnimeList
import kotlinx.android.synthetic.main.item_anime_list.view.*

/**
 * Created by WYZ on 2017/6/13.
 */
class AnimeListAdapter(var data:List<AnimeList> =ArrayList(),var itemClick:(View,Int)->Unit)
    :RecyclerView.Adapter<AnimeListAdapter.AnimeListViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): AnimeListViewHolder {
        val itemView = LayoutInflater.from(parent?.context).inflate(R.layout.item_anime_list, parent, false)
        return  AnimeListViewHolder(itemView)
    }


    override fun onBindViewHolder(holder: AnimeListViewHolder, position: Int) {
        bindView(holder.itemView,position)

    }

    private fun  bindView(itemView: View, position: Int) {
        val animeContainer=data[position]
        itemView.tv_container_title.text=animeContainer.title
        itemView.rv_child_container.layoutManager=LinearLayoutManager(itemView.context)
        itemView.rv_child_container.adapter=AnimeAdapter(animeContainer.animeList)
        itemView.cardView.setOnClickListener { itemClick(itemView,position) }

    }

    override fun getItemCount(): Int=data.size
    class AnimeListViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView) {

    }
    fun refreshData(newData:List<AnimeList>){
        data=newData
        notifyDataSetChanged()
    }
}