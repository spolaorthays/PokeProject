package com.pdi.network.data

import com.google.gson.annotations.SerializedName

data class VersionGroup(
        @SerializedName("name")
        val name: String,
        @SerializedName("url")
        val url: String
)

data class LearnMethod(
        @SerializedName("name")
        val name: String,
        @SerializedName("url")
        val url: String
)

data class VersionGroupDetails(
        @SerializedName("level_learned_at")
        val levelLearnedAt: Int,
        @SerializedName("move_learn_method")
        val moveLearnMethod: LearnMethod,
        @SerializedName("version_group")
        val versionGroup: VersionGroup
)

data class MoveDetailsDetails(
        @SerializedName("name")
        val name: String,
        @SerializedName("url")
        val url: String
)

data class MoveDetails(
        @SerializedName("move")
        val move: MoveDetailsDetails,
        @SerializedName("version_group_details")
        val version_group_details: VersionGroupDetails
)

data class Move(
        val moves: List<MoveDetails>
)
