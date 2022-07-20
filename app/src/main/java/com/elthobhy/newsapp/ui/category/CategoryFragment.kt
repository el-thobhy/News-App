package com.elthobhy.newsapp.ui.category

import android.os.Bundle
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
import com.elthobhy.newsapp.utils.loadingExtension
import com.elthobhy.newsapp.viewmodel.ExploreViewModel

class CategoryFragment : Fragment() {

    private var _binding: FragmentCategoryBinding? = null
    private val binding get() = _binding as FragmentCategoryBinding
    private lateinit var adapterCategory: CategoryAdapter
    private lateinit var exploreViewModel: ExploreViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCategoryBinding.inflate(inflater, container, false)
        adapterCategory = CategoryAdapter()
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
        adapterCategory.notifyDataSetChanged()
        binding.apply {
            recyclerView.apply {
                layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                setHasFixedSize(true)
                adapter = adapterCategory
            }
            val explore = exploreViewModel.getExplore()
            adapterCategory.setList(explore)

            adapterCategory.setOnClickCallback(object : CategoryAdapter.OnItemClickCallback {
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