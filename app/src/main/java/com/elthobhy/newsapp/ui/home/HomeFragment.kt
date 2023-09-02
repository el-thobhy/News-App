package com.elthobhy.newsapp.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.elthobhy.core.domain.model.Domain
import com.elthobhy.core.utils.Constants
import com.elthobhy.core.utils.loadingExtension
import com.elthobhy.core.utils.vo.Status
import com.elthobhy.newsapp.R
import com.elthobhy.newsapp.databinding.FragmentHomeBinding
import com.elthobhy.newsapp.ui.account.UserActivity
import com.elthobhy.newsapp.ui.detail.DetailActivity
import com.elthobhy.newsapp.viewmodel.headline.HeadlineViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import org.koin.android.ext.android.inject

class HomeFragment : Fragment() {

    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding as FragmentHomeBinding
    private val adapterHeadline by inject<HeadlineAdapter>()
    private val headlineViewModel by inject<HeadlineViewModel>()
    private var firebaseUser: FirebaseUser? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater,container,false)
        showRvHeadline()

        firebaseUser = FirebaseAuth.getInstance().currentUser
        getDataUser()
        onClick()
        return binding.root
    }

    private fun onClick() {
        binding.ivUser.setOnClickListener{
            startActivity(Intent(context,UserActivity::class.java))
        }
    }

    private fun getDataUser() {
        val uid = firebaseUser?.uid
        if (uid != null) {
            headlineViewModel.getDataUser(uid).observe(viewLifecycleOwner){
                when(it.status){
                    Status.SUCCESS -> {
                        binding.apply {
                            tvWelcome.text = getString(R.string.welcome) +" " +it.data?.nameUser
                            Glide.with(this@HomeFragment)
                                .load(it.data?.avatarUser)
                                .placeholder(android.R.color.darker_gray)
                                .into(ivUser)
                        }
                    }
                    Status.ERROR -> {
                        Toast.makeText(
                            context,
                            it.message,
                            Toast.LENGTH_LONG
                        ).show()
                    }
                    Status.LOADING -> {}
                }
            }
        }
    }


    private fun showRvHeadline() {
        adapterHeadline.notifyDataSetChanged()
        binding.apply {
            rvTopHeadlines.apply {
                layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
                setHasFixedSize(true)
                adapter = adapterHeadline
            }

            headlineViewModel.getHeadline().observe(viewLifecycleOwner) { listArticle ->
                if (listArticle != null) {
                    when(listArticle.status){
                        Status.LOADING -> {
                            true.loadingExtension(shimmerHeadline1, rvTopHeadlines)
                            true.loadingExtension(shimmerHeadline2, rvTopHeadlines)
                            true.loadingExtension(shimmerHeadline3, rvTopHeadlines)
                            Log.e("tes", "showRvHeadline: ")
                        }
                        Status.SUCCESS->{
                            Log.d("tag", "showRvHeadline: ${listArticle.data}")
                            listArticle.data?.let { adapterHeadline.setList(it) }
                            adapterHeadline.notifyDataSetChanged()
                            imageErrorHeadline.visibility = View.GONE
                            errorMessage.visibility=View.GONE
                            false.loadingExtension(shimmerHeadline1, rvTopHeadlines)
                            false.loadingExtension(shimmerHeadline2, rvTopHeadlines)
                            false.loadingExtension(shimmerHeadline3, rvTopHeadlines)
                        }
                        Status.ERROR->{
                            imageErrorHeadline.visibility = View.VISIBLE
                            errorMessage.text=listArticle.message
                            errorMessage.visibility=View.VISIBLE
                            Log.e("headline", "showRvHeadline: ${listArticle.message}" )
                            false.loadingExtension(shimmerHeadline1, rvTopHeadlines)
                            false.loadingExtension(shimmerHeadline2, rvTopHeadlines)
                            false.loadingExtension(shimmerHeadline3, rvTopHeadlines)
                        }
                    }
                }else{
                    false.loadingExtension(shimmerHeadline1, rvTopHeadlines)
                    false.loadingExtension(shimmerHeadline2, rvTopHeadlines)
                    false.loadingExtension(shimmerHeadline3, rvTopHeadlines)
                    imageErrorHeadline.visibility = View.VISIBLE
                    errorMessage.visibility=View.VISIBLE
                    errorMessage.text= getString(R.string.list_is_null)
                }
            }
        }


        adapterHeadline.setOnItemClickCallback(object : HeadlineAdapter.OnItemClickCallback{
            override fun onItemClicked(data: Domain) {
                showDetailData(data)
            }

        })
    }

    private fun showDetailData(data: Domain) {
        val intentDetail = Intent(activity,DetailActivity::class.java)
        intentDetail.putExtra(Constants.TOP_HEADLINE,data)
        startActivity(intentDetail)
        Log.e("dataHeadlineDetail", "showDetailData: $data" )
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}