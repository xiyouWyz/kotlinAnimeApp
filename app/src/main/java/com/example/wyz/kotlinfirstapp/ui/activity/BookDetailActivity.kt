package com.example.wyz.kotlinfirstapp.ui.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.view.MenuItem
import android.view.View
import com.example.wyz.kotlinfirstapp.App
import com.example.wyz.kotlinfirstapp.R
import com.example.wyz.kotlinfirstapp.domain.model.BookInfo
import com.example.wyz.kotlinfirstapp.domain.network.BookInfoSource
import com.example.wyz.kotlinfirstapp.ui.adapter.BookInfoAdapter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_book_detail.*
import org.jetbrains.anko.async
import org.jetbrains.anko.uiThread

class BookDetailActivity : AppCompatActivity() {

    lateinit  var coverUrl:String
    lateinit  var link:String
    lateinit  var title:String
    lateinit  var adapter:BookInfoAdapter

    var mData:List<BookInfo> =ArrayList<BookInfo>()
    companion object {
        val INTENT_COVER_URL = "cover"
        val INTENT_URL = "url"
        val INTENT_TITLE = "title"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_detail)
        getIntentData()
    }

    private fun getIntentData() {
        link=intent.getStringExtra(INTENT_URL)
        title=intent.getStringExtra(INTENT_TITLE)
        coverUrl=intent.getStringExtra(INTENT_COVER_URL)
        initView()
    }

    private fun initView() {
        toolbar.title=title
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        Picasso.with(this)
                .load(coverUrl)
                .into(image)
        adapter= BookInfoAdapter{ view:View,position:Int ->jump2bookInfoDetail(position)}

        recyclerView.layoutManager=GridLayoutManager(this,5)
        recyclerView.adapter=adapter

        bookDetailRefresh.setOnRefreshListener { load() }


    }

    override fun onResume() {
        super.onResume()
        bookDetailRefresh.post { bookDetailRefresh.isRefreshing=true }
        load()
    }
    private fun load() {
        async {
            val data=BookInfoSource().obtain(App.BASE_URL,link)
            mData=data
            uiThread {

                adapter.refreshData(mData)
                bookDetailRefresh.isRefreshing=false
            }
        }
    }

    private fun jump2bookInfoDetail(position: Int ) {
        val url=mData[position].url
        val intent=Intent(this,ComicActivity().javaClass)
        intent.putExtra(ComicActivity.INTENT_COMIC_URL,url)
        startActivity(intent)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item?.itemId==android.R.id.home){
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}
