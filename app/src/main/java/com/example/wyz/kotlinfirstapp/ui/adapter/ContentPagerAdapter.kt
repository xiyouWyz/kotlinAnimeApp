package com.example.wyz.kotlinfirstapp.ui.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.view.ViewGroup

/**
 * Created by WYZ on 2017/6/12.
 */
class ContentPagerAdapter(val  fragments:List<Fragment>,val nameList: List<String>,val fm:FragmentManager):FragmentPagerAdapter(fm) {



    override fun getCount(): Int=fragments.size


   /* override fun getItem(position: Int): Fragment {
        return fragments[position]
    }*/
    override fun getItem(position: Int): Fragment=fragments[position]

    override fun getPageTitle(position: Int): CharSequence= nameList.get(position)

    override fun destroyItem(container: ViewGroup?, position: Int, `object`: Any?) {
        super.destroyItem(container, position, `object`)
    }



}