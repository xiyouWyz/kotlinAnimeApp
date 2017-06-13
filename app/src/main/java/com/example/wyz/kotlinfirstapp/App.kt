package com.example.wyz.kotlinfirstapp

import android.app.Application
import com.squareup.picasso.LruCache
import com.squareup.picasso.Picasso

/**
 * Created by WYZ on 2017/6/12.
 */
class App : Application() {

    companion object{
        val BASE_URL="http://ishuhui.net"
    }

    override fun onCreate() {
        super.onCreate()
        val maxMemory=Runtime.getRuntime().maxMemory().toInt()
        Picasso.Builder(this).memoryCache(LruCache(maxMemory/8)).build()
    }
}