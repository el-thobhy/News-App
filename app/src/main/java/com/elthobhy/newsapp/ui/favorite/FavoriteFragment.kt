package com.elthobhy.newsapp.ui.favorite

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.elthobhy.newsapp.data.source.local.entity.business.ArticleBusiness
import com.elthobhy.newsapp.data.source.local.entity.detik.ArticleDetik
import com.elthobhy.newsapp.data.source.local.entity.entertainment.ArticleEntertainment
import com.elthobhy.newsapp.data.source.local.entity.general.ArticleGeneral
import com.elthobhy.newsapp.data.source.local.entity.headline.ArticleHeadline
import com.elthobhy.newsapp.data.source.local.entity.health.ArticleHealth
import com.elthobhy.newsapp.data.source.local.entity.kapanlagi.ArticleKapanlagi
import com.elthobhy.newsapp.data.source.local.entity.science.ArticleScience
import com.elthobhy.newsapp.data.source.local.entity.sports.ArticleSports
import com.elthobhy.newsapp.data.source.local.entity.suara.ArticleSuara
import com.elthobhy.newsapp.data.source.local.entity.technology.ArticleTechnology
import com.elthobhy.newsapp.data.source.local.entity.viva.ArticleViva
import com.elthobhy.newsapp.databinding.FragmentFavoriteBinding
import com.elthobhy.newsapp.ui.detail.DetailActivity
import com.elthobhy.newsapp.ui.favorite.adapter.business.FavoriteBusinessAdapter
import com.elthobhy.newsapp.ui.favorite.adapter.detik.FavoriteDetikAdapter
import com.elthobhy.newsapp.ui.favorite.adapter.entertainment.FavoriteEntertainmentAdapter
import com.elthobhy.newsapp.ui.favorite.adapter.general.FavoriteGeneralAdapter
import com.elthobhy.newsapp.ui.favorite.adapter.headline.FavoriteHeadlineAdapter
import com.elthobhy.newsapp.ui.favorite.adapter.health.FavoriteHealthAdapter
import com.elthobhy.newsapp.ui.favorite.adapter.kapanlagi.FavoriteKapanlagiAdapter
import com.elthobhy.newsapp.ui.favorite.adapter.science.FavoriteScienceAdapter
import com.elthobhy.newsapp.ui.favorite.adapter.sports.FavoriteSportsAdapter
import com.elthobhy.newsapp.ui.favorite.adapter.suara.FavoriteSuaraAdapter
import com.elthobhy.newsapp.ui.favorite.adapter.technology.FavoriteTechnologyAdapter
import com.elthobhy.newsapp.ui.favorite.adapter.viva.FavoriteVivaAdapter
import com.elthobhy.newsapp.utils.Constants
import com.elthobhy.newsapp.utils.loadingExtension
import com.elthobhy.newsapp.viewmodel.favorite.FavoriteViewModel
import com.elthobhy.newsapp.viewmodel.ViewModelFactory

class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding as FragmentFavoriteBinding
    private lateinit var favoriteHeadlineAdapter: FavoriteHeadlineAdapter
    private lateinit var favoriteBusinessAdapter: FavoriteBusinessAdapter
    private lateinit var favoriteDetikAdapter: FavoriteDetikAdapter
    private lateinit var favoriteEntertainmentAdapter: FavoriteEntertainmentAdapter
    private lateinit var favoriteGeneralAdapter: FavoriteGeneralAdapter
    private lateinit var favoriteHealthAdapter: FavoriteHealthAdapter
    private lateinit var favoriteKapanlagiAdapter: FavoriteKapanlagiAdapter
    private lateinit var favoriteScienceAdapter: FavoriteScienceAdapter
    private lateinit var favoriteSportsAdapter: FavoriteSportsAdapter
    private lateinit var favoriteSuaraAdapter: FavoriteSuaraAdapter
    private lateinit var favoriteTechnologyAdapter: FavoriteTechnologyAdapter
    private lateinit var favoriteVivaAdapter: FavoriteVivaAdapter
    private lateinit var favoriteViewModel: FavoriteViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        favoriteHeadlineAdapter = FavoriteHeadlineAdapter()
        favoriteDetikAdapter = FavoriteDetikAdapter()
        favoriteBusinessAdapter = FavoriteBusinessAdapter()
        favoriteEntertainmentAdapter = FavoriteEntertainmentAdapter()
        favoriteGeneralAdapter = FavoriteGeneralAdapter()
        favoriteHealthAdapter = FavoriteHealthAdapter()
        favoriteKapanlagiAdapter = FavoriteKapanlagiAdapter()
        favoriteScienceAdapter = FavoriteScienceAdapter()
        favoriteSportsAdapter = FavoriteSportsAdapter()
        favoriteTechnologyAdapter = FavoriteTechnologyAdapter()
        favoriteVivaAdapter = FavoriteVivaAdapter()
        favoriteSuaraAdapter = FavoriteSuaraAdapter()
        val factory = ViewModelFactory.getInstance(requireActivity())
        favoriteViewModel = ViewModelProvider(this, factory)[FavoriteViewModel::class.java]
        showRvBusiness()
        showRvEntertainment()
        showRvGeneral()
        showRvHealth()
        showRvHeadline()
        showRvScience()
        showRvSports()
        showRvTechnology()
        showRvDetik()
        showRvViva()
        showRvSuara()
        showRvKapanlagi()
    }

    private fun showRvBusiness() {
        favoriteBusinessAdapter.notifyDataSetChanged()
        binding.apply {
            rvBusiness.apply {
                layoutManager =
                    LinearLayoutManager(activity, GridLayoutManager.HORIZONTAL, false)
                setHasFixedSize(true)
                adapter = favoriteBusinessAdapter
            }
            favoriteBusinessAdapter.apply {
                favoriteViewModel.getFavoriteBusiness().observe(viewLifecycleOwner) {
                    setList(it)
                    if (it.isEmpty()) {
                        false.loadingExtension(shimmerBusiness, rvBusiness)
                        emptyListBusiness.visibility = View.VISIBLE
                    } else {
                        emptyListBusiness.visibility = View.GONE
                    }
                    false.loadingExtension(shimmerBusiness, rvBusiness)
                    notifyDataSetChanged()
                }
                setOnItemClickCallback(object : FavoriteBusinessAdapter.OnItemClickCallback {
                    override fun onItemClicked(data: ArticleBusiness) {
                        showDetailData(data, Constants.BUSINESS)
                    }

                })
            }

        }

    }

    private fun showRvEntertainment() {
        favoriteEntertainmentAdapter.notifyDataSetChanged()
        binding.apply {
            rvEntertainment.apply {
                layoutManager =
                    LinearLayoutManager(activity, GridLayoutManager.HORIZONTAL, false)
                setHasFixedSize(true)
                adapter = favoriteEntertainmentAdapter
            }
            favoriteEntertainmentAdapter.apply {
                favoriteViewModel.getFavoriteEntertainment().observe(viewLifecycleOwner) {
                    setList(it)
                    if (it.isEmpty()) {
                        false.loadingExtension(shimmerBusiness, rvBusiness)
                        emptyListEntertainment.visibility = View.VISIBLE
                    }else{
                        emptyListEntertainment.visibility = View.GONE
                    }
                    false.loadingExtension(shimmerEntertainment, rvEntertainment)
                    notifyDataSetChanged()
                }
                setOnItemClickCallback(object : FavoriteEntertainmentAdapter.OnItemClickCallback {
                    override fun onItemClicked(data: ArticleEntertainment) {
                        showDetailData(data, Constants.ENTERTAINMENT)
                    }

                })
            }

        }
    }

    private fun showRvGeneral() {
        favoriteGeneralAdapter.notifyDataSetChanged()
        binding.apply {
            rvGeneral.apply {
                layoutManager =
                    LinearLayoutManager(activity, GridLayoutManager.HORIZONTAL, false)
                setHasFixedSize(true)
                adapter = favoriteGeneralAdapter
            }
            favoriteGeneralAdapter.apply {
                favoriteViewModel.getFavoriteGeneral().observe(viewLifecycleOwner) {
                    setList(it)
                    if (it.isEmpty()) {
                        false.loadingExtension(shimmerBusiness, rvBusiness)
                        emptyListGeneral.visibility = View.VISIBLE
                    }else{
                        emptyListGeneral.visibility = View.GONE
                    }
                    false.loadingExtension(shimmerGeneral, rvGeneral)
                    notifyDataSetChanged()
                }
                setOnItemClickCallback(object : FavoriteGeneralAdapter.OnItemClickCallback {
                    override fun onItemClicked(data: ArticleGeneral) {
                        showDetailData(data, Constants.GENERAL)
                    }
                })
            }
        }
    }

    private fun showRvHealth() {
        favoriteHealthAdapter.notifyDataSetChanged()
        binding.apply {
            rvHealth.apply {
                layoutManager =
                    LinearLayoutManager(activity, GridLayoutManager.HORIZONTAL, false)
                setHasFixedSize(true)
                adapter = favoriteHealthAdapter
            }
            favoriteHealthAdapter.apply {
                favoriteViewModel.getFavoriteHealth().observe(viewLifecycleOwner) {
                    setList(it)
                    if (it.isEmpty()) {
                        false.loadingExtension(shimmerBusiness, rvBusiness)
                        emptyListHealth.visibility = View.VISIBLE
                    }else{
                        emptyListHealth.visibility = View.GONE
                    }
                    false.loadingExtension(shimmerHealth, rvHealth)
                    notifyDataSetChanged()
                }
                setOnItemClickCallback(object : FavoriteHealthAdapter.OnItemClickCallback {
                    override fun onItemClicked(data: ArticleHealth) {
                        showDetailData(data, Constants.GENERAL)
                    }
                })
            }
        }
    }

    private fun showRvScience() {
        favoriteScienceAdapter.notifyDataSetChanged()
        binding.apply {
            rvScience.apply {
                layoutManager =
                    LinearLayoutManager(activity, GridLayoutManager.HORIZONTAL, false)
                setHasFixedSize(true)
                adapter = favoriteScienceAdapter
            }
            favoriteScienceAdapter.apply {
                favoriteViewModel.getFavoriteScience().observe(viewLifecycleOwner) {
                    setList(it)
                    if (it.isEmpty()) {
                        false.loadingExtension(shimmerBusiness, rvBusiness)
                        emptyListScience.visibility = View.VISIBLE
                    }else{
                        emptyListScience.visibility = View.GONE
                    }

                    false.loadingExtension(shimmerScience, rvScience)
                    notifyDataSetChanged()
                }
                setOnItemClickCallback(object : FavoriteScienceAdapter.OnItemClickCallback {
                    override fun onItemClicked(data: ArticleScience) {
                        showDetailData(data, Constants.SCIENCE)
                    }
                })
            }
        }
    }

    private fun showRvSports() {
        favoriteSportsAdapter.notifyDataSetChanged()
        binding.apply {
            rvSport.apply {
                layoutManager =
                    LinearLayoutManager(activity, GridLayoutManager.HORIZONTAL, false)
                setHasFixedSize(true)
                adapter = favoriteSportsAdapter
            }
            favoriteSportsAdapter.apply {
                favoriteViewModel.getFavoriteSports().observe(viewLifecycleOwner) {
                    setList(it)
                    if (it.isEmpty()) {
                        false.loadingExtension(shimmerBusiness, rvBusiness)
                        emptyListSport.visibility = View.VISIBLE
                    }else{
                        emptyListSport.visibility = View.GONE
                    }

                    false.loadingExtension(shimmerSport, rvSport)
                    notifyDataSetChanged()
                }
                setOnItemClickCallback(object : FavoriteSportsAdapter.OnItemClickCallback {
                    override fun onItemClicked(data: ArticleSports) {
                        showDetailData(data, Constants.SPORTS)
                    }
                })
            }
        }
    }

    private fun showRvTechnology() {
        favoriteTechnologyAdapter.notifyDataSetChanged()
        binding.apply {
            rvTechnology.apply {
                layoutManager =
                    LinearLayoutManager(activity, GridLayoutManager.HORIZONTAL, false)
                setHasFixedSize(true)
                adapter = favoriteTechnologyAdapter
            }
            favoriteTechnologyAdapter.apply {
                favoriteViewModel.getFavoriteTechnology().observe(viewLifecycleOwner) {
                    setList(it)
                    if (it.isEmpty()) {
                        false.loadingExtension(shimmerBusiness, rvBusiness)
                        emptyListTechnology.visibility = View.VISIBLE
                    }else{
                        emptyListTechnology.visibility = View.GONE
                    }

                    false.loadingExtension(shimmerTechnology, rvTechnology)
                    notifyDataSetChanged()
                }
                setOnItemClickCallback(object : FavoriteTechnologyAdapter.OnItemClickCallback {
                    override fun onItemClicked(data: ArticleTechnology) {
                        showDetailData(data, Constants.TECHNOLOGY)
                    }
                })
            }
        }
    }

    private fun showRvDetik() {
        favoriteDetikAdapter.notifyDataSetChanged()
        binding.apply {
            rvDetik.apply {
                layoutManager =
                    LinearLayoutManager(activity, GridLayoutManager.HORIZONTAL, false)
                setHasFixedSize(true)
                adapter = favoriteDetikAdapter
            }
            favoriteDetikAdapter.apply {
                favoriteViewModel.getFavoriteDetik().observe(viewLifecycleOwner) {
                    setList(it)
                    if (it.isEmpty()) {
                        false.loadingExtension(shimmerBusiness, rvBusiness)
                        emptyListDetik.visibility = View.VISIBLE
                    }else{
                        emptyListDetik.visibility = View.GONE
                    }

                    false.loadingExtension(shimmerDetik, rvDetik)
                    notifyDataSetChanged()
                }
                setOnItemClickCallback(object : FavoriteDetikAdapter.OnItemClickCallback {
                    override fun onItemClicked(data: ArticleDetik) {
                        showDetailData(data, Constants.DETIK)
                    }
                })
            }
        }
    }

    private fun showRvViva() {
        favoriteVivaAdapter.notifyDataSetChanged()
        binding.apply {
            rvViva.apply {
                layoutManager =
                    LinearLayoutManager(activity, GridLayoutManager.HORIZONTAL, false)
                setHasFixedSize(true)
                adapter = favoriteVivaAdapter
            }
            favoriteVivaAdapter.apply {
                favoriteViewModel.getFavoriteViva().observe(viewLifecycleOwner) {
                    setList(it)
                    if (it.isEmpty()) {
                        false.loadingExtension(shimmerBusiness, rvBusiness)
                        emptyListViva.visibility = View.VISIBLE
                    }else{
                        emptyListViva.visibility = View.GONE
                    }

                    false.loadingExtension(shimmerViva, rvViva)
                    notifyDataSetChanged()
                }
                setOnItemClickCallback(object : FavoriteVivaAdapter.OnItemClickCallback {
                    override fun onItemClicked(data: ArticleViva) {
                        showDetailData(data, Constants.VIVA)
                    }
                })
            }
        }
    }

    private fun showRvKapanlagi() {
        favoriteKapanlagiAdapter.notifyDataSetChanged()
        binding.apply {
            rvKapanlagi.apply {
                layoutManager =
                    LinearLayoutManager(activity, GridLayoutManager.HORIZONTAL, false)
                setHasFixedSize(true)
                adapter = favoriteKapanlagiAdapter
            }
            favoriteKapanlagiAdapter.apply {
                favoriteViewModel.getFavoriteKapanlagi().observe(viewLifecycleOwner) {
                    setList(it)
                    if (it.isEmpty()) {
                        false.loadingExtension(shimmerBusiness, rvBusiness)
                        emptyListKapanlagi.visibility = View.VISIBLE
                    }else{
                        emptyListKapanlagi.visibility = View.GONE
                    }

                    false.loadingExtension(shimmerKapanlagi, rvKapanlagi)
                    notifyDataSetChanged()
                }
                setOnItemClickCallback(object : FavoriteKapanlagiAdapter.OnItemClickCallback {
                    override fun onItemClicked(data: ArticleKapanlagi) {
                        showDetailData(data, Constants.KAPAN_LAGI)
                    }
                })
            }
        }
    }

    private fun showRvSuara() {
        favoriteSuaraAdapter.notifyDataSetChanged()
        binding.apply {
            rvSuara.apply {
                layoutManager =
                    LinearLayoutManager(activity, GridLayoutManager.HORIZONTAL, false)
                setHasFixedSize(true)
                adapter = favoriteSuaraAdapter
            }
            favoriteSuaraAdapter.apply {
                favoriteViewModel.getFavoriteSuara().observe(viewLifecycleOwner) {
                    setList(it)
                    if (it.isEmpty()) {
                        false.loadingExtension(shimmerBusiness, rvBusiness)
                        emptyListSuara.visibility = View.VISIBLE
                    }else{
                        emptyListSuara.visibility = View.GONE
                    }

                    false.loadingExtension(shimmerSuara, rvSuara)
                    notifyDataSetChanged()
                }
                setOnItemClickCallback(object : FavoriteSuaraAdapter.OnItemClickCallback {
                    override fun onItemClicked(data: ArticleSuara) {
                        showDetailData(data, Constants.SUARA)
                    }
                })
            }
        }
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
                favoriteViewModel.getFavoriteHeadline().observe(viewLifecycleOwner) {
                    setList(it)
                    if (it.isEmpty()) {
                        false.loadingExtension(shimmerBusiness, rvBusiness)
                        emptyListHeadline.visibility = View.VISIBLE
                    }else{
                        emptyListHeadline.visibility = View.GONE
                    }

                    false.loadingExtension(shimmerHeadline, rvHeadline)
                    notifyDataSetChanged()
                }
                setOnItemClickCallback(object : FavoriteHeadlineAdapter.OnItemClickCallback {
                    override fun onItemClicked(data: ArticleHeadline) {
                        showDetailData(data, Constants.TOP_HEADLINE)
                    }
                })
            }
        }
    }

    private fun showDetailData(data: Parcelable, key: String) {
        when (key) {
            Constants.BUSINESS -> {
                val intentDetail = Intent(activity, DetailActivity::class.java)
                intentDetail.putExtra(Constants.BUSINESS, data)
                startActivity(intentDetail)
            }
            Constants.ENTERTAINMENT -> {
                val intentDetail = Intent(activity, DetailActivity::class.java)
                intentDetail.putExtra(Constants.ENTERTAINMENT, data)
                startActivity(intentDetail)
            }
            Constants.GENERAL -> {
                val intentDetail = Intent(activity, DetailActivity::class.java)
                intentDetail.putExtra(Constants.GENERAL, data)
                startActivity(intentDetail)
            }
            Constants.HEALTH -> {
                val intentDetail = Intent(activity, DetailActivity::class.java)
                intentDetail.putExtra(Constants.HEALTH, data)
                startActivity(intentDetail)
            }
            Constants.SCIENCE -> {
                val intentDetail = Intent(activity, DetailActivity::class.java)
                intentDetail.putExtra(Constants.SCIENCE, data)
                startActivity(intentDetail)
            }
            Constants.SPORTS -> {
                val intentDetail = Intent(activity, DetailActivity::class.java)
                intentDetail.putExtra(Constants.SPORTS, data)
                startActivity(intentDetail)
            }
            Constants.TECHNOLOGY -> {
                val intentDetail = Intent(activity, DetailActivity::class.java)
                intentDetail.putExtra(Constants.TECHNOLOGY, data)
                startActivity(intentDetail)
            }
            Constants.DETIK ->{
                val intentDetail = Intent(activity, DetailActivity::class.java)
                intentDetail.putExtra(Constants.DETIK, data)
                startActivity(intentDetail)
            }
            Constants.TOP_HEADLINE->{
                val intentDetail = Intent(activity, DetailActivity::class.java)
                intentDetail.putExtra(Constants.TOP_HEADLINE, data)
                startActivity(intentDetail)
            }
            Constants.SUARA->{
                val intentDetail = Intent(activity, DetailActivity::class.java)
                intentDetail.putExtra(Constants.SUARA, data)
                startActivity(intentDetail)
            }
            Constants.KAPAN_LAGI->{
                val intentDetail = Intent(activity, DetailActivity::class.java)
                intentDetail.putExtra(Constants.KAPAN_LAGI, data)
                startActivity(intentDetail)
            }
            Constants.VIVA->{
                val intentDetail = Intent(activity, DetailActivity::class.java)
                intentDetail.putExtra(Constants.VIVA, data)
                startActivity(intentDetail)
            }

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}