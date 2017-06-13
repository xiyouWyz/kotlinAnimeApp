package com.example.wyz.kotlinfirstapp.domain.network

import com.example.wyz.kotlinfirstapp.domain.model.AnimeInfo
import com.example.wyz.kotlinfirstapp.domain.model.AnimeList
import com.example.wyz.kotlinfirstapp.okhttp.getHtml
import org.jsoup.Jsoup

/**
 * Created by WYZ on 2017/6/13.
 */
class AnimeSource :Source<ArrayList<AnimeList>>{
    override fun obtain(BASEURL: String, url: String): ArrayList<AnimeList> {
        val animeList=ArrayList<AnimeList>()
        val html= getHtml(url)
        val doc=Jsoup.parse(html)
        val elements=doc.select("div.reportersBox").select("div.reportersMain")
        for (element in elements){
            val title=element.select("div.mangeListTitle").select("span").text()
            val more_url=BASEURL+element.select("div.mangeListTitle").select("a").attr("href")
            val ul=element.select("ul.reportersList").select("li").select("a")
            val list=ArrayList<AnimeInfo>()

            for(ele in ul){
                val title_news=ele.select("span")[0].text()
                val updateTime_news=ele.select("span")[1].text()
                val link=BASEURL+ele.attr("href")
                val anime=AnimeInfo(title_news,updateTime_news,link)
                list.add(anime)
            }
            animeList.add(AnimeList(title,more_url,list))
        }
        return  animeList
    }

}