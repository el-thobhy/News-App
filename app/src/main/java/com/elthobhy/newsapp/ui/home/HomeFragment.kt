package com.elthobhy.newsapp.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.elthobhy.newsapp.data.source.local.entity.ArticleHeadline
import com.elthobhy.newsapp.databinding.FragmentHomeBinding
import com.elthobhy.newsapp.ui.detail.DetailActivity
import com.elthobhy.newsapp.ui.explore.detik.DetikActivity
import com.elthobhy.newsapp.ui.explore.suara.SuaraActivity
import com.elthobhy.newsapp.ui.explore.kapanlagi.KapanLagiActivity
import com.elthobhy.newsapp.ui.explore.viva.VivaActivity
import com.elthobhy.newsapp.utils.Constants
import com.elthobhy.newsapp.utils.loadingExtension
import com.elthobhy.newsapp.utils.vo.Status
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
        val factory = ViewModelFactory.getInstance(requireActivity())
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
                startActivity(Intent(context,KapanLagiActivity::class.java))
            }
            vivaCo.setOnClickListener {
                startActivity(Intent(context,VivaActivity::class.java))
            }
            suaraCom.setOnClickListener {
                startActivity(Intent(context,SuaraActivity::class.java))
            }
        }
    }

    private fun showRvHeadline() {
        adapterHeadline.notifyDataSetChanged()
        binding.apply {
            true.loadingExtension(shimmerHeadline, rvTopHeadlines)
            rvTopHeadlines.apply {
                layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
                setHasFixedSize(true)
                adapter = adapterHeadline
            }

            headlineViewModel.getHeadline().observe(viewLifecycleOwner) { listArticle ->
                if (listArticle != null) {
                    when(listArticle.status){
                        Status.LOADING -> true.loadingExtension(shimmerHeadline,rvTopHeadlines)
                        Status.SUCCESS->{
                            listArticle.data?.let { adapterHeadline.setList(it) }
                            adapterHeadline.notifyDataSetChanged()
                            false.loadingExtension(shimmerHeadline,rvTopHeadlines)
                        }
                        Status.ERROR->{
                            false.loadingExtension(shimmerHeadline,rvTopHeadlines)
                        }
                    }
                }
                false.loadingExtension(shimmerHeadline, rvTopHeadlines)
            }
        }


        adapterHeadline.setOnItemClickCallback(object : HeadlineAdapter.OnItemClickCallback{
            override fun onItemClicked(data: ArticleHeadline) {
                showDetailData(data)
            }

        })
    }

    private fun showDetailData(data: ArticleHeadline) {
        val intentDetail = Intent(activity,DetailActivity::class.java)
        intentDetail.putExtra(Constants.TOP_HEADLINE,data)
        startActivity(intentDetail)
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}