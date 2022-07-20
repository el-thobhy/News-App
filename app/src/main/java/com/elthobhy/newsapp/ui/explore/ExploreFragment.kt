package com.elthobhy.newsapp.ui.explore

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
import com.elthobhy.newsapp.databinding.FragmentExploreBinding
import com.elthobhy.newsapp.utils.loadingExtension
import com.elthobhy.newsapp.viewmodel.ExploreViewModel

class ExploreFragment : Fragment() {

    private var _binding: FragmentExploreBinding? = null
    private val binding get() = _binding as FragmentExploreBinding
    private lateinit var adapterExplore: ExploreAdapter
    private lateinit var exploreViewModel: ExploreViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentExploreBinding.inflate(inflater, container, false)
        adapterExplore = ExploreAdapter()
        exploreViewModel = ViewModelProvider(this).get(ExploreViewModel::class.java)
        binding.apply {
            val listRv = arrayListOf(
                rvBusiness,
                rvEntertainment,
                rvGeneral,
                rvHealth,
                rvScience,
                rvSport,
                rvTechnology
            )
            val listShimmer = arrayListOf(
                shimmerBusiness,
                shimmerEntertainment,
                shimmerGeneral,
                shimmerHealth,
                shimmerScience,
                shimmerSport,
                shimmerTechnology
            )
            for(i in listShimmer){
                for (j in listRv) {
                    false.loadingExtension(i,j)
                    showRv(j)
                }
            }

        }

        return binding.root
    }

    private fun showRv(recyclerView: RecyclerView) {
        adapterExplore.notifyDataSetChanged()
        binding.apply {
            recyclerView.apply {
                layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                setHasFixedSize(true)
                adapter = adapterExplore
            }
            val explore = exploreViewModel.getExplore()
            adapterExplore.setList(explore)

            adapterExplore.setOnClickCallback(object : ExploreAdapter.OnItemClickCallback {
                override fun onClicked(data: Article) {
                    Toast.makeText(context, "clicked", Toast.LENGTH_SHORT).show()
                }

            })
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}