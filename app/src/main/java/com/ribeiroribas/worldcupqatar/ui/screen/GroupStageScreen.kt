package com.ribeiroribas.worldcupqatar.ui.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ribeiroribas.worldcupqatar.ui.component.*
import com.ribeiroribas.worldcupqatar.ui.state.AppUiState
import com.ribeiroribas.worldcupqatar.ui.state.GroupStageUiState
import com.ribeiroribas.worldcupqatar.ui.theme.getColorByGroup
import com.ribeiroribas.worldcupqatar.ui.viewmodel.GroupStageViewModel

@Composable
fun GroupStageScreen(
    groupStageViewModel: GroupStageViewModel = hiltViewModel()
) {
    when (val groupStageUiState =
        groupStageViewModel.matchesGroupStageUiState.collectAsState().value) {

        is AppUiState.Empty -> {}

        is AppUiState.Loading -> CircularProgress()

        is AppUiState.Loaded -> GroupStageLayout(groupStageUiState = groupStageUiState.data)

        is AppUiState.Error -> Message(message = groupStageUiState.message)
    }
}

@Composable
private fun GroupStageLayout(groupStageUiState: List<GroupStageUiState> ) {
    LazyColumn {
        groupStageUiState.forEach { groupStage ->
            item {
                Card(
                    modifier = Modifier.padding(8.dp),
                    border = BorderStroke(3.dp, getColorByGroup(groupStage.group)),
                    backgroundColor = MaterialTheme.colors.background
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(bottom = 8.dp)
                    ) {

                        Title(text = groupStage.group.uppercase())

                        Table(tableValues = groupStage.tableValuesList)

                        TeamMatchesLazyRow(matches = groupStage.matches)
                    }
                }
            }
        }
    }
}
