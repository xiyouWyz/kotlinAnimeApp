package com.example.wyz.kotlinfirstapp.ui.fragment
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.wyz.kotlinfirstapp.App
import com.example.wyz.kotlinfirstapp.R
import com.example.wyz.kotlinfirstapp.domain.model.Cover
import com.example.wyz.kotlinfirstapp.domain.network.CoverSource
import com.example.wyz.kotlinfirstapp.ui.activity.ComicActivity
import com.example.wyz.kotlinfirstapp.ui.adapter.CoverAdapter
import kotlinx.android.synthetic.main.fragment_home.*
import org.jetbrains.anko.async
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread

/**
 * Created by WYZ on 2017/6/12.
 */
class HomeFragment : Fragment(){
    companion object{
        val AIM_URL="http://ishuhui.net/?PageIndex=1"
    }
    var mData= ArrayList<Cover>()
   /* lateinit  var  coverList:RecyclerView
    lateinit  var homeRefresh:SwipeRefreshLayout*/
    lateinit  var adapter:CoverAdapter
    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
    }

    private fun  initView(view: View) {
        homeList.layoutManager=GridLayoutManager(context,2)
        adapter=CoverAdapter{view:View,position:Int->jump2Comic(position)}
        homeList.adapter=adapter
        homeRefresh.setOnClickListener { load() }
        homeRefresh.post { homeRefresh.isRefreshing=true }

    }

    private fun  jump2Comic(position: Int) {
        context.toast("点击HomeFragment")
        var intent=Intent(context,ComicActivity().javaClass)
        intent.putExtra(ComicActivity.INTENT_COMIC_URL,mData[position].link)
        startActivity(intent)
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        //懒加载
        if(isVisibleToUser&&mData.size==0){
            load()
        }
    }

    private fun load() {
        async {
            val data=CoverSource().obtain(App.BASE_URL,AIM_URL)
            uiThread {
                mData=data
                adapter.refreshData(mData)
                homeRefresh.isRefreshing=false
            }
        }
    }

}