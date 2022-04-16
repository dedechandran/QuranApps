package com.dedechandran.quranapps.component.verse

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.dedechandran.quranapps.databinding.BismillahItemBinding
import com.dedechandran.quranapps.databinding.VerseItemBinding


sealed class VerseListViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    class VerseViewHolder(
        private val binding: VerseItemBinding,
        private val onItemClickListener: ((String) -> Unit)? = null
    ) : VerseListViewHolder(binding.root) {

        fun bind(item: VerseListItem.Value) {
            with(binding) {
                tvVerseNumber.text = item.number
                tvVerseInArab.text = item.verseInArab
                tvVerseInEnglish.text = item.verseInEnglish
                tvVerseTranslation.text = item.translationId
                clVerseItem.setOnClickListener {
                    onItemClickListener?.invoke(item.number)
                }
            }
        }

    }

    class BismillahViewHolder(
        private val binding: BismillahItemBinding,
        private val onItemClickListener: (() -> Unit)? = null
    ) : VerseListViewHolder(binding.root) {

        fun bind(item: VerseListItem.Bismillah) {
            with(binding) {
                tvPreBismillah.text = item.value
            }
        }

    }

}