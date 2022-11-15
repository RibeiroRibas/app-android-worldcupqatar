package com.ribeiroribas.worldcupqatar.ui.state

import com.ribeiroribas.worldcupqatar.model.Match

data class GroupStageUiState (
    val group: String = "",
    val matches: List<Match> = listOf(),
    val tableValuesList: List<List<TableValues>> = mutableListOf()
)