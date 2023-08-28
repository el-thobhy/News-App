package com.elthobhy.newsapp.ui.favorite

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.elthobhy.core.data.source.local.entity.headline.ArticleHeadlineEntity
import com.elthobhy.newsapp.databinding.FragmentFavoriteBinding
import com.elthobhy.newsapp.ui.detail.DetailActivity
import com.elthobhy.newsapp.ui.favorite.adapter.headline.FavoriteHeadlineAdapter
import com.elthobhy.newsapp.viewmodel.favorite.FavoriteViewModel
import org.koin.android.ext.android.inject

class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding as FragmentFavoriteBinding
    private val favoriteHeadlineAdapter by inject<FavoriteHeadlineAdapter>()
    private val favoriteViewModel by inject<FavoriteViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showRvHeadline()
    }

    private fun showRvHeadline() {
        favoriteHeadlineAdapter.notifyDataSetChanged()
        binding.apply {
            rvHeadline.apply {
                layoutManager =
                    LinearLayoutManager(activity, GridLayoutManager.HORIZONTAL, false)
                setHasFixedSize(true)
                adapter = favoriteHeadlineAdapter
            }
            favoriteHeadlineAdapter.apply {
                /*favoriteViewModel.getFavoriteHeadline().observe(viewLifecycleOwner) {
                    setList(it)
                    if (it.isEmpty()) {
                        false.loadingExtension(shimmerBusiness, rvBusiness)
                        emptyListHeadline.visibility = View.VISIBLE
                    }else{
                        emptyListHeadline.visibility = View.GONE
                    }

                    false.loadingExtension(shimmerHeadline, rvHeadline)
                    notifyDataSetChanged()
                }*/
                setOnItemClickCallback(object : FavoriteHeadlineAdapter.OnItemClickCallback {
                    override fun onItemClicked(data: ArticleHeadlineEntity) {
                        showDetailData(data, com.elthobhy.core.utils.Constants.TOP_HEADLINE)
                    }
                })
            }
        }
    }

    private fun showDetailData(data: Parcelable, key: String) {
        when (key) {
            com.elthobhy.core.utils.Constants.BUSINESS -> {
                val intentDetail = Intent(activity, DetailActivity::class.java)
                intentDetail.putExtra(com.elthobhy.core.utils.Constants.BUSINESS, data)
                startActivity(intentDetail)
            }
            com.elthobhy.core.utils.Constants.ENTERTAINMENT -> {
                val intentDetail = Intent(activity, DetailActivity::class.java)
                intentDetail.putExtra(com.elthobhy.core.utils.Constants.ENTERTAINMENT, data)
                startActivity(intentDetail)
            }
            com.elthobhy.core.utils.Constants.GENERAL -> {
                val intentDetail = Intent(activity, DetailActivity::class.java)
                intentDetail.putExtra(com.elthobhy.core.utils.Constants.GENERAL, data)
                startActivity(intentDetail)
            }
            com.elthobhy.core.utils.Constants.HEALTH -> {
                val intentDetail = Intent(activity, DetailActivity::class.java)
                intentDetail.putExtra(com.elthobhy.core.utils.Constants.HEALTH, data)
                startActivity(intentDetail)
            }
            com.elthobhy.core.utils.Constants.SCIENCE -> {
                val intentDetail = Intent(activity, DetailActivity::class.java)
                intentDetail.putExtra(com.elthobhy.core.utils.Constants.SCIENCE, data)
                startActivity(intentDetail)
            }
            com.elthobhy.core.utils.Constants.SPORTS -> {
                val intentDetail = Intent(activity, DetailActivity::class.java)
                intentDetail.putExtra(com.elthobhy.core.utils.Constants.SPORTS, data)
                startActivity(intentDetail)
            }
            com.elthobhy.core.utils.Constants.TECHNOLOGY -> {
                val intentDetail = Intent(activity, DetailActivity::class.java)
                intentDetail.putExtra(com.elthobhy.core.utils.Constants.TECHNOLOGY, data)
                startActivity(intentDetail)
            }
            com.elthobhy.core.utils.Constants.DETIK ->{
                val intentDetail = Intent(activity, DetailActivity::class.java)
                intentDetail.putExtra(com.elthobhy.core.utils.Constants.DETIK, data)
                startActivity(intentDetail)
            }
            com.elthobhy.core.utils.Constants.TOP_HEADLINE->{
                val intentDetail = Intent(activity, DetailActivity::class.java)
                intentDetail.putExtra(com.elthobhy.core.utils.Constants.TOP_HEADLINE, data)
                startActivity(intentDetail)
            }
            com.elthobhy.core.utils.Constants.SUARA->{
                val intentDetail = Intent(activity, DetailActivity::class.java)
                intentDetail.putExtra(com.elthobhy.core.utils.Constants.SUARA, data)
                startActivity(intentDetail)
            }
            com.elthobhy.core.utils.Constants.KAPAN_LAGI->{
                val intentDetail = Intent(activity, DetailActivity::class.java)
                intentDetail.putExtra(com.elthobhy.core.utils.Constants.KAPAN_LAGI, data)
                startActivity(intentDetail)
            }
            com.elthobhy.core.utils.Constants.VIVA->{
                val intentDetail = Intent(activity, DetailActivity::class.java)
                intentDetail.putExtra(com.elthobhy.core.utils.Constants.VIVA, data)
                startActivity(intentDetail)
            }

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}