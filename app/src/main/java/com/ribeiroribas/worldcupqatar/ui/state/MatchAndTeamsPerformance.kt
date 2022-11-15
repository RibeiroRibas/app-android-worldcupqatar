package com.ribeiroribas.worldcupqatar.ui.state

import com.ribeiroribas.worldcupqatar.model.Match

data class MatchAndTeamsPerformance(
    var match: Match = Match(),
    val teamsPerformance: MutableList<TeamPerformance> = mutableListOf()
)