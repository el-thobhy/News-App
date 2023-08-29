package com.elthobhy.newsapp.ui.detik

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.elthobhy.core.domain.model.Domain
import com.elthobhy.core.utils.Constants
import com.elthobhy.core.utils.vo.Status
import com.elthobhy.newsapp.databinding.ActivityIndonesiaNewsBinding
import com.elthobhy.newsapp.ui.category.technology.TechnologyAdapter
import com.elthobhy.newsapp.ui.detail.DetailActivity
import com.elthobhy.newsapp.viewmodel.indonesianews.IndonesiaNewsViewModel
import org.koin.android.ext.android.inject

class DetikActivity : AppCompatActivity() {
    private val viewModel by inject<IndonesiaNewsViewModel>()
    private val adapterList by inject<TechnologyAdapter>()
    private lateinit var binding: ActivityIndonesiaNewsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIndonesiaNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        onGetDataDetik()
        showRv()
    }

    private fun showRv() {
        binding.rvDetik.apply {
            layoutManager =
                LinearLayoutManager(this@DetikActivity, LinearLayoutManager.VERTICAL, false)
            setHasFixedSize(true)
            adapter = adapterList
        }
        adapterList.setOnClickCallback(object : TechnologyAdapter.OnItemClickCallback{
            override fun onClicked(data: Domain) {
                showDetail(data)
            }
        })
    }

    private fun showDetail(data: Domain) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(Constants.TO_DETAIL, data)
        startActivity(intent)
    }

    private fun onGetDataDetik() {
        val detik = intent.getStringExtra(Constants.DETIK)
        val suara = intent.getStringExtra(Constants.SUARA)
        val kapanLagi = intent.getStringExtra(Constants.KAPAN_LAGI)
        val liputan = intent.getStringExtra(Constants.LIPUTAN)
        binding.apply {
            when {
                detik == "detik.com" -> {
                    getData(
                        detik, detik = true, suara = false, kapanlagi = false, liputan = false
                    )
                }

                suara == "suara.com" -> {
                    getData(
                        suara, detik = false, suara = true, kapanlagi = false, liputan = false
                    )
                }

                kapanLagi == "kapanlagi.com" -> {
                    getData(
                        kapanLagi,
                        detik = false,
                        suara = false,
                        kapanlagi = true,
                        liputan = false
                    )
                }

                liputan == "liputan6.com" -> {
                    getData(
                        liputan,
                        detik = false,
                        suara = false,
                        kapanlagi = false,
                        liputan = true
                    )
                }
            }
        }
    }

    private fun getData(
        key: String,
        detik: Boolean,
        suara: Boolean,
        kapanlagi: Boolean,
        liputan: Boolean
    ) {
        viewModel.getIndonesianNews(key, detik, suara, kapanlagi, liputan)
            .observe(this@DetikActivity) { data ->
                when (data.status) {
                    Status.LOADING -> {}
                    Status.SUCCESS -> {
                        adapterList.submitList(data.data)
                        Log.e("detik", "onGetData: ${data.data}")
                    }

                    Status.ERROR -> {
                    }
                }
            }
    }
}
