package com.elthobhy.newsapp.ui.favorite

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.elthobhy.core.domain.model.Domain
import com.elthobhy.core.utils.Constants
import com.elthobhy.core.utils.loadingExtension
import com.elthobhy.newsapp.databinding.FragmentFavoriteBinding
import com.elthobhy.newsapp.ui.category.technology.TechnologyAdapter
import com.elthobhy.newsapp.ui.detail.DetailActivity
import com.elthobhy.newsapp.viewmodel.favorite.FavoriteViewModel
import org.koin.android.ext.android.inject

class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding as FragmentFavoriteBinding
    private val favoriteHeadlineAdapter by inject<TechnologyAdapter>()
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
                    LinearLayoutManager(activity, GridLayoutManager.VERTICAL, false)
                setHasFixedSize(true)
                adapter = favoriteHeadlineAdapter
            }
            favoriteHeadlineAdapter.apply {
                favoriteViewModel.getFavoriteHeadline().observe(viewLifecycleOwner) {
                    Log.e("dataFavorite", "showRvHeadline: $it" )
                    submitList(it)
                    if (it.isEmpty()) {
                        false.loadingExtension(shimmerHeadline, rvHeadline)
                        false.loadingExtension(shimmerHeadline1, rvHeadline)
                        emptyListHeadline.visibility = View.VISIBLE
                    }else{
                        emptyListHeadline.visibility = View.GONE
                    }

                    false.loadingExtension(shimmerHeadline, rvHeadline)
                    false.loadingExtension(shimmerHeadline1, rvHeadline)
                    notifyDataSetChanged()
                }
                setOnClickCallback(object : TechnologyAdapter.OnItemClickCallback {
                    override fun onClicked(data: Domain) {
                        showDetailData(data)
                    }
                })
            }
        }
    }

    private fun showDetailData(data: Domain) {
        val intentDetail = Intent(activity, DetailActivity::class.java)
        intentDetail.putExtra(Constants.TO_DETAIL, data)
        startActivity(intentDetail)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}