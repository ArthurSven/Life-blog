package com.devapps.lifeblog.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.navigation.NavArgs
import com.devapps.lifeblog.R
import com.devapps.lifeblog.views.MainActivity
import com.devapps.lifeblog.views.viewModels.NewsViewModel
import androidx.navigation.fragment.navArgs


class ArticleFragment : Fragment() {
    lateinit var viewModel: NewsViewModel
    private val args: ArticleFragmentArgs by navArgs()

    lateinit var webView: WebView


            override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_article, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        val article = args.article
        webView = view.findViewById(R.id.webView)

        webView.apply {
            webViewClient = WebViewClient()
            loadUrl(article.url)
        }

    }
}