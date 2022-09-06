package com.codenome.cstv.ui.players

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.codenome.cstv.R
import com.codenome.cstv.databinding.ItemMatchesPlayersTwoBinding
import com.codenome.cstv.model.Player
import com.codenome.cstv.utils.glideRequestListener

class PlayerTwoItemViewHolder constructor(var binding: ItemMatchesPlayersTwoBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(player: Player) {
        binding.impPlayerTwoNickname.text = player.name
        binding.impPlayerTwoName.text = player.fullName

        Glide.with(binding.impPlayerTwoImage.context)
            .load(player.image_url)
            .placeholder(R.drawable.rectangle_shape)
            .addListener(glideRequestListener(binding.progressImage))
            .into(binding.impPlayerTwoImage)
    }
}
