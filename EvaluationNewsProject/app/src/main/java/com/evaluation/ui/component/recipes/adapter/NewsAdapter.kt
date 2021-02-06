package com.evaluation.ui.component.recipes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.evaluation.data.dto.news.Article
import com.evaluation.databinding.NewsItemBigBinding
import com.evaluation.databinding.NewsItemBinding
import com.evaluation.ui.base.listeners.RecyclerItemListener
import com.evaluation.ui.component.recipes.NewsListViewModel

/**
 * Created by Rajesh
 */

class NewsAdapter(private val recipesListViewModel: NewsListViewModel, private val recipes: List<Article>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val VIEW_TYPE_HEADER = 0
    val VIEW_TYPE_ITEM = 1
    private val onItemClickListener: RecyclerItemListener = object : RecyclerItemListener {
        override fun onItemSelected(recipe: Article) {
            recipesListViewModel.openRecipeDetails(recipe)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        if(viewType == VIEW_TYPE_HEADER) {
            val itemBinding = NewsItemBigBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return NewsViewHolderBig(itemBinding)
        }else{
            val itemBinding = NewsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return NewsViewHolder(itemBinding)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if(getItemViewType(position) == VIEW_TYPE_HEADER) {
            (holder as NewsViewHolderBig).bind(recipes[position], onItemClickListener)
        }else{
            (holder as NewsViewHolder).bind(recipes[position], onItemClickListener,position)
        }
    }

    override fun getItemCount(): Int {
        return recipes.size
    }

    override fun getItemViewType(position: Int): Int {
        if(position==0){
          return  VIEW_TYPE_HEADER
        }else{
           return VIEW_TYPE_ITEM
        }
    }
}

