package com.example.mycurse.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mycurse.Models.UserData
import com.example.mycurse.databinding.ItemconBinding

class Adapter(val list: List<UserData>, val summ: Float, val num: String) :
    RecyclerView.Adapter<Adapter.VH>() {

    inner class VH(val itemconBinding: ItemconBinding) :
        RecyclerView.ViewHolder(itemconBinding.root) {
        fun onBind(data: UserData) {
            itemconBinding.apply {
                tvName.text = data.code
//                val number2digits: Float =
//                    String.format("%.2f", data.cbPrice.toDouble()/ summ / num.toFloat()).toFloat()

                tvNNN.text = String.format("%.2f", data.cbPrice.toFloat() / summ * num.toFloat())
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(ItemconBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        return holder.onBind(list[position])

    }

    override fun getItemCount(): Int {
        return list.size
    }
}