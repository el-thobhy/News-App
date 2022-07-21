package com.elthobhy.newsapp.ui.category

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.elthobhy.newsapp.data.source.local.entity.Article
import com.elthobhy.newsapp.databinding.FragmentCategoryBinding
import com.elthobhy.newsapp.ui.category.business.BusinessAdapter
import com.elthobhy.newsapp.ui.category.entertainment.EntertainmentAdapter
import com.elthobhy.newsapp.ui.category.general.GeneralAdapter
import com.elthobhy.newsapp.ui.category.health.HealthAdapter
import com.elthobhy.newsapp.ui.category.science.ScienceAdapter
import com.elthobhy.newsapp.ui.category.sport.SportAdapter
import com.elthobhy.newsapp.ui.category.technology.TechnologyAdapter
import com.elthobhy.newsapp.ui.detail.DetailActivity
import com.elthobhy.newsapp.utils.Constants
import com.elthobhy.newsapp.utils.loadingExtension
import com.elthobhy.newsapp.viewmodel.*
import com.facebook.shimmer.ShimmerFrameLayout

class CategoryFragment : Fragment() {

    private var _binding: FragmentCategoryBinding? = null
    private val binding get() = _binding as FragmentCategoryBinding
    private lateinit var adapterBusiness: BusinessAdapter
    private lateinit var entertainmentAdapter: EntertainmentAdapter
    private lateinit var generalAdapter: GeneralAdapter
    private lateinit var healthAdapter: HealthAdapter
    private lateinit var scienceAdapter: ScienceAdapter
    private lateinit var sportAdapter: SportAdapter
    private lateinit var technologyAdapter: TechnologyAdapter
    private lateinit var businessViewModel: BusinessViewModel
    private lateinit var entertainmentViewModel: EntertainmentViewModel
    private lateinit var generalViewModel: GeneralViewModel
    private lateinit var healthViewModel: HealthViewModel
    private lateinit var scienceViewModel: ScienceViewModel
    private lateinit var sportsViewModel: SportViewModel
    private lateinit var technologyViewModel: TechnologyViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCategoryBinding.inflate(inflater, container, false)
        val factory = ViewModelFactory.getInstance()
        businessViewModel = ViewModelProvider(this,factory)[BusinessViewModel::class.java]
        entertainmentViewModel = ViewModelProvider(this,factory)[EntertainmentViewModel::class.java]
        generalViewModel = ViewModelProvider(this,factory)[GeneralViewModel::class.java]
        healthViewModel = ViewModelProvider(this,factory)[HealthViewModel::class.java]
        scienceViewModel = ViewModelProvider(this,factory)[ScienceViewModel::class.java]
        sportsViewModel = ViewModelProvider(this,factory)[SportViewModel::class.java]
        technologyViewModel = ViewModelProvider(this,factory)[TechnologyViewModel::class.java]

        binding.apply {
            showRvBusiness(rvBusiness,shimmerBusiness)
            showRvEntertainment(rvEntertainment,shimmerEntertainment)
            showRvGeneral(rvGeneral,shimmerGeneral)
            showRvHealth(rvHealth,shimmerHealth)
            showRvScience(rvScience,shimmerScience)
            showRvSports(rvSport,shimmerSport)
            showRvTechnology(rvTechnology,shimmerTechnology)
        }

        return binding.root
    }

    private fun showRvBusiness(recyclerView: RecyclerView, shimmer: ShimmerFrameLayout) {
        adapterBusiness = BusinessAdapter()
        adapterBusiness.notifyDataSetChanged()
        binding.apply {
            true.loadingExtension(shimmer,recyclerView)
            recyclerView.apply {
                layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                setHasFixedSize(true)
                adapter = adapterBusiness
            }
            businessViewModel.getBusinessNews().observe(viewLifecycleOwner){ listArticle->
                        if(listArticle.isNotEmpty()){
                            false.loadingExtension(shimmer,recyclerView)
                            adapterBusiness.setList(listArticle)
                        }else{
                            true.loadingExtension(shimmer,recyclerView)
                        }
                    }
                }
            adapterBusiness.setOnClickCallback(object : BusinessAdapter.OnItemClickCallback {
                override fun onClicked(data: Article) {
                    showDetailData(data,Constants.BUSINESS)
                }

            })
        }

    private fun showDetailData(data: Article, key: String) {
        when (key){
            Constants.BUSINESS->{
                val intentDetail = Intent(activity, DetailActivity::class.java)
                intentDetail.putExtra(Constants.BUSINESS,data)
                startActivity(intentDetail)
            }
            Constants.ENTERTAINMENT->{
                val intentDetail = Intent(activity, DetailActivity::class.java)
                intentDetail.putExtra(Constants.ENTERTAINMENT,data)
                startActivity(intentDetail)
            }
            Constants.GENERAL->{
                val intentDetail = Intent(activity, DetailActivity::class.java)
                intentDetail.putExtra(Constants.GENERAL,data)
                startActivity(intentDetail)
            }
            Constants.HEALTH->{
                val intentDetail = Intent(activity, DetailActivity::class.java)
                intentDetail.putExtra(Constants.HEALTH,data)
                startActivity(intentDetail)
            }
            Constants.SCIENCE->{
                val intentDetail = Intent(activity, DetailActivity::class.java)
                intentDetail.putExtra(Constants.SCIENCE,data)
                startActivity(intentDetail)
            }
            Constants.SPORTS->{
                val intentDetail = Intent(activity, DetailActivity::class.java)
                intentDetail.putExtra(Constants.SPORTS,data)
                startActivity(intentDetail)
            }
            Constants.TECHNOLOGY->{
                val intentDetail = Intent(activity, DetailActivity::class.java)
                intentDetail.putExtra(Constants.TECHNOLOGY,data)
                startActivity(intentDetail)
            }
            else->{
                Log.e("else", "showDetailData: ", )
            }
        }
    }

    private fun showRvEntertainment(recyclerView: RecyclerView, shimmer: ShimmerFrameLayout) {
        entertainmentAdapter = EntertainmentAdapter()
        entertainmentAdapter.notifyDataSetChanged()
        binding.apply {
            true.loadingExtension(shimmer,recyclerView)
            recyclerView.apply {
                layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                setHasFixedSize(true)
                adapter = entertainmentAdapter
            }
            entertainmentViewModel.getEntertainment().observe(viewLifecycleOwner){ listArticle->
                if(listArticle.isNotEmpty()){
                    false.loadingExtension(shimmer,recyclerView)
                    entertainmentAdapter.setList(listArticle)
                }else{
                    true.loadingExtension(shimmer,recyclerView)
                }
            }
        }
        entertainmentAdapter.setOnClickCallback(object : EntertainmentAdapter.OnItemClickCallback {
            override fun onClicked(data: Article) {
                showDetailData(data, Constants.ENTERTAINMENT)
            }

        })
    }
    private fun showRvGeneral(recyclerView: RecyclerView, shimmer: ShimmerFrameLayout) {
        generalAdapter = GeneralAdapter()
        generalAdapter.notifyDataSetChanged()
        binding.apply {
            true.loadingExtension(shimmer,recyclerView)
            recyclerView.apply {
                layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                setHasFixedSize(true)
                adapter = generalAdapter
            }
            generalViewModel.getGeneralNews().observe(viewLifecycleOwner){ listArticle->
                if(listArticle.isNotEmpty()){
                    false.loadingExtension(shimmer,recyclerView)
                    generalAdapter.setList(listArticle)
                }else{
                    true.loadingExtension(shimmer,recyclerView)
                }
            }
        }
        generalAdapter.setOnClickCallback(object : GeneralAdapter.OnItemClickCallback {
            override fun onClicked(data: Article) {
                showDetailData(data,Constants.GENERAL)
            }

        })
    }
    private fun showRvHealth(recyclerView: RecyclerView, shimmer: ShimmerFrameLayout) {
        healthAdapter = HealthAdapter()
        healthAdapter.notifyDataSetChanged()
        binding.apply {
            true.loadingExtension(shimmer,recyclerView)
            recyclerView.apply {
                layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                setHasFixedSize(true)
                adapter = healthAdapter
            }
            healthViewModel.getHealthNews().observe(viewLifecycleOwner){ listArticle->
                if(listArticle.isNotEmpty()){
                    false.loadingExtension(shimmer,recyclerView)
                    healthAdapter.setList(listArticle)
                }else{
                    true.loadingExtension(shimmer,recyclerView)
                }
            }
        }
        healthAdapter.setOnClickCallback(object : HealthAdapter.OnItemClickCallback {
            override fun onClicked(data: Article) {
                showDetailData(data,Constants.HEALTH)
            }

        })
    }
    private fun showRvScience(recyclerView: RecyclerView, shimmer: ShimmerFrameLayout) {
        scienceAdapter = ScienceAdapter()
        scienceAdapter.notifyDataSetChanged()
        binding.apply {
            true.loadingExtension(shimmer,recyclerView)
            recyclerView.apply {
                layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                setHasFixedSize(true)
                adapter = scienceAdapter
            }
            scienceViewModel.getScienceNews().observe(viewLifecycleOwner){ listArticle->
                if(listArticle.isNotEmpty()){
                    false.loadingExtension(shimmer,recyclerView)
                    scienceAdapter.setList(listArticle)
                }else{
                    true.loadingExtension(shimmer,recyclerView)
                }
            }
        }
        scienceAdapter.setOnClickCallback(object : ScienceAdapter.OnItemClickCallback {
            override fun onClicked(data: Article) {
                showDetailData(data,Constants.SCIENCE)
            }

        })
    }
    private fun showRvSports(recyclerView: RecyclerView, shimmer: ShimmerFrameLayout) {
        sportAdapter = SportAdapter()
        sportAdapter.notifyDataSetChanged()
        binding.apply {
            true.loadingExtension(shimmer,recyclerView)
            recyclerView.apply {
                layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                setHasFixedSize(true)
                adapter = sportAdapter
            }
            sportsViewModel.getSportsNews().observe(viewLifecycleOwner){ listArticle->
                if(listArticle.isNotEmpty()){
                    false.loadingExtension(shimmer,recyclerView)
                    sportAdapter.setList(listArticle)
                }else{
                    true.loadingExtension(shimmer,recyclerView)
                }
            }
        }
        sportAdapter.setOnClickCallback(object : SportAdapter.OnItemClickCallback {
            override fun onClicked(data: Article) {
                showDetailData(data,Constants.SPORTS)
            }

        })
    }
    private fun showRvTechnology(recyclerView: RecyclerView, shimmer: ShimmerFrameLayout) {
        technologyAdapter = TechnologyAdapter()
        technologyAdapter.notifyDataSetChanged()
        binding.apply {
            true.loadingExtension(shimmer,recyclerView)
            recyclerView.apply {
                layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                setHasFixedSize(true)
                adapter = technologyAdapter
            }
            technologyViewModel.getTechnologyNews().observe(viewLifecycleOwner){ listArticle->
                if(listArticle.isNotEmpty()){
                    false.loadingExtension(shimmer,recyclerView)
                    technologyAdapter.setList(listArticle)
                }else{
                    true.loadingExtension(shimmer,recyclerView)
                }
            }
        }
        technologyAdapter.setOnClickCallback(object : TechnologyAdapter.OnItemClickCallback {
            override fun onClicked(data: Article) {
                showDetailData(data,Constants.TECHNOLOGY)
            }

        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}