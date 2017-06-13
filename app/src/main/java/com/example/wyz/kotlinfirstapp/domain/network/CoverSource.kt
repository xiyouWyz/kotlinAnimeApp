package com.example.wyz.kotlinfirstapp.domain.network

import com.example.wyz.kotlinfirstapp.domain.model.Cover
import com.example.wyz.kotlinfirstapp.okhttp.getHtml
import org.jsoup.Jsoup


/**
 * Created by WYZ on 2017/6/12.
 */
class CoverSource() : Source<ArrayList<Cover>> {
    override fun obtain(BASEURL: String, url: String): ArrayList<Cover> {
        val list=ArrayList<Cover>()
        val html= getHtml(url)
        val doc = Jsoup.parse(html)

        val elements = doc.select("ul.mangeListBox").select("li")
        for (element in elements) {
            val coverUrl = element.select("img").attr("src")
            val title = element.select("h1").text() + "\n" + element.select("h2").text()
            val link = BASEURL + element.select("div.magesPhoto").select("a").attr("href")
            val cover = Cover(coverUrl, title, link)
            list.add(cover)
        }

        return list
    }
}