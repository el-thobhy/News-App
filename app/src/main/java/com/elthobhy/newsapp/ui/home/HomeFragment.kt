package com.elthobhy.newsapp.ui.home

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.elthobhy.newsapp.R
import com.elthobhy.newsapp.data.source.local.entity.Article
import com.elthobhy.newsapp.databinding.FragmentHomeBinding
import com.elthobhy.newsapp.utils.loadingExtension
import com.elthobhy.newsapp.viewmodel.EverythingViewModel
import com.elthobhy.newsapp.viewmodel.HeadlineViewModel

class HomeFragment : Fragment() {

    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding as FragmentHomeBinding
    private lateinit var adapterHeadline: HeadlineAdapter
    private lateinit var adapterEverything: EverythingAdapter
    private lateinit var headlineViewModel: HeadlineViewModel
    private lateinit var everythingViewModel: EverythingViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        headlineViewModel = ViewModelProvider(this).get(HeadlineViewModel::class.java)
        everythingViewModel = ViewModelProvider(this).get(EverythingViewModel::class.java)
        _binding = FragmentHomeBinding.inflate(inflater,container,false)
        adapterHeadline = HeadlineAdapter()
        adapterEverything  = EverythingAdapter()
        showRvHeadline()
        showRvEverything()
        return binding.root
    }

    private fun showRvEverything() {
        adapterEverything.notifyDataSetChanged()
        binding.rvBusiness.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
            setHasFixedSize(true)
            adapter = adapterEverything
        }
        val everything = everythingViewModel.getEverything()
        false.loadingExtension(binding.shimmerEverything,binding.rvBusiness)
        adapterEverything.setList(everything)
        Log.e("debug", "showRvEverything: $everything", )

        adapterEverything.setOnClickCallback(object :EverythingAdapter.OnItemClickCallback{
            override fun onClicked(data: Article) {
                Toast.makeText(context,"cliked",Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun showRvHeadline() {
        adapterHeadline.notifyDataSetChanged()
        binding.rvTopHeadlines.apply {
            layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
            setHasFixedSize(true)
            adapter = adapterHeadline
        }
        val headline = headlineViewModel.getHeadline()
        false.loadingExtension(binding.shimmerHeadline,binding.rvTopHeadlines)
        adapterHeadline.setList(headline)
        Log.e("debug", "showRvHeadline: $headline", )

        adapterHeadline.setOnItemClickCallback(object : HeadlineAdapter.OnItemClickCallback{
            override fun onItemClicked(data: Article) {
                Toast.makeText(context, "clicked",Toast.LENGTH_SHORT).show()
            }

        })
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}