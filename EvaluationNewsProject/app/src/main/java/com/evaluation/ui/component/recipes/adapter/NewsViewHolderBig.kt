package com.evaluation.ui.component.recipes.adapter

import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.evaluation.R
import com.evaluation.data.dto.news.Article
import com.evaluation.databinding.NewsItemBigBinding
import com.evaluation.ui.base.listeners.RecyclerItemListener

/**
 * Created by Rajesh
 */

class NewsViewHolderBig(private val itemBinding: NewsItemBigBinding) : RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(Article: Article, recyclerItemListener: RecyclerItemListener) {
        itemBinding.tvCaption.text = Article.description
        itemBinding.tvName.text = Article.title
        Picasso.get().load(Article.urlToImage).placeholder(R.drawable.ic_healthy_food).error(R.drawable.ic_healthy_food).into(itemBinding.ivRecipeItemImage)
        itemBinding.rlRecipeItem.setOnClickListener { recyclerItemListener.onItemSelected(Article) }
    }
}

