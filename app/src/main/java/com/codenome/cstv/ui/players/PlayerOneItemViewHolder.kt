package com.codenome.cstv.ui.players

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.codenome.cstv.R
import com.codenome.cstv.databinding.ItemMatchesPlayersOneBinding
import com.codenome.cstv.model.Player
import com.codenome.cstv.utils.glideRequestListener

class PlayerOneItemViewHolder constructor(var binding: ItemMatchesPlayersOneBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(player: Player) {

        binding.impPlayerOneNickname.text = player.name
        binding.impPlayerOneName.text = player.fullName

        Glide.with(binding.impPlayerOneImage.context)
            .load(player.image_url)
            .placeholder(R.drawable.rectangle_shape)
            .listener(glideRequestListener(binding.progressImage))
            .into(binding.impPlayerOneImage)
    }
}