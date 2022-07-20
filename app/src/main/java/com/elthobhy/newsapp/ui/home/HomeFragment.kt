package com.elthobhy.newsapp.ui.home

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
import com.elthobhy.newsapp.data.source.local.entity.Article
import com.elthobhy.newsapp.databinding.FragmentHomeBinding
import com.elthobhy.newsapp.ui.explore.detik.DetikActivity
import com.elthobhy.newsapp.ui.explore.okezone.OkezoneActivity
import com.elthobhy.newsapp.ui.explore.tvone.TvoneActivity
import com.elthobhy.newsapp.ui.explore.viva.VivaActivity
import com.elthobhy.newsapp.utils.loadingExtension
import com.elthobhy.newsapp.viewmodel.HeadlineViewModel
import com.elthobhy.newsapp.viewmodel.ViewModelFactory

class HomeFragment : Fragment() {

    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding as FragmentHomeBinding
    private lateinit var adapterHeadline: HeadlineAdapter
    private lateinit var headlineViewModel: HeadlineViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater,container,false)
        val factory = ViewModelFactory.getInstance()
        headlineViewModel = ViewModelProvider(this,factory)[HeadlineViewModel::class.java]
        adapterHeadline = HeadlineAdapter()
        showRvHeadline()
        onClick()
        return binding.root
    }

    private fun onClick() {
        binding.apply {
            detikCom.setOnClickListener {
                startActivity(Intent(context,DetikActivity::class.java))
            }
            tvOne.setOnClickListener {
                startActivity(Intent(context,TvoneActivity::class.java))
            }
            vivaCo.setOnClickListener {
                startActivity(Intent(context,VivaActivity::class.java))
            }
            okezoneCom.setOnClickListener {
                startActivity(Intent(context,OkezoneActivity::class.java))
            }
        }
    }

    private fun showRvHeadline() {
        adapterHeadline.notifyDataSetChanged()
        true.loadingExtension(binding.shimmerHeadline, binding.rvTopHeadlines)
        binding.rvTopHeadlines.apply {
            layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
            setHasFixedSize(true)
            adapter = adapterHeadline
        }

        headlineViewModel.getHeadline().observe(viewLifecycleOwner) { listArticle ->
            if (listArticle.isNotEmpty()) {
                false.loadingExtension(binding.shimmerHeadline, binding.rvTopHeadlines)
                adapterHeadline.setList(listArticle)
            } else {
                true.loadingExtension(binding.shimmerHeadline, binding.rvTopHeadlines)
            }
            Log.e("debug", "showRvHeadline: $listArticle",)
        }

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