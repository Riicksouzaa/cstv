package com.codenome.cstv.ui.match

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.paging.PagingDataAdapter
import com.codenome.cstv.databinding.ItemMatchesBinding
import com.codenome.cstv.model.Match

class MatchAdapter() :
    PagingDataAdapter<Match, MatchItemViewHolder>(MatchListDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchItemViewHolder {
        val holder = MatchItemViewHolder(
            ItemMatchesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

        holder.binding.root.setOnClickListener { view ->
            getItem(holder.absoluteAdapterPosition)?.let { match ->
                ViewCompat.setTransitionName(
                    holder.binding.imTeamOneImage,
                    "team_one_image_${match.firstOpponent?.id}"
                )
                ViewCompat.setTransitionName(
                    holder.binding.imTeamTwoImage,
                    "team_two_image_${match.secondOpponent?.id}"
                )
                ViewCompat.setTransitionName(
                    holder.binding.imTeamOneName,
                    "team_one_name_${match.firstOpponent?.id}"
                )
                ViewCompat.setTransitionName(
                    holder.binding.imTeamTwoName,
                    "team_two_name_${match.secondOpponent?.id}"
                )
                ViewCompat.setTransitionName(
                    holder.binding.imVersusText,
                    "versus_text"
                )
                val extras = FragmentNavigatorExtras(
                    holder.binding.imTeamOneImage to "team_one_image_${match.firstOpponent?.id}",
                    holder.binding.imTeamTwoImage to "team_two_image_${match.secondOpponent?.id}",
                    holder.binding.imTeamOneName to "team_one_name_${match.firstOpponent?.id}",
                    holder.binding.imTeamTwoName to "team_two_name_${match.secondOpponent?.id}",
                    holder.binding.imVersusText to "versus_text",
                )
                view.findNavController().navigate(
                    MatchesFragmentDirections.actionMatchesFragmentToMatchesDetailFragment(match),
                    extras
                )
            }
        }

        return holder
    }

    override fun onBindViewHolder(holder: MatchItemViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }
}