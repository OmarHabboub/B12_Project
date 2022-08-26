package com.example.b12_training

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_details.*
import java.util.*

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        val entry = intent.getSerializableExtra("entry") as entry
        APItv.text = "API :${entry.API}"
        DescTV.text = "Desc :${entry.Description}"
        CateTv.text = "Cate :${entry.Category}"

    }

}