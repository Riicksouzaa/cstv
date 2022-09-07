package com.codenome.cstv.ui.match

import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.codenome.cstv.R
import com.codenome.cstv.databinding.ItemMatchesBinding
import com.codenome.cstv.model.Match

class MatchItemViewHolder constructor(var binding: ItemMatchesBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(match: Match) {
        if (match.isRunning == true) {
            binding.imMatchTimeText.background = AppCompatResources.getDrawable(
                binding.imMatchTimeText.context,
                R.drawable.text_shape
            )
            binding.imMatchTimeText.backgroundTintList = ContextCompat.getColorStateList(
                binding.imMatchTimeText.context,
                R.color.red_fuze
            )
            binding.imMatchTimeText.text = "AGORA"
        } else {
            binding.imMatchTimeText.text = match.date
        }
        binding.imTeamOneName.text = match.firstOpponent?.name
        binding.imTeamTwoName.text = match.secondOpponent?.name

        binding.imLeagueAndSeriesText.text = match.leagueSerieName

        Glide.with(binding.imLeagueAndSeriesImage.context)
            .load(match.league?.image_url)
            .placeholder(R.drawable.circle_shape)
            .into(binding.imLeagueAndSeriesImage)

        Glide.with(binding.imTeamOneImage.context)
            .load(match.firstOpponent?.image_url)
            .placeholder(R.drawable.circle_shape)
            .into(binding.imTeamOneImage)

        Glide.with(binding.imTeamTwoImage.context)
            .load(match.secondOpponent?.image_url)
            .placeholder(R.drawable.circle_shape)
            .into(binding.imTeamTwoImage)
    }
}