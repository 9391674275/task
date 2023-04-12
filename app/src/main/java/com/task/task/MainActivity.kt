package com.task.task

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.task.task.model.RecyclerData
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), RecyclerViewAdapter.OnItemClickListener {

    private lateinit var recyclerViewAdapter: RecyclerViewAdapter
    private lateinit var mainActivityViewModel: MainActivityViewModel
    lateinit var listItems: List<RecyclerData>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecyclerView()
        initViewModel()

    }

    private fun initRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerViewAdapter = RecyclerViewAdapter(this)
        recyclerView.adapter = recyclerViewAdapter
        /* recyclerViewAdapter?.onItemClick = { contact ->

             val newIntent = Intent(this, NewActivity::class.java)

             startActivity(newIntent)

         }*/


    }

    private fun initViewModel() {
        mainActivityViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        mainActivityViewModel.getLiveDataObserver()
            .observe(this, object : Observer<List<RecyclerData>> {
                override fun onChanged(t: List<RecyclerData>?) {
                    if (t != null) {
                        listItems = t
                        recyclerViewAdapter.setUpdateData(t)
                        recyclerViewAdapter.notifyDataSetChanged()

                    } else {
                        Toast.makeText(
                            this@MainActivity,
                            "Error getting fetching data",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            })
        mainActivityViewModel.makeApiCall()
    }

    override fun onItemClick(position: Int) {
        val description = listItems.get(position).title
        val image = listItems.get(position).url
        val newIntent = Intent(this, NewActivity::class.java)
        newIntent.putExtra("desc", description)
        newIntent.putExtra("img", image)
        startActivity(newIntent)
    }


}