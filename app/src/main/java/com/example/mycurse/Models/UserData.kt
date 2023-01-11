package com.example.mycurse.Models


import com.google.gson.annotations.SerializedName

data class UserData(
    @SerializedName("cb_price")
    val cbPrice: String,
    @SerializedName("code")
    val code: String,
    @SerializedName("date")
    val date: String,
    @SerializedName("nbu_buy_price")
    val nbuBuyPrice: String,
    @SerializedName("nbu_cell_price")
    val nbuCellPrice: String,
    @SerializedName("title")
    val title: String
)