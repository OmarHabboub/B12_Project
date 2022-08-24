package com.example.b12_training

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BaseUrl = "https://api.publicapis.org/"
class MainActivity : AppCompatActivity() , OnEntryClickListener {
    lateinit var myAdapter: MyAdapter
    lateinit var linearLayoutManager: LinearLayoutManager
    lateinit var entries : List<entry>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ItemsRycycler.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(this)
        ItemsRycycler.layoutManager = linearLayoutManager
        val retrofitBuilder = Retrofit.Builder().baseUrl(BaseUrl).addConverterFactory(
            GsonConverterFactory.create())
            .build()
            .create(Api::class.java)
        val job = GlobalScope.launch (Dispatchers.IO){
            val retrofitData = retrofitBuilder.getPost()
            if(retrofitData.isSuccessful){
                entries = retrofitData.body()?.entries!!
            }
        }
        runBlocking {
            job.join()
        }
        myAdapter = MyAdapter(baseContext, entries,this)
        myAdapter.notifyDataSetChanged()
        ItemsRycycler.adapter = myAdapter
    }

            override fun OnEntryClicked(position: Int) {
                val intent = Intent(this@MainActivity,DetailsActivity::class.java)
                intent.putExtra("entry",entries[position])
                startActivity(intent)
            }
        }
