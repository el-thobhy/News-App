package com.elthobhy.newsapp.data.source.remote.response

import com.google.gson.annotations.SerializedName


data class ResponseTopHeadlines<T>(

	@field:SerializedName("totalResults")
	val totalResults: Int? = null,

	@field:SerializedName("articles")
	val articles: List<T?>? = null,

	@field:SerializedName("status")
	val status: String? = null
)