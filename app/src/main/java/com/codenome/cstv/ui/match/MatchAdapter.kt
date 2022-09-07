package com.codenome.cstv.ui.match

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
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
            getItem(holder.bindingAdapterPosition)?.let { match ->
                view.findNavController().navigate(
                    MatchesFragmentDirections.actionMatchesFragmentToMatchesDetailFragment(match)
                )
            }
        }

        return holder
    }

    override fun onBindViewHolder(holder: MatchItemViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }
}