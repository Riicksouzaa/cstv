package com.codenome.cstv.ui.players

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.codenome.cstv.databinding.ItemMatchesPlayersOneBinding
import com.codenome.cstv.databinding.ItemMatchesPlayersTwoBinding
import com.codenome.cstv.model.Player

class PlayerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var players = emptyList<Player>()
        set(value) {
            val result = DiffUtil.calculateDiff(
                PlayerListDiffCallback(
                    field,
                    value
                )
            )
            result.dispatchUpdatesTo(this)
            field = value
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = when (viewType) {
            0 -> PlayerOneItemViewHolder(
                ItemMatchesPlayersOneBinding
                    .inflate(LayoutInflater.from(parent.context), parent, false)
            )
            else -> PlayerTwoItemViewHolder(
                ItemMatchesPlayersTwoBinding
                    .inflate(LayoutInflater.from(parent.context), parent, false)
            )
        }
        return binding
    }

    override fun getItemViewType(position: Int): Int = players[position].team

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            0 -> (holder as PlayerOneItemViewHolder).bind(players[position])
            else -> (holder as PlayerTwoItemViewHolder).bind(players[position])
        }
    }

    override fun getItemCount(): Int = players.size
}