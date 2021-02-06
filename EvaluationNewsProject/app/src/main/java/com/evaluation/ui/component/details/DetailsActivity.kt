package com.evaluation.ui.component.details

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.webkit.WebSettings
import android.webkit.WebViewClient
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.evaluation.R
import com.evaluation.RECIPE_ITEM_KEY
import com.evaluation.data.Resource
import com.evaluation.data.dto.news.Article
import com.evaluation.databinding.DetailsLayoutBinding
import com.evaluation.ui.base.BaseActivity
import com.evaluation.utils.observe
import dagger.hilt.android.AndroidEntryPoint


/**
 * Created by Rajesh
 */

@AndroidEntryPoint
class DetailsActivity : BaseActivity() {

    private val viewModel: DetailsViewModel by viewModels()

    private lateinit var binding: DetailsLayoutBinding
    private var menu: Menu? = null
    private var url: String? = null


    override fun initViewBinding() {
        binding = DetailsLayoutBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.initIntentData(intent.getSerializableExtra(RECIPE_ITEM_KEY) as Article ?: Article())
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val webSettings: WebSettings = binding.webview.getSettings()
        webSettings.javaScriptEnabled = true
        binding.webview.setWebViewClient(WebViewClient())
        viewModel.newsPrivate.value?.url?.let {
            binding.webview.loadUrl(it)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.details_menu, menu)
        this.menu = menu
        viewModel.isFavourites()
        return true
    }

    fun onClickFavorite(mi: MenuItem) {
        mi.isCheckable = false
        if (viewModel.isFavourite.value?.data == true) {
            viewModel.removeFromFavourites()
        } else {
            viewModel.addToFavourites()
        }
    }

    override fun observeViewModel() {
        observe(viewModel.newsData, ::initializeView)
        observe(viewModel.isFavourite, ::handleIsFavourite)
    }

    private fun handleIsFavourite(isFavourite: Resource<Boolean>) {
        when (isFavourite) {
            is Resource.Loading -> {
                //binding.pbLoading.toVisible()
            }
            is Resource.Success -> {
                isFavourite.data?.let {
                    handleIsFavouriteUI(it)
                    menu?.findItem(R.id.add_to_favorite)?.isCheckable = true
                    //binding.pbLoading.toGone()
                }
            }
            is Resource.DataError -> {
                menu?.findItem(R.id.add_to_favorite)?.isCheckable = true
                //binding.pbLoading.toGone()
            }
        }
    }

    private fun handleIsFavouriteUI(isFavourite: Boolean) {
        menu?.let {
            it.findItem(R.id.add_to_favorite)?.icon =
                    if (isFavourite) {
                        ContextCompat.getDrawable(this, R.drawable.ic_star_24)
                    } else {
                        ContextCompat.getDrawable(this, R.drawable.ic_outline_star_border_24)
                    }
        }
    }

    private fun initializeView(Article: Article) {
//        binding.tvName.text = Article.title
//        binding.tvHeadline.text = Article.description
//        binding.tvDescription.text = Article.description
//        Picasso.get().load(Article.urlToImage).placeholder(R.drawable.ic_healthy_food_small).into(binding.ivRecipeImage)
    }
}
