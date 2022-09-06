package com.codenome.cstv.ui.match

import androidx.recyclerview.widget.DiffUtil
import com.codenome.cstv.model.Match

class MatchListDiffCallback(
    private val oldList: List<Match>,
    private val newList: List<Match>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].id == newList[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean = true
}
