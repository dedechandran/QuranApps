package com.dedechandran.quranapps.component.surah

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.dedechandran.quranapps.component.bottomsheet.BottomSheetListViewHolder
import com.dedechandran.quranapps.databinding.SurahItemBinding


sealed class SurahListViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    class SurahViewHolder(
        private val binding: SurahItemBinding,
        private val onItemClickListener: ((Int) -> Unit)? = null
    ) : SurahListViewHolder(binding.root) {

        fun bind(item: SurahListItem.Value) {
            with(binding) {
                tvSurahNumber.text = item.number
                tvSurahName.text = item.name
                tvSurahNameInArab.text = item.nameInArab
                tvRevelation.text = item.revelation
                tvVerseNumber.text = item.verseNumber
                clSurahItem.setOnClickListener {
                    onItemClickListener?.invoke(item.number.toInt())
                }
            }
        }
    }

}