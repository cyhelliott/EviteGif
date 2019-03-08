package com.example.evitegif

import android.app.Application
import com.giphy.sdk.core.network.api.GPHApiClient

class EviteGifApplication : Application() {
    companion object {
        val client: GPHApiClient = GPHApiClient("jz8uyU0tUfJ7JZyMES5bZlLPU3yTB6bN")
    }
}