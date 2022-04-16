package com.dedechandran.quranapps.component.bottomsheet

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.dedechandran.quranapps.R
import com.dedechandran.quranapps.component.surah.SurahListItem

class BottomSheetListAdapter : RecyclerView.Adapter<BottomSheetListViewHolder>() {

    private val items = mutableListOf<BottomSheetListItem>()
    private var onItemClickListener: ((Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BottomSheetListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            TEXT_ITEM -> BottomSheetListViewHolder.TextViewHolder(
                binding = DataBindingUtil.inflate(
                    layoutInflater,
                    R.layout.text_item,
                    parent,
                    false
                ),
                onItemClickListener = onItemClickListener
            )
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: BottomSheetListViewHolder, position: Int) {
        when (holder) {
            is BottomSheetListViewHolder.TextViewHolder -> holder.bind(items[position] as BottomSheetListItem.Text)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is BottomSheetListItem.Text -> TEXT_ITEM
        }
    }

    fun setItems(items: List<BottomSheetListItem>) {
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(onItemClickListener: (Int) -> Unit) {
        this.onItemClickListener = onItemClickListener
    }

    companion object {
        const val TEXT_ITEM = 1
    }

}