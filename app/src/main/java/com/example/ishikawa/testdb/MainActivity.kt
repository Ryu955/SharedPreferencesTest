package com.example.ishikawa.testdb

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val arrayList = loadArrayList("test")
        textView(arrayList)

        button.setOnClickListener {
            arrayList.add(editText.text.toString())
            saveArrayList("test",arrayList)
            textView(arrayList)
        }

        saveArrayList("test",arrayList)
    }

    fun saveArrayList(key:String ,arrayList:ArrayList<String>){

        val shardPreferences = this.getPreferences(Context.MODE_PRIVATE)
        val shardPrefEditor = shardPreferences.edit()

        val jsonArray = JSONArray(arrayList)
        shardPrefEditor.putString(key,jsonArray.toString())
        shardPrefEditor.apply()
    }

    fun loadArrayList(key: String) : ArrayList<String> {

        val shardPreferences = this.getPreferences(Context.MODE_PRIVATE)
        val jsonArray = JSONArray(shardPreferences.getString(key, "[]"));
        val arrayList : ArrayList<String> = ArrayList()

        for (i in 0 until jsonArray.length()) {
            arrayList.add(jsonArray.get(i) as String)
        }
        return arrayList
    }

    fun textView(list:ArrayList<String>){
        val BR = System.getProperty("line.separator")
        var viewText=""
        for (i in 0 until list.size){
            viewText += list.get(i).toString() + BR
            Log.i("loadArrayList","[$i] ->" + list.get(i))
        }
        textView.text = viewText
    }
}
