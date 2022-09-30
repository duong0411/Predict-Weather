package com.example.appiot.fragment

import android.os.Bundle
import android.view.*
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.example.appiot.R

class fragment2:Fragment(R.layout.fragment2) {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?


    ): View? {
        val view = inflater.inflate(R.layout.fragment2, container, false)
        // Inflate the layout for this fragment
        val mWebView = view.findViewById<View>(R.id.WebView) as WebView
        mWebView.loadUrl("https://duong04111.herokuapp.com/")
        val webSettings: WebSettings = mWebView.settings
        webSettings.javaScriptEnabled = true
        mWebView.webViewClient = WebViewClient()
        mWebView.settings.builtInZoomControls =true
        mWebView.settings.displayZoomControls = false
        mWebView.settings.useWideViewPort = true
        mWebView.settings.loadsImagesAutomatically = true
        mWebView.settings.loadWithOverviewMode = true
        mWebView.settings.domStorageEnabled = true
        setDesktopMode(mWebView,false)
        mWebView.canGoBack()
        mWebView.setOnKeyListener(View.OnKeyListener
        { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_BACK
                && event.action == MotionEvent.ACTION_UP
                && mWebView.canGoBack()
            ) {
                mWebView.goBack()
                return@OnKeyListener true

            }
            false

        })
        return view
    }
    private fun setDesktopMode(webView: WebView, enabled: Boolean) {
        var newUserAgent: String? = webView.settings.userAgentString
        if (enabled) {
            try {
                val ua: String = webView.settings.userAgentString
                val androidOSString: String = webView.settings.userAgentString.substring(
                    ua.indexOf("("),
                    ua.indexOf(")") + 1
                )
                val newUA =
                    "Mozilla/5.0 (X11; U; Linux i686; en-US; rv:1.9.0.4) Gecko/20100101 Firefox/4.0"

                newUserAgent = webView.settings.userAgentString.replace(androidOSString, newUA)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        } else {
            newUserAgent = null
        }
        webView.settings.apply {
            userAgentString = newUserAgent
            useWideViewPort = enabled
            loadWithOverviewMode = enabled
        }
        webView.reload()
    }

}