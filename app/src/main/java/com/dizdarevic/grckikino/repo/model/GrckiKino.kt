package com.dizdarevic.grckikino.repo.model


import com.google.gson.annotations.SerializedName

class GrckiKino : ArrayList<GrckiKino.GrckiKinoItem>() {
    data class GrckiKinoItem(
        @SerializedName("drawBreak")
        val drawBreak: Int,
        @SerializedName("drawId")
        val drawId: Int,
        @SerializedName("drawTime")
        val drawTime: Long,
        @SerializedName("gameId")
        val gameId: Int,
        @SerializedName("pricePoints")
        val pricePoints: PricePoints,
        @SerializedName("prizeCategories")
        val prizeCategories: List<PrizeCategory>,
        @SerializedName("status")
        val status: String,
        @SerializedName("visualDraw")
        val visualDraw: Int,
        @SerializedName("wagerStatistics")
        val wagerStatistics: WagerStatistics
    ) {
        data class PricePoints(
            @SerializedName("addOn")
            val addOn: List<AddOn>,
            @SerializedName("amount")
            val amount: Double
        ) {
            data class AddOn(
                @SerializedName("amount")
                val amount: Double,
                @SerializedName("gameType")
                val gameType: String
            )
        }

        data class PrizeCategory(
            @SerializedName("categoryType")
            val categoryType: Int,
            @SerializedName("distributed")
            val distributed: Double,
            @SerializedName("divident")
            val divident: Double,
            @SerializedName("fixed")
            val fixed: Double,
            @SerializedName("gameType")
            val gameType: String,
            @SerializedName("id")
            val id: Int,
            @SerializedName("jackpot")
            val jackpot: Double,
            @SerializedName("winners")
            val winners: Int
        )

        data class WagerStatistics(
            @SerializedName("addOn")
            val addOn: List<Any>,
            @SerializedName("columns")
            val columns: Int,
            @SerializedName("wagers")
            val wagers: Int
        )
    }
}