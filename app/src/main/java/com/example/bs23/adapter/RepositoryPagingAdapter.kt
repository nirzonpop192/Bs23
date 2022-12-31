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
import com.example.bs23.databinding.RowItemRepoBinding

class RepositoryPagingAdapter :PagingDataAdapter<Item,RepositoryPagingAdapter.RepoViewHolder>(COMPARATOR) {

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        val item:Item?= getItem(position)
        item.let {
            holder.tvName.text= item!!.name
            holder.tvStar.text= item!!.stargazers_count.toString()
            holder.tvDate.text= "updated_at "+item!!.updated_at
        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
       val binding = RowItemRepoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RepoViewHolder(binding)
    }

    class RepoViewHolder(val  binding:RowItemRepoBinding):RecyclerView.ViewHolder(binding.root){
        val tvName=binding.tvName
        val tvStar=binding.tvStar
        val tvDate = binding.tvDate
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