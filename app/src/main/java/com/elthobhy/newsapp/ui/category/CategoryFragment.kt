package com.elthobhy.newsapp.ui.category

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.elthobhy.newsapp.data.source.local.entity.business.ArticleBusiness
import com.elthobhy.newsapp.data.source.local.entity.entertainment.ArticleEntertainment
import com.elthobhy.newsapp.data.source.local.entity.general.ArticleGeneral
import com.elthobhy.newsapp.data.source.local.entity.health.ArticleHealth
import com.elthobhy.newsapp.data.source.local.entity.science.ArticleScience
import com.elthobhy.newsapp.data.source.local.entity.sports.ArticleSports
import com.elthobhy.newsapp.data.source.local.entity.technology.ArticleTechnology
import com.elthobhy.newsapp.databinding.FragmentCategoryBinding
import com.elthobhy.newsapp.ui.category.business.BusinessAdapter
import com.elthobhy.newsapp.ui.category.entertainment.EntertainmentAdapter
import com.elthobhy.newsapp.ui.category.general.GeneralAdapter
import com.elthobhy.newsapp.ui.category.health.HealthAdapter
import com.elthobhy.newsapp.ui.category.science.ScienceAdapter
import com.elthobhy.newsapp.ui.category.sport.SportsAdapter
import com.elthobhy.newsapp.ui.category.technology.TechnologyAdapter
import com.elthobhy.newsapp.ui.detail.DetailActivity
import com.elthobhy.newsapp.utils.Constants
import com.elthobhy.newsapp.utils.loadingExtension
import com.elthobhy.newsapp.utils.vo.Status
import com.elthobhy.newsapp.viewmodel.*
import com.elthobhy.newsapp.viewmodel.business.BusinessViewModel
import com.elthobhy.newsapp.viewmodel.entertainment.EntertainmentViewModel
import com.elthobhy.newsapp.viewmodel.general.GeneralViewModel
import com.elthobhy.newsapp.viewmodel.health.HealthViewModel
import com.elthobhy.newsapp.viewmodel.science.ScienceViewModel
import com.elthobhy.newsapp.viewmodel.sports.SportViewModel
import com.elthobhy.newsapp.viewmodel.technology.TechnologyViewModel
import com.facebook.shimmer.ShimmerFrameLayout
import org.koin.android.ext.android.inject

class CategoryFragment : Fragment() {

    private var _binding: FragmentCategoryBinding? = null
    private val binding get() = _binding as FragmentCategoryBinding
    private val adapterBusiness by inject<BusinessAdapter>()
    private val entertainmentAdapter by inject<EntertainmentAdapter>()
    private val generalAdapter by inject<GeneralAdapter>()
    private val healthAdapter by inject<HealthAdapter>()
    private val scienceAdapter by inject<ScienceAdapter>()
    private val sportsAdapter by inject<SportsAdapter>()
    private val technologyAdapter by inject<TechnologyAdapter>()
    private val businessViewModel by inject<BusinessViewModel>()
    private val entertainmentViewModel by inject<EntertainmentViewModel>()
    private val generalViewModel by inject<GeneralViewModel>()
    private val healthViewModel by inject<HealthViewModel>()
    private val scienceViewModel by inject<ScienceViewModel>()
    private val sportsViewModel by inject<SportViewModel>()
    private val technologyViewModel by inject<TechnologyViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCategoryBinding.inflate(inflater, container, false)

        binding.apply {
            showRvBusiness(rvBusiness, shimmerBusiness)
            showRvEntertainment(rvEntertainment, shimmerEntertainment)
            showRvGeneral(rvGeneral, shimmerGeneral)
            showRvHealth(rvHealth, shimmerHealth)
            showRvScience(rvScience, shimmerScience)
            showRvSports(rvSport, shimmerSport)
            showRvTechnology(rvTechnology, shimmerTechnology)
        }

        return binding.root
    }

    private fun showRvBusiness(recyclerView: RecyclerView, shimmer: ShimmerFrameLayout) {
        binding.apply {
            true.loadingExtension(shimmer, recyclerView)
            recyclerView.apply {
                layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                setHasFixedSize(true)
                adapter = adapterBusiness
            }
            businessViewModel.getBusinessNews().observe(viewLifecycleOwner) { listArticle ->
                if (listArticle != null) {
                    when(listArticle.status){
                        Status.LOADING -> true.loadingExtension(shimmerBusiness,rvBusiness)
                        Status.SUCCESS->{
                            listArticle.data?.let { adapterBusiness.setList(it) }
                            adapterBusiness.notifyDataSetChanged()
                            Log.e("business", "showRvHeadline: ${listArticle.data}",)
                            false.loadingExtension(shimmerBusiness,rvBusiness)
                        }
                        Status.ERROR->{
                            false.loadingExtension(shimmerBusiness,rvBusiness)
                        }
                    }
                }
                false.loadingExtension(shimmerBusiness, rvBusiness)
            }
        }
        adapterBusiness.setOnClickCallback(object : BusinessAdapter.OnItemClickCallback {
            override fun onClicked(data: ArticleBusiness) {
                showDetailData(data, Constants.BUSINESS)
            }

        })
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
            else -> {
                Log.e("else", "showDetailData: ")
            }
        }
    }

    private fun showRvEntertainment(recyclerView: RecyclerView, shimmer: ShimmerFrameLayout) {
        binding.apply {
            true.loadingExtension(shimmer, recyclerView)
            recyclerView.apply {
                layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                setHasFixedSize(true)
                adapter = entertainmentAdapter
            }
            entertainmentViewModel.getEntertainment().observe(viewLifecycleOwner) { listArticle ->
                if (listArticle != null) {
                    when(listArticle.status){
                        Status.LOADING -> true.loadingExtension(shimmerEntertainment,rvEntertainment)
                        Status.SUCCESS->{
                            listArticle.data?.let { entertainmentAdapter.setList(it) }
                            false.loadingExtension(shimmerEntertainment,rvEntertainment)
                        }
                        Status.ERROR->{
                            false.loadingExtension(shimmerEntertainment,rvEntertainment)
                        }
                    }
                }
                Log.e("debug", "showRvHeadline: $listArticle",)
                false.loadingExtension(shimmerEntertainment, rvEntertainment)
            }
        }
        entertainmentAdapter.setOnClickCallback(object : EntertainmentAdapter.OnItemClickCallback {
            override fun onClicked(data: ArticleEntertainment) {
                showDetailData(data, Constants.ENTERTAINMENT)
            }

        })
    }

    private fun showRvGeneral(recyclerView: RecyclerView, shimmer: ShimmerFrameLayout) {
        binding.apply {
            true.loadingExtension(shimmer, recyclerView)
            recyclerView.apply {
                layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                setHasFixedSize(true)
                adapter = generalAdapter
            }
            generalViewModel.getGeneralNews().observe(viewLifecycleOwner) { listArticle ->
                if (listArticle != null) {
                    when(listArticle.status){
                        Status.LOADING -> true.loadingExtension(shimmerGeneral,rvGeneral)
                        Status.SUCCESS->{
                            listArticle.data?.let { generalAdapter.setList(it) }
                            false.loadingExtension(shimmerGeneral,rvGeneral)
                        }
                        Status.ERROR->{
                            false.loadingExtension(shimmerGeneral,rvGeneral)
                        }
                    }
                }
                Log.e("debug", "showRvHeadline: $listArticle",)
                false.loadingExtension(shimmerGeneral, rvGeneral)
            }
        }
        generalAdapter.setOnClickCallback(object : GeneralAdapter.OnItemClickCallback {
            override fun onClicked(data: ArticleGeneral) {
                showDetailData(data, Constants.GENERAL)
            }

        })
    }

    private fun showRvHealth(recyclerView: RecyclerView, shimmer: ShimmerFrameLayout) {
        binding.apply {
            true.loadingExtension(shimmer, recyclerView)
            recyclerView.apply {
                layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                setHasFixedSize(true)
                adapter = healthAdapter
            }
            healthViewModel.getHealthNews().observe(viewLifecycleOwner) { listArticle ->
                if (listArticle != null) {
                    when(listArticle.status){
                        Status.LOADING -> true.loadingExtension(shimmerHealth,rvHealth)
                        Status.SUCCESS->{
                            listArticle.data?.let { healthAdapter.setList(it) }
                            false.loadingExtension(shimmerHealth,rvHealth)
                        }
                        Status.ERROR->{
                            false.loadingExtension(shimmerHealth,rvHealth)
                        }
                    }
                }
                Log.e("debug", "showRvHeadline: $listArticle",)
                false.loadingExtension(shimmerHealth, rvHealth)
            }
        }
        healthAdapter.setOnClickCallback(object : HealthAdapter.OnItemClickCallback {
            override fun onClicked(data: ArticleHealth) {
                showDetailData(data, Constants.HEALTH)
            }

        })
    }

    private fun showRvScience(recyclerView: RecyclerView, shimmer: ShimmerFrameLayout) {
        binding.apply {
            true.loadingExtension(shimmer, recyclerView)
            recyclerView.apply {
                layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                setHasFixedSize(true)
                adapter = scienceAdapter
            }
            scienceViewModel.getScienceNews().observe(viewLifecycleOwner) { listArticle ->
                if (listArticle != null) {
                    when(listArticle.status){
                        Status.LOADING -> true.loadingExtension(shimmerScience,rvScience)
                        Status.SUCCESS->{
                            listArticle.data?.let { scienceAdapter.setList(it) }
                            false.loadingExtension(shimmerScience,rvScience)
                        }
                        Status.ERROR->{
                            false.loadingExtension(shimmerScience,rvScience)
                        }
                    }
                }
                Log.e("debug", "showRvHeadline: $listArticle",)
                false.loadingExtension(shimmerScience, rvScience)
            }
        }
        scienceAdapter.setOnClickCallback(object : ScienceAdapter.OnItemClickCallback {
            override fun onClicked(data: ArticleScience) {
                showDetailData(data, Constants.SCIENCE)
            }

        })
    }

    private fun showRvSports(recyclerView: RecyclerView, shimmer: ShimmerFrameLayout) {
        binding.apply {
            true.loadingExtension(shimmer, recyclerView)
            recyclerView.apply {
                layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                setHasFixedSize(true)
                adapter = sportsAdapter
            }
            sportsViewModel.getSportsNews().observe(viewLifecycleOwner) { listArticle ->
                if (listArticle != null) {
                    when(listArticle.status){
                        Status.LOADING -> true.loadingExtension(shimmerSport,rvSport)
                        Status.SUCCESS->{
                            listArticle.data?.let { sportsAdapter.setList(it) }
                            false.loadingExtension(shimmerSport,rvSport)
                        }
                        Status.ERROR->{
                            false.loadingExtension(shimmerSport,rvSport)
                        }
                    }
                }
                Log.e("debug", "showRvHeadline: $listArticle",)
                false.loadingExtension(shimmerSport, rvSport)
            }
        }
        sportsAdapter.setOnClickCallback(object : SportsAdapter.OnItemClickCallback {
            override fun onClicked(data: ArticleSports) {
                showDetailData(data, Constants.SPORTS)
            }

        })
    }

    private fun showRvTechnology(recyclerView: RecyclerView, shimmer: ShimmerFrameLayout) {
        binding.apply {
            true.loadingExtension(shimmer, recyclerView)
            recyclerView.apply {
                layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                setHasFixedSize(true)
                adapter = technologyAdapter
            }
            technologyViewModel.getTechnologyNews().observe(viewLifecycleOwner) { listArticle ->
                if (listArticle != null) {
                    when(listArticle.status){
                        Status.LOADING -> true.loadingExtension(shimmerTechnology,rvTechnology)
                        Status.SUCCESS->{
                            listArticle.data?.let { technologyAdapter.setList(it) }
                            false.loadingExtension(shimmerTechnology,rvTechnology)
                        }
                        Status.ERROR->{
                            false.loadingExtension(shimmerTechnology,rvTechnology)
                        }
                    }
                }
                Log.e("debug", "showRvHeadline: $listArticle",)
                false.loadingExtension(shimmerTechnology, rvTechnology)
            }
        }
        technologyAdapter.setOnClickCallback(object : TechnologyAdapter.OnItemClickCallback {
            override fun onClicked(data: ArticleTechnology) {
                showDetailData(data, Constants.TECHNOLOGY)
            }

        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}