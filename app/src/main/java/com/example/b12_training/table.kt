package com.example.b12_training

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_recycler.*
import kotlinx.android.synthetic.main.fragment_table.*
import kotlin.random.Random

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [table.newInstance] factory method to
 * create an instance of this fragment.
 */
class table : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var classAdapter: classAdapter
    lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_table, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        DaysRecycler.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(activity)
        DaysRecycler.layoutManager = linearLayoutManager
        val classes = arrayOf<String>("Math","English","Computer Science","Arabic","Geography","Physics","Urdu"
        ,"General Science","Launch Break","France","Italian","Spanish")
        val classTime = arrayOf<String>("8:15 - 9:15","9:15 - 10:15","10:15 - 11:15","11:15 - 12:15",
        "12:15 - 1:15","1:15 - 2:15","2.15 - 3:15")
        var tableClass = arrayListOf<ClassInfo>()
        for (i in 0..6 ){
            var Class = classes.random()
            var prog =Random.nextInt(0,100)
            var Info = ClassInfo(Class,classTime[i],prog)
            tableClass.add(Info)
        }
        toggleButton.check(R.id.Mon)
        classAdapter = classAdapter(activity,tableClass)
        classAdapter.notifyDataSetChanged()
        DaysRecycler.adapter = classAdapter
        toggleButton.addOnButtonCheckedListener { toggleButton, checkedId, isChecked ->
            tableClass.clear()
            for (i in 0..6 ){
                var Class = classes.random()
                var prog =Random.nextInt(0,100)
                var Info = ClassInfo(Class,classTime[i],prog)
                tableClass.add(Info)
            }
            if(isChecked){
                when(checkedId){
                    R.id.Mon ->{
                        classAdapter = classAdapter(activity,tableClass)
                        classAdapter.notifyDataSetChanged()
                        DaysRecycler.adapter = classAdapter
                    }
                    R.id.Tue -> {
                        classAdapter = classAdapter(activity,tableClass)
                        classAdapter.notifyDataSetChanged()
                        DaysRecycler.adapter = classAdapter
                    }
                    R.id.WED -> {
                        classAdapter = classAdapter(activity,tableClass)
                        classAdapter.notifyDataSetChanged()
                        DaysRecycler.adapter = classAdapter}
                    R.id.Thu -> {
                        classAdapter = classAdapter(activity,tableClass)
                        classAdapter.notifyDataSetChanged()
                        DaysRecycler.adapter = classAdapter
                    }
                    R.id.Fri -> {
                        classAdapter = classAdapter(activity,tableClass)
                        classAdapter.notifyDataSetChanged()
                        DaysRecycler.adapter = classAdapter
                    }
                    R.id.Sat -> {
                        classAdapter = classAdapter(activity,tableClass)
                        classAdapter.notifyDataSetChanged()
                        DaysRecycler.adapter = classAdapter
                    }
                }
            }

        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment table.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            table().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}