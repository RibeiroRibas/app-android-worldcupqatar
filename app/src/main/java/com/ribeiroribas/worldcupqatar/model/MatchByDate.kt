package com.ribeiroribas.worldcupqatar.model

data class MatchByDate(
    val match: Match,
    val matchesTeam1: MutableList<Match>,
    val matchesTeam2: MutableList<Match>
)