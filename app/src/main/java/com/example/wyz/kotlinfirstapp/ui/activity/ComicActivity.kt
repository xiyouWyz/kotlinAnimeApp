package com.example.wyz.kotlinfirstapp.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.wyz.kotlinfirstapp.App
import com.example.wyz.kotlinfirstapp.R
import com.example.wyz.kotlinfirstapp.domain.model.Comic
import com.example.wyz.kotlinfirstapp.domain.network.ComicDetailSource
import com.example.wyz.kotlinfirstapp.ui.adapter.ComicPagerAdapter
import kotlinx.android.synthetic.main.activity_commic.*
import org.jetbrains.anko.async
import org.jetbrains.anko.uiThread

class ComicActivity : AppCompatActivity() {

    companion object{
        val INTENT_COMIC_URL = "url"
    }
    var mData=ArrayList<Comic>()
    lateinit  var url:String
    lateinit var adapter:ComicPagerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_commic)
        getIntentData()
    }

    private fun getIntentData() {
        url=intent.getStringExtra(INTENT_COMIC_URL)
        adapter= ComicPagerAdapter(mData,supportFragmentManager)
        comicPagers.adapter=adapter
        comicPagers.offscreenPageLimit=2
    }

    override fun onResume() {
        super.onResume()
        async {
            val data=ComicDetailSource().obtain(App.BASE_URL,url)
            uiThread {
                mData=data
                adapter.refreshData(mData)

            }
        }
    }
}
