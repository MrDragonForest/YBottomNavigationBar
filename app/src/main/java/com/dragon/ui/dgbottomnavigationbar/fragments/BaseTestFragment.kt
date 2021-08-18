package com.dragon.ui.dgbottomnavigationbar.fragments

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.dragon.ui.dgbottomnavigationbar.R

/***
 *  create by DragonForest on 2021/8/13
 */
open class BaseTestFragment(var mtag:String?="测试123") : Fragment() {
    var TAG = this.javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG,"onCreate")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG,"onResume")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = LayoutInflater.from(context).inflate(R.layout.fragment_test1, container, false)
        Log.d(TAG,"onCreateView")
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.setBackgroundColor(getBackgroundColor())
        view.findViewById<TextView>(R.id.tv_title).text = getTitle()
    }

    open fun getTitle():String?{
        return mtag
    }

    open fun getBackgroundColor():Int{
        return Color.WHITE
    }



}