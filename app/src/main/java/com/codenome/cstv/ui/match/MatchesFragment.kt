package com.codenome.cstv.ui.match

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.codenome.cstv.R
import com.codenome.cstv.databinding.FragmentMatchesBinding
import com.codenome.cstv.ui.main.MainActivity
import com.codenome.cstv.utils.Resource
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MatchesFragment : Fragment() {
    private val matchesViewModel: MatchesViewModel by viewModel()
    private var _binding: FragmentMatchesBinding? = null
    private val binding: FragmentMatchesBinding get() = requireNotNull(_binding)

    private val matchAdapter: MatchAdapter by lazy {
        MatchAdapter {
            val action = MatchesFragmentDirections.actionMatchesFragmentToMatchesDetailFragment(it)
            findNavController().navigate(action)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMatchesBinding.inflate(inflater, container, false)
        if (matchAdapter.matches.isEmpty()) {
            matchesViewModel.refresh()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fmRecyclerMatches.layoutManager = LinearLayoutManager(activity)
        binding.fmRecyclerMatches.adapter = matchAdapter

        lifecycleScope.launch {
            matchesViewModel.flowState.collect { resource ->
                when (resource.status) {
                    Resource.Status.LOADING -> binding.progressMatches.visibility = View.VISIBLE
                    Resource.Status.SUCCESS -> {
                        binding.progressMatches.visibility = View.GONE
                        resource.data?.let {
                            matchAdapter.matches = it
                        }
                    }
                    Resource.Status.ERROR -> {
                        binding.progressMatches.visibility = View.GONE
                    }
                }

            }
        }

        (activity as MainActivity).toolbar?.let {
            with(it) {
                isTitleCentered = false
                setTitleTextAppearance(activity, R.style.Toolbar_TitleText)
            }
        }
    }

}