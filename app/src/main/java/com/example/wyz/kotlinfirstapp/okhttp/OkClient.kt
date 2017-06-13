package com.example.wyz.kotlinfirstapp.okhttp

import com.squareup.okhttp.OkHttpClient

/**
 * 单例OkHttpClient
 * Created by WYZ on 2017/6/12.
 */

object OkClient {
    private val client=OkHttpClient()
    fun getInstance()= client;
}