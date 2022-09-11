package com.example.b12_training

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_recycler.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [recycler.newInstance] factory method to
 * create an instance of this fragment.
 */
class recycler : Fragment() , OnEntryClickListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var myAdapter: MyAdapter
    lateinit var linearLayoutManager: LinearLayoutManager
    lateinit var entries : List<entry>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ItemsRycycler.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(activity)
        ItemsRycycler.layoutManager = linearLayoutManager
        val entryBase = dataBase.getDatabase(activity)
        val entryDao = entryBase.entryDao()
        val retrofitBuilder = Retrofit.Builder().baseUrl(BaseUrl).addConverterFactory(
            GsonConverterFactory.create())
            .build()
            .create(Api::class.java)
        var job1=GlobalScope.launch {
            entries=entryDao.getEntries()
        }
        runBlocking {
            job1.join()
        }
        if(entries.size!=0){
            myAdapter = MyAdapter(activity, entries,this)
            myAdapter.notifyDataSetChanged()
            ItemsRycycler.adapter = myAdapter
        }

        val job2 = GlobalScope.launch (Dispatchers.IO){
            val retrofitData = retrofitBuilder.getPost()
            if(retrofitData.isSuccessful){
                entries = retrofitData.body()!!.entries
                entryDao.insertEntries(entries)
            }
        }
        runBlocking {
            job2.join()}

        myAdapter = MyAdapter(activity, entries,this)
        myAdapter.notifyDataSetChanged()
        ItemsRycycler.adapter = myAdapter
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recycler, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment recycler.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            recycler().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun OnEntryClicked(position: Int) {
        val intent = Intent(activity ,DetailsActivity::class.java)
        intent.putExtra("entry",entries[position])
        startActivity(intent)
    }
}