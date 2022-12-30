package com.example.bs23.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.bs23.R
import com.example.bs23.data.model.Item

class RepositoryPagingAdapter :PagingDataAdapter<Item,RepositoryPagingAdapter.RepoViewHolder>(COMPARATOR) {

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        val item:Item?= getItem(position)
        item.let {
            holder.tvName.text= item!!.name
        }
    }

    fun getSize()=itemCount

    fun getAdapterItem(position: Int): Item?{
        return  getItem(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.row_item_repo,parent,false)
        return RepoViewHolder(view)
    }

    class RepoViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val tvName=itemView.findViewById<TextView>(R.id.tv_name)
    }

    companion object{
        private val COMPARATOR= object :DiffUtil.ItemCallback<Item>(){
            override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
                return  oldItem.id==newItem.id
            }

            override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
                return  oldItem == newItem
            }
        }
    }
}