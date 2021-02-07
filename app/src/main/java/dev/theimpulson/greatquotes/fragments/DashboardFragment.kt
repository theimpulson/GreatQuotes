package dev.theimpulson.greatquotes.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import dev.theimpulson.greatquotes.databinding.FragmentDashboardBinding
import dev.theimpulson.greatquotes.models.RVQuotes
import dev.theimpulson.greatquotes.viewmodel.MainActivityViewModel

class DashboardFragment : Fragment() {
    private var _fragmentDashboardBinding: FragmentDashboardBinding? = null
    private val fragmentDashboardBinding get() = _fragmentDashboardBinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _fragmentDashboardBinding = FragmentDashboardBinding.inflate(inflater, container, false)
        return fragmentDashboardBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        viewModel.fetchQuotes()
        viewModel.quotesResponse.observe(viewLifecycleOwner, Observer {
            val quotesList = it.body()
            fragmentDashboardBinding.rvQuotes.adapter = quotesList?.let { it1 -> RVQuotes(it1) }
            fragmentDashboardBinding.rvQuotes.layoutManager = LinearLayoutManager(activity)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _fragmentDashboardBinding = null
    }
}