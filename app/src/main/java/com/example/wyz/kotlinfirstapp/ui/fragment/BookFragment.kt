package com.example.wyz.kotlinfirstapp.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.wyz.kotlinfirstapp.App
import com.example.wyz.kotlinfirstapp.R
import com.example.wyz.kotlinfirstapp.domain.model.Cover
import com.example.wyz.kotlinfirstapp.domain.network.ComicListSource
import com.example.wyz.kotlinfirstapp.ui.activity.BookDetailActivity
import com.example.wyz.kotlinfirstapp.ui.adapter.CoverAdapter
import kotlinx.android.synthetic.main.fragment_book.*
import org.jetbrains.anko.async
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread

/**
 * Created by WYZ on 2017/6/12.
 */
class BookFragment : Fragment(){


    companion object{
        val AIM_URL="http://ishuhui.net/ComicBookList/"
    }
    var mData= ArrayList<Cover>()
    lateinit  var adapter: CoverAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.retainInstance = true
    }
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_book, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
    }

    private fun  initView(view: View) {
        bookList.layoutManager= GridLayoutManager(context,2)
        adapter= CoverAdapter{ view:View, position:Int->jump2Comic(position)}
        bookList.adapter=adapter
        bookRefresh.setOnClickListener { load() }
        bookRefresh.post { bookRefresh.isRefreshing=true }

    }

    private fun  jump2Comic(position: Int) {
        context.toast("点击BookFragment")
        var intent= Intent(context, BookDetailActivity().javaClass)
        intent.putExtra(BookDetailActivity.INTENT_URL,mData[position].link)
        intent.putExtra(BookDetailActivity.INTENT_COVER_URL,mData[position].coverUrl)
        intent.putExtra(BookDetailActivity.INTENT_TITLE,mData[position].title)
        startActivity(intent)
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        //懒加载
        if(isVisibleToUser&&mData.size==0){
            load()
        }
    }

    private fun load() {
        async {
            val data= ComicListSource().obtain(App.BASE_URL,AIM_URL)
            uiThread {
                mData=data
                adapter.refreshData(mData)
                bookRefresh.isRefreshing=false
            }
        }
    }
}