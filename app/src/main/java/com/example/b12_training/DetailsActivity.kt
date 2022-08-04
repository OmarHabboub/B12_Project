package com.example.b12_training

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_details.*
import java.util.*

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        val entry = intent.extras
        val API = entry?.get("API").toString()
        val Desc = entry?.get("Desc").toString()
        val Cate = entry?.get("Cate").toString()
        APItv.text = "API :$API"
        DescTV.text = "Desc :$Desc"
        CateTv.text = "Cate :$Cate"
    }

}