package com.evaluation.ui.component.recipes.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.evaluation.R
import com.evaluation.data.dto.news.Article
import com.evaluation.databinding.NewsItemBinding
import com.evaluation.ui.base.listeners.RecyclerItemListener

/**
 * Created by Rajesh
 */

class NewsViewHolder(private val itemBinding: NewsItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(Article: Article, recyclerItemListener: RecyclerItemListener, position: Int) {
        itemBinding.tvCaption.text = Article.description
        itemBinding.tvName.text = Article.title
        if(position==1){
            itemBinding.header.visibility = View.VISIBLE
        }else{
            itemBinding.header.visibility = View.GONE
        }
        Picasso.get().load(Article.urlToImage).placeholder(R.drawable.ic_healthy_food).error(R.drawable.ic_healthy_food).into(itemBinding.ivRecipeItemImage)
        itemBinding.rlRecipeItem.setOnClickListener { recyclerItemListener.onItemSelected(Article) }
    }
}

