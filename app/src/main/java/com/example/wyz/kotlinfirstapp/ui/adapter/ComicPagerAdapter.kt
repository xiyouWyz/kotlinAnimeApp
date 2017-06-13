package com.example.wyz.kotlinfirstapp.ui.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.wyz.kotlinfirstapp.domain.model.Comic
import com.example.wyz.kotlinfirstapp.ui.fragment.ComicItemFragment

/**
 * Created by WYZ on 2017/6/13.
 */
class ComicPagerAdapter (var data:ArrayList<Comic> = ArrayList(), fragmentManager: FragmentManager)
    :FragmentPagerAdapter(fragmentManager){
    override fun getItem(position: Int): Fragment =newInstance(data[position].imageUrl)

    override fun getCount(): Int =data.size

    fun refreshData(newsData:ArrayList<Comic>){
        data=newsData
        notifyDataSetChanged()
    }
    private  fun newInstance(url:String):Fragment =ComicItemFragment(url)

}