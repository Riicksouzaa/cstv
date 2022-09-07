package com.codenome.cstv.ui.match

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.codenome.cstv.R
import com.codenome.cstv.databinding.FragmentMatchesBinding
import com.codenome.cstv.ui.main.MainActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MatchesFragment : Fragment() {
    private val matchesViewModel: MatchesViewModel by viewModel()
    private var _binding: FragmentMatchesBinding? = null
    private val binding: FragmentMatchesBinding get() = requireNotNull(_binding)

    private val matchAdapter: MatchAdapter by lazy {
        MatchAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMatchesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fmRecyclerMatches.layoutManager = LinearLayoutManager(activity)
        binding.fmRecyclerMatches.adapter = matchAdapter

        binding.progressMatches.visibility = View.VISIBLE

        matchAdapter.addLoadStateListener {
            binding.progressMatches.isVisible = it.source.refresh is LoadState.Loading
        }

        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            matchesViewModel.matchesFlow.collect { matches ->
                binding.progressMatches.visibility = View.GONE
                matchAdapter.submitData(matches)
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