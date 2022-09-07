package com.codenome.cstv.ui.match

import androidx.recyclerview.widget.DiffUtil
import com.codenome.cstv.model.Match

class MatchListDiffCallback : DiffUtil.ItemCallback<Match>() {
    override fun areItemsTheSame(oldItem: Match, newItem: Match): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Match, newItem: Match): Boolean = oldItem == newItem
}
