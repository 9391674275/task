package com.task.task

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.task.task.model.RecyclerData
import kotlinx.android.synthetic.main.recycler_view_list_row.view.*

class RecyclerViewAdapter(private val itemClickListener: OnItemClickListener) : RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {
    private var listData: List<RecyclerData?>? = null

    interface OnItemClickListener {
        fun onItemClick(position: Int)

    }

    fun setUpdateData(listData: List<RecyclerData?>?) {
        this.listData = listData
    }

   inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView = view.imageView
        val title = view.title
        val totalCard = view.totalView
        fun bind(data: RecyclerData) {
            title.setText(data.title)
            Picasso.get().load(data.thumbnailUrl).into(imageView)
        }

        init {
            view.setOnClickListener {
                if (adapterPosition >= 0) {
                    itemClickListener.onItemClick(adapterPosition)
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_view_list_row, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        listData?.get(position)?.let { holder.bind(it) }
    }

    override fun getItemCount(): Int {
        if (listData == null) return 0
        else return listData!!.size
    }
}