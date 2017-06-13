package com.example.wyz.kotlinfirstapp.domain.network

/**
 * Created by WYZ on 2017/6/12.
 */
interface Source<T> {
    fun  obtain(BASEURL:String,url:String) :T
}