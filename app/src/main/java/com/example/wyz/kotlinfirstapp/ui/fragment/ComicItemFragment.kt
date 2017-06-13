package com.example.wyz.kotlinfirstapp.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.wyz.kotlinfirstapp.R
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_comic.*

/**
 * Created by WYZ on 2017/6/13.
 */
class ComicItemFragment(val url:String): Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view=inflater.inflate(R.layout.item_comic,container,false)
        return  view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
    }

    private fun  initView(view: View) {
        progressBar.visibility=View.VISIBLE
    }

    override fun onResume() {
        super.onResume()
        Picasso.with(context)
                .load(url)
                .placeholder(R.color.material_deep_purple_50)
                .into(iv_comic,object  :Callback{
                    override fun onSuccess() {
                        progressBar.visibility = View.GONE
                    }

                    override fun onError() {

                    }

                })
    }
}