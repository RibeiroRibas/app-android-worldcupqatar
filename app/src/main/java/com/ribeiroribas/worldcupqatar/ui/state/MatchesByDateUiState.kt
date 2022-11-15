package com.ribeiroribas.worldcupqatar.ui.state

data class MatchesByDateUiState(
    var stage: String = "",
    val matchAndTeamsPerformance: MutableList<MatchAndTeamsPerformance> = mutableListOf()
)