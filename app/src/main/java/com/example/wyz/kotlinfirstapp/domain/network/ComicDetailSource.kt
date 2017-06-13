package com.example.wyz.kotlinfirstapp.domain.network

import com.example.wyz.kotlinfirstapp.domain.model.Comic
import com.example.wyz.kotlinfirstapp.okhttp.getHtml
import org.jsoup.Jsoup

/**
 * Created by WYZ on 2017/6/13.
 */
class ComicDetailSource :Source<ArrayList<Comic>>{
    override fun obtain(BASEURL: String, url: String): ArrayList<Comic> {
        val list=ArrayList<Comic>()
        val html= getHtml(url)

        val doc =Jsoup.parse(html)
        val elements=doc.select("div.mangaContentMainImg").select("img")
        for(element in elements){
            var comicUrl: String
            val temp = element.attr("src")
            if (temp.contains(".png") || temp.contains(".jpg") || temp.contains(".JPEG")) {
                comicUrl = temp
            } else if (!element.attr("data-original").equals("")) {
                comicUrl = element.attr("data-original")
            } else {
                continue
            }
            val comic = Comic(comicUrl)
            list.add(comic)
        }
        return  list
    }
}