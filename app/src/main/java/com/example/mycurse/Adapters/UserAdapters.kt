package com.example.mycurse.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mycurse.Models.UserData
import com.example.mycurse.databinding.ItemBinding

class UserAdapters(val list: ArrayList<UserData>,
                   val onItemClick: (userdata: UserData) -> Unit
) : RecyclerView.Adapter<UserAdapters.VH>() {

    inner class VH(val itemBinding: ItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun onBind(data: UserData) {
            itemBinding.apply {
                tvDate.text = data.date
                tvCcy.text = data.code
                tvRate.text = "${data.cbPrice} UZC"
                tvCcyNmRU.text = data.title
                tvBuy.text = "Sotish Olish: ${data.nbuBuyPrice}"
                tvSell.text = "Sotish: ${data.nbuCellPrice}"

                btnCard.setOnClickListener {
                    onItemClick.invoke(data)
                }

            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        return holder.onBind(list[position])

    }

    override fun getItemCount(): Int {
        return list.size
    }
}