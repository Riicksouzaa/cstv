package com.codenome.cstv.ui.players

import android.view.View
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
        player.image_url?.let {
            Glide.with(binding.root)
                .load(it)
                .addListener(glideRequestListener(binding.progressImage))
                .into(binding.impPlayerTwoImage)
        } ?: kotlin.run {
            Glide.with(binding.root)
                .load(R.drawable.rectangle_shape)
                .into(binding.impPlayerTwoImage)
            binding.progressImage.visibility = View.GONE
        }
    }
}
