package com.example.wyz.kotlinfirstapp.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.wyz.kotlinfirstapp.R
import com.example.wyz.kotlinfirstapp.domain.model.BookInfo
import com.example.wyz.kotlinfirstapp.ui.adapter.BookInfoAdapter.BookInfoViewHolder
import kotlinx.android.synthetic.main.item_book_info.view.*

/**
 * Created by WYZ on 2017/6/13.
 */
class BookInfoAdapter(var data:List<BookInfo> =ArrayList<BookInfo>(),var itemClick :(view:View,position:Int)->Unit)
    :RecyclerView.Adapter<BookInfoViewHolder>(){
    override fun onBindViewHolder(holder: BookInfoViewHolder, position: Int) {
        bindView(holder.itemView,position)
    }

    private fun  bindView(itemView: View, position: Int) {
        itemView.tv_page.text=data[position].text
        itemView.tv_page_container.setOnClickListener { itemClick(itemView,position) }
    }

    override fun getItemCount(): Int =data.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): BookInfoViewHolder{
        val itemView=LayoutInflater.from(parent?.context).inflate(R.layout.item_book_info,parent,false)
        return  BookInfoViewHolder(itemView)
    }

    class BookInfoViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {

    }
    fun refreshData(newsData:List<BookInfo>){
        data=newsData
        notifyDataSetChanged()
    }


}