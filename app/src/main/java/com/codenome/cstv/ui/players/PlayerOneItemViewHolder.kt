package com.codenome.cstv.ui.players

import android.view.View
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
        player.image_url?.let {
            Glide.with(binding.root)
                .load(it)
                .addListener(glideRequestListener(binding.progressImage))
                .into(binding.impPlayerOneImage)
        } ?: kotlin.run {
            Glide.with(binding.root)
                .load(R.drawable.rectangle_shape)
                .into(binding.impPlayerOneImage)
            binding.progressImage.visibility = View.GONE
        }
    }
}