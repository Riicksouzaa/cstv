package com.codenome.cstv.ui.match_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.TransitionInflater
import com.bumptech.glide.Glide
import com.codenome.cstv.R
import com.codenome.cstv.databinding.FragmentMatchesDetailBinding
import com.codenome.cstv.ui.main.MainActivity
import com.codenome.cstv.ui.players.PlayerAdapter
import com.codenome.cstv.utils.Resource
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MatchesDetailFragment : Fragment() {

    private var _binding: FragmentMatchesDetailBinding? = null
    private val binding: FragmentMatchesDetailBinding get() = requireNotNull(_binding)
    private val matchesDetailViewModel: MatchesDetailViewModel by viewModel()

    private val args: MatchesDetailFragmentArgs by navArgs()

    private val playerOneAdapter: PlayerAdapter by lazy { PlayerAdapter() }
    private val playerTwoAdapter: PlayerAdapter by lazy { PlayerAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            findNavController().navigateUp()
        }
        sharedElementEnterTransition =
            TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.move)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMatchesDetailBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val match = args.match
        ViewCompat.setTransitionName(
            binding.fmdTeamOneImage,
            "team_one_image_${match?.firstOpponent?.id}"
        )
        ViewCompat.setTransitionName(
            binding.fmdTeamTwoImage,
            "team_two_image_${match?.secondOpponent?.id}"
        )
        ViewCompat.setTransitionName(
            binding.fmdTeamOneName,
            "team_one_name_${match?.firstOpponent?.id}"
        )
        ViewCompat.setTransitionName(
            binding.fmdTeamTwoName,
            "team_two_name_${match?.secondOpponent?.id}"
        )
        ViewCompat.setTransitionName(binding.fmdVersusText, "versus_text")

        binding.fmdTimeMatch.text = args.match?.date


        binding.fmdTeamOneName.text = args.match?.firstOpponent?.name
        binding.fmdTeamTwoName.text = args.match?.secondOpponent?.name

        Glide.with(binding.root)
            .load(args.match?.firstOpponent?.image_url)
            .placeholder(R.drawable.circle_shape)
            .into(binding.fmdTeamOneImage)

        Glide.with(binding.root)
            .load(args.match?.secondOpponent?.image_url)
            .placeholder(R.drawable.circle_shape)
            .into(binding.fmdTeamTwoImage)



        (activity as MainActivity).toolbar?.let {
            with(it) {
                isTitleCentered = true
                title = if (args.match?.serie?.name.isNullOrBlank()) {
                    args.match?.league?.name
                } else {
                    "${args.match?.serie?.name} - ${args.match?.league?.name}"
                }
                setTitleTextAppearance(activity, R.style.Toolbar_TitleTextSmall)
            }
        }
        var selectIds: String? = null
        args.match?.firstOpponent?.id?.let { selectIds = "$it" }
        args.match?.secondOpponent?.id?.let { selectIds = "$selectIds,${it}" }

        matchesDetailViewModel.getTeamsById(selectIds)

        lifecycleScope.launch {
            matchesDetailViewModel.flowState.collect { resources ->
                when (resources.status) {
                    Resource.Status.LOADING -> binding.progressMatchesDetail.visibility =
                        View.VISIBLE
                    Resource.Status.SUCCESS -> {
                        binding.progressMatchesDetail.visibility = View.GONE
                        resources.data?.let {
                            with(it) {
                                val player1 = getOrNull(0)?.players?.map { it.copy(team = 0) }
                                    ?.toMutableList()
                                val player2 = getOrNull(1)?.players?.map { it.copy(team = 1) }
                                    ?.toMutableList()


                                binding.fmdRecyclerPlayersOne.layoutManager =
                                    LinearLayoutManager(activity)
                                binding.fmdRecyclerPlayersTwo.layoutManager =
                                    LinearLayoutManager(activity)
                                binding.fmdRecyclerPlayersOne.adapter = playerOneAdapter
                                binding.fmdRecyclerPlayersTwo.adapter = playerTwoAdapter
                                player1?.let { playerOneAdapter.players = it.take(5) }
                                player2?.let { playerTwoAdapter.players = it.take(5) }
                            }
                        }
                    }
                    Resource.Status.ERROR -> {
                        binding.progressMatchesDetail.visibility = View.GONE
                    }
                }
            }
        }
    }
}