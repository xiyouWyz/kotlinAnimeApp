package com.example.wyz.kotlinfirstapp.domain.network

import com.example.wyz.kotlinfirstapp.domain.model.BookInfo
import com.example.wyz.kotlinfirstapp.okhttp.getHtml
import org.jsoup.Jsoup

/**
 * Created by WYZ on 2017/6/13.
 */
class BookInfoSource :Source<ArrayList<BookInfo>> {
    override fun obtain(BASEURL: String, url: String): ArrayList<BookInfo> {
        val html = getHtml(url)
        val doc = Jsoup.parse(html)

        val pages = java.util.ArrayList<BookInfo>()
        val elements = doc.select("div.volumeControl").select("a")

        for (element in elements) {
            val title = element.text()
            val link = BASEURL + element.attr("href")
            val page = BookInfo(title, link)
            pages.add(page)
        }
        return pages
    }
}