package com.example.b12_training

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BaseUrl = "https://api.publicapis.org/"
class MainActivity : AppCompatActivity() {
    lateinit var myAdapter: MyAdapter
    lateinit var linearLayoutManager: LinearLayoutManager
    lateinit var entries : List<entry>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ItemsRycycler.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(this)
        ItemsRycycler.layoutManager = linearLayoutManager
        getData()
    }

    private fun getData() {
        val retrofitBuilder = Retrofit.Builder().baseUrl(BaseUrl).addConverterFactory(
            GsonConverterFactory.create())
            .build()
            .create(Api::class.java)

        val retrofitData = retrofitBuilder.getPost()

        retrofitData.enqueue(object : Callback<response?>, OnEntryClickListener {
            override fun onResponse(call: Call<response?>, response: Response<response?>) {
                val responseBody = response.body()
                myAdapter = MyAdapter(baseContext, responseBody!!.entries,this)
                myAdapter.notifyDataSetChanged()
                ItemsRycycler.adapter = myAdapter
                entries = responseBody!!.entries

            }

            override fun onFailure(call: Call<response?>, t: Throwable) {
                Log.d("MainActivity","onFailuredddddddddddddddddddddddddddddddddddddddddddddddddddd")
            }

            override fun OnEntryClicked(position: Int) {
                val intent = Intent(this@MainActivity,DetailsActivity::class.java)
                intent.putExtra("entry",entries[position])
                startActivity(intent)
            }
        })


    }
}