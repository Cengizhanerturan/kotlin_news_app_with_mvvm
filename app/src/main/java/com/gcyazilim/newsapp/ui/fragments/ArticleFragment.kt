package com.gcyazilim.newsapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.gcyazilim.newsapp.R
import com.gcyazilim.newsapp.databinding.FragmentArticleBinding
import com.gcyazilim.newsapp.databinding.FragmentSavedNewsBinding
import com.gcyazilim.newsapp.ui.NewsActivity
import com.gcyazilim.newsapp.ui.NewsViewModel
import com.google.android.material.snackbar.Snackbar

class ArticleFragment : Fragment(R.layout.fragment_article) {
    private var _binding: FragmentArticleBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    lateinit var viewModel: NewsViewModel
    val args: ArticleFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentArticleBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as NewsActivity).viewModel
        val article = args.article
        article?.let {
            binding.webView.apply {
                webViewClient = WebViewClient()
                article.url?.let {
                    loadUrl(article.url)
                }
            }
        }

        binding.fab.setOnClickListener {
            article?.let {
                viewModel.saveArticle(article)
                Snackbar.make(view, "Article saved succesfully", Snackbar.LENGTH_SHORT).show()
            }
        }
    }
}