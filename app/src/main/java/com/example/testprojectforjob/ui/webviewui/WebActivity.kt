package com.example.testprojectforjob.ui.webviewui

import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.testprojectforjob.databinding.ActivityWebBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


@AndroidEntryPoint
class WebActivity : AppCompatActivity() {

    companion object {
        private const val START_URL = "https://www.google.com.ua"
        private const val CURRENT_LINK_KEY = "currentLinkKey"
    }

    private var _binding: ActivityWebBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityWebBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {
            webView.apply {
                webChromeClient = object : WebChromeClient() {
                    override fun onProgressChanged(view: WebView?, newProgress: Int) {
                        pbWebActive.progress = newProgress
                    }
                }
                webViewClient = ProgressiveWebClient()
            }
            val settings = webView.settings
            settings.javaScriptEnabled = true
            if (savedInstanceState != null){
                webView.restoreState(savedInstanceState.getBundle(CURRENT_LINK_KEY)!!)
            }
            else{
                webView.loadUrl(START_URL)
            }
        }

    }

    //Overriding methods to make progress bar visible when page loading and invisible it was loaded
    inner class ProgressiveWebClient() : WebViewClient() {
        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            binding.containerPbWeb.visibility = View.VISIBLE
            super.onPageStarted(view, url, favicon)
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            binding.containerPbWeb.visibility = View.GONE
        }



    }

    //Save current state of webView
    override fun onSaveInstanceState(outState: Bundle) {
        val bundle = Bundle()
        binding.webView.saveState(bundle)
        outState.putBundle(CURRENT_LINK_KEY,bundle)
        super.onSaveInstanceState(outState)
    }


}