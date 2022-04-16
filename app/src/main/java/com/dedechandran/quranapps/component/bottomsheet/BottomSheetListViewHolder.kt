package com.dedechandran.quranapps.component.bottomsheet

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.dedechandran.quranapps.component.surah.SurahListItem
import com.dedechandran.quranapps.databinding.SurahItemBinding
import com.dedechandran.quranapps.databinding.TextItemBinding


sealed class BottomSheetListViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    class TextViewHolder(
        private val binding: TextItemBinding,
        private val onItemClickListener: ((Int) -> Unit)? = null
    ) : BottomSheetListViewHolder(binding.root) {

        fun bind(item: BottomSheetListItem.Text) {
            with(binding){
                tvText.text = item.text
                llText.setOnClickListener {
                    onItemClickListener?.invoke(adapterPosition)
                }
            }
        }
    }

}