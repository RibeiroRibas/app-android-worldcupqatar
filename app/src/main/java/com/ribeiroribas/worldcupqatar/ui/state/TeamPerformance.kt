package com.ribeiroribas.worldcupqatar.ui.state

import com.ribeiroribas.worldcupqatar.model.Match

data class TeamPerformance(
    val teamsMatches: MutableList<MutableList<Match>> = mutableListOf(),
    var tableValues: List<List<TableValues>> = mutableListOf()
)