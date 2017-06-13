package com.example.wyz.kotlinfirstapp.domain.network

import com.example.wyz.kotlinfirstapp.domain.model.Cover
import com.example.wyz.kotlinfirstapp.okhttp.getHtml
import org.jsoup.Jsoup

/**
 * Created by WYZ on 2017/6/12.
 */
class ComicListSource :Source<ArrayList<Cover>>{
    override fun obtain(BASEURL: String, url: String): ArrayList<Cover> {
        val list= ArrayList<Cover>()
        //val client=OkClient.getInstance()
        val html=getHtml(url)
        val doc=Jsoup.parse(html)
        val elements = doc.select("ul.chinaMangaContentList").select("li")

        for (element in elements) {
            val coverUrl = if (element.select("img").attr("src").contains("http")) {
                element.select("img").attr("src")
            } else {
                BASEURL + element.select("img").attr("src")
            }

            val title = element.select("p").text()
            val link = BASEURL + element.select("div.chinaMangaContentImg").select("a").attr("href")

            val cover = Cover(coverUrl, title, link)
            list.add(cover)
        }
        return list    }


}