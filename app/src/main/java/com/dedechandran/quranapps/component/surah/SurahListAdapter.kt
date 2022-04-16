package com.dedechandran.quranapps.component.surah

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.dedechandran.quranapps.R
import com.dedechandran.quranapps.component.bottomsheet.BottomSheetListViewHolder

class SurahListAdapter : RecyclerView.Adapter<SurahListViewHolder>() {

    private val items = mutableListOf<SurahListItem>()
    private var onItemClickListener: ((Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SurahListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            VALUE_ITEM -> SurahListViewHolder.SurahViewHolder(
                binding = DataBindingUtil.inflate(
                    layoutInflater,
                    R.layout.surah_item,
                    parent,
                    false
                ),
                onItemClickListener = onItemClickListener
            )
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: SurahListViewHolder, position: Int) {
        when (holder) {
            is SurahListViewHolder.SurahViewHolder -> holder.bind(items[position] as SurahListItem.Value)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is SurahListItem.Value -> VALUE_ITEM
        }
    }

    fun setItems(items: List<SurahListItem>) {
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(onItemClickListener: (Int) -> Unit) {
        this.onItemClickListener = onItemClickListener
    }

    companion object {
        const val VALUE_ITEM = 1
    }

}