package com.example.wyz.kotlinfirstapp.domain.model

/**
 * Created by WYZ on 2017/6/12.
 */

data class Cover(val coverUrl:String,val title:String,val link:String)


data class AnimeInfo(val title_news: String,val updateName: String,val link: String)

data class AnimeList(val title: String,val more_url:String,val animeList:List<AnimeInfo>)

data class Comic(val imageUrl:String)

data class  BookInfo(val text:String,val url:String)
