package com.codenome.cstv.ui.match

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.codenome.cstv.databinding.ItemMatchesBinding
import com.codenome.cstv.model.Match

class MatchAdapter(private val cb: (Match) -> Unit) : RecyclerView.Adapter<MatchItemViewHolder>() {
    var matches = emptyList<Match>()
        set(value) {
            val result = DiffUtil.calculateDiff(
                MatchListDiffCallback(
                    field,
                    value
                )
            )
            result.dispatchUpdatesTo(this)
            field = value
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchItemViewHolder {
        val binding =
            ItemMatchesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MatchItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MatchItemViewHolder, position: Int) {
        holder.bind(cb, matches[position])
    }

    override fun getItemCount(): Int = matches.size
}