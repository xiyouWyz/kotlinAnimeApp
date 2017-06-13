package com.example.wyz.kotlinfirstapp.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.wyz.kotlinfirstapp.App
import com.example.wyz.kotlinfirstapp.R
import com.example.wyz.kotlinfirstapp.domain.model.AnimeList
import com.example.wyz.kotlinfirstapp.domain.network.AnimeSource
import com.example.wyz.kotlinfirstapp.ui.adapter.AnimeListAdapter
import kotlinx.android.synthetic.main.fragment_news.*
import org.jetbrains.anko.async
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread

/**
 * Created by WYZ on 2017/6/12.
 */
class NewsFragment : Fragment(){

    companion object{
        val AIM_URL="http://ishuhui.net/CMS/"
    }
    var mData=ArrayList<AnimeList>()
    lateinit var adapter:AnimeListAdapter
    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_news,container,false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
    }

    private fun  initView(view: View) {
        newsList.layoutManager=LinearLayoutManager(context)
        adapter=AnimeListAdapter{view:View,position:Int -> jump2AnimeDetail()}
        newsList.adapter=adapter
        newsRefresh.setOnRefreshListener { load() }
        newsRefresh.post { newsRefresh.isRefreshing=true }
    }

    private fun jump2AnimeDetail() {
        context.toast("点击NewsFragment")
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if(isVisibleToUser&&mData.size==0){
            load()
        }
    }
    private fun  load(){
        async {
            val data=AnimeSource().obtain(App.BASE_URL,AIM_URL)
            uiThread {
                mData=data
                adapter.refreshData(mData)
                newsRefresh.isRefreshing=false
            }
        }
    }
}