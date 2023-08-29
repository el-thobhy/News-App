package com.elthobhy.newsapp.ui.category

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.elthobhy.core.domain.model.Domain
import com.elthobhy.core.utils.Constants
import com.elthobhy.core.utils.loadingExtension
import com.elthobhy.newsapp.databinding.FragmentCategoryBinding
import com.elthobhy.newsapp.ui.category.technology.TechnologyAdapter
import com.elthobhy.newsapp.ui.detail.DetailActivity
import com.elthobhy.newsapp.ui.detik.DetikActivity
import com.elthobhy.newsapp.viewmodel.search.SearchViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.koin.android.ext.android.inject

@FlowPreview
@ExperimentalCoroutinesApi
class CategoryFragment : Fragment() {

    private var _binding: FragmentCategoryBinding? = null
    private val binding get() = _binding as FragmentCategoryBinding
    private val searchViewModel by inject<SearchViewModel>()
    private val technologyAdapter by inject<TechnologyAdapter>()
    private lateinit var searchView: SearchView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCategoryBinding.inflate(inflater, container, false)
        searchView = binding.searchView
        searchList()
        showRv()
        onClick()
        return binding.root
    }

    private fun onClick() {
        binding.apply {
            card.setOnClickListener {
                val intent = Intent(activity,DetikActivity::class.java)
                intent.putExtra(Constants.DETIK,"detik.com")
                startActivity(intent)
            }
            card1.setOnClickListener {
                val intent = Intent(activity,DetikActivity::class.java)
                intent.putExtra(Constants.SUARA,"suara.com")
                startActivity(intent)
            }
            card2.setOnClickListener {
                val intent = Intent(activity,DetikActivity::class.java)
                intent.putExtra(Constants.KAPAN_LAGI,"kapanlagi.com")
                startActivity(intent)
            }
            card3.setOnClickListener {
                val intent = Intent(activity,DetikActivity::class.java)
                intent.putExtra(Constants.LIPUTAN,"liputan6.com")
                startActivity(intent)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply{
            false.loadingExtension(shimmerTechnology1, rvTechnology)
            false.loadingExtension(shimmerTechnology2, rvTechnology)
        }
    }

    private fun showRv() {
        with(binding.rvTechnology){
            layoutManager=LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
            setHasFixedSize(true)
            adapter = technologyAdapter
        }
        technologyAdapter.setOnClickCallback(object : TechnologyAdapter.OnItemClickCallback{
            override fun onClicked(data: Domain) {
                showDetailData(data)
            }

        })
    }

    private fun showDetailData(data: Domain) {
        val intentDetail = Intent(activity, DetailActivity::class.java)
        intentDetail.putExtra(Constants.SEARCH, data)
        startActivity(intentDetail)
    }

    private fun searchList() {
        binding.apply {
            tvIndoNews.visibility=View.VISIBLE
            card.visibility=View.VISIBLE
            card1.visibility=View.VISIBLE
            card2.visibility=View.VISIBLE
            card3.visibility=View.VISIBLE
        }
        searchViewModel.SearchResult.observe(viewLifecycleOwner, observerSearch)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return if(!p0.isNullOrEmpty()){
                    searchViewModel.queryChannel.value = p0
                    binding.apply {
                        tvIndoNews.visibility=View.GONE
                        card.visibility=View.GONE
                        card1.visibility=View.GONE
                        card2.visibility=View.GONE
                        card3.visibility=View.GONE
                        true.loadingExtension(shimmerTechnology1,rvTechnology)
                        true.loadingExtension(shimmerTechnology2,rvTechnology)

                    }
                    true
                }else{
                    binding.apply {
                        false.loadingExtension(shimmerTechnology1,rvTechnology)
                        false.loadingExtension(shimmerTechnology2,rvTechnology)
                    }
                    true
                }
            }
        })
        searchView.setOnCloseListener {
            binding.apply {
                false.loadingExtension(shimmerTechnology1, rvTechnology)
                false.loadingExtension(shimmerTechnology2, rvTechnology)
                rvTechnology.visibility = View.GONE
                tvIndoNews.visibility=View.VISIBLE
                card.visibility=View.VISIBLE
                card1.visibility=View.VISIBLE
                card2.visibility=View.VISIBLE
                card3.visibility=View.VISIBLE
            }
            false
        }
    }

    private val observerSearch = Observer<List<Domain>> {data->
        binding.apply {
            false.loadingExtension(shimmerTechnology1,rvTechnology)
            false.loadingExtension(shimmerTechnology2,rvTechnology)
            technologyAdapter.submitList(data)
            Log.e("hasil", "tesHasil: $data" )
        }
    }

    override fun onResume() {
        super.onResume()
        binding.rvTechnology.visibility = View.GONE
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}