package com.example.wyz.kotlinfirstapp.okhttp

import com.squareup.okhttp.Request

/**
 * Created by WYZ on 2017/6/12.
 */
fun getHtml(url:String):String{
    val client=OkClient.getInstance()
    val request= Request.Builder()
            .url(url)
            .build()
    val response=client.newCall(request).execute()
    return  response.body().string()

}