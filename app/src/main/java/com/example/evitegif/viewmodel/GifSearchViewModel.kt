package com.example.evitegif.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.example.evitegif.EviteGifApplication
import com.example.evitegif.MainActivity
import com.giphy.sdk.core.models.enums.MediaType
import com.giphy.sdk.core.network.api.CompletionHandler
import com.giphy.sdk.core.network.response.ListMediaResponse
import java.util.*

class GifSearchViewModel : ViewModel() {

    val gifList: MutableLiveData<ArrayList<String>> by lazy {
        MutableLiveData<ArrayList<String>>()
    }

    fun populateGifArray(searchString: String, gifOffset: Int) {

        var urlList = ArrayList<String>()

        EviteGifApplication.client.search(
            searchString,
            MediaType.gif,
            null,
            gifOffset,
            null,
            null,
            object : CompletionHandler<ListMediaResponse> {
                override fun onComplete(result: ListMediaResponse?, throwable: Throwable?) {
                    if (result != null) {
                        for (gif in result?.getData()) {
                            urlList?.add(gif.images.original.gifUrl)
                        }
                        if (gifList.value != null) {
                            gifList.value?.addAll(urlList)
                            gifList.value = gifList.value
                        } else {
                            gifList.value = urlList
                        }
                    }
                }
            }
        )
    }
}
