package com.example.wyz.kotlinfirstapp.ui.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.example.wyz.kotlinfirstapp.R
import com.example.wyz.kotlinfirstapp.ui.adapter.ContentPagerAdapter
import com.example.wyz.kotlinfirstapp.ui.fragment.BookFragment
import com.example.wyz.kotlinfirstapp.ui.fragment.HomeFragment
import com.example.wyz.kotlinfirstapp.ui.fragment.NewsFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {

    val nameResList: ArrayList<Int> = arrayListOf(R.string.tab_one, R.string.tab_two, R.string.tab_three)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        setSupportActionBar(toolbar)
        toolbar.setTitle(R.string.app_name)
        fab.setOnClickListener { fabClick() }
        val fragments=ArrayList<Fragment>()

        fragments.add(HomeFragment())
        fragments.add(BookFragment())
        fragments.add(NewsFragment())

        val nameList=nameResList.map(this:: getString)
        viewPager.adapter=ContentPagerAdapter(fragments,nameList,supportFragmentManager)
        viewPager.offscreenPageLimit=2

        tabLayout.setupWithViewPager(viewPager)

    }

    private fun fabClick() {
        toast("点击fab")

    }
}
