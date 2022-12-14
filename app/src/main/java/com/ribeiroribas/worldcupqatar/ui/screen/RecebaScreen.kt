package com.ribeiroribas.worldcupqatar.ui.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ribeiroribas.worldcupqatar.R
import com.ribeiroribas.worldcupqatar.model.Match
import com.ribeiroribas.worldcupqatar.ui.component.*
import com.ribeiroribas.worldcupqatar.ui.state.AppUiState
import com.ribeiroribas.worldcupqatar.ui.state.TableValues
import com.ribeiroribas.worldcupqatar.ui.viewmodel.RecebaViewModel

@Composable
fun RecebaScreen(recebaViewModel: RecebaViewModel = hiltViewModel()) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(8.dp)
            .verticalScroll(rememberScrollState())
    ) {

        when (val finalMatchUiState =
            recebaViewModel.finalMatchUiState.collectAsState().value) {
            is AppUiState.Empty -> {}
            is AppUiState.Loading -> CircularProgress()
            is AppUiState.Loaded -> FinalMatchLayout(finalMatchUiState.data)
            is AppUiState.Error -> Message(message = finalMatchUiState.message)
        }

        when (val tableRankingUiState =
            recebaViewModel.tableRankingUiState.collectAsState().value) {
            is AppUiState.Empty -> {}
            is AppUiState.Loading -> CircularProgress()
            is AppUiState.Loaded -> TableCardLayout(
                tableValues = tableRankingUiState.data,
                title = stringResource(id = R.string.ranking)
            )
            is AppUiState.Error -> Message(message = tableRankingUiState.message)
        }

        when (val tableWorldCupFinalsUiState =
            recebaViewModel.tableWorldCupFinalsUiState.collectAsState().value) {
            is AppUiState.Empty -> {}
            is AppUiState.Loading -> CircularProgress()
            is AppUiState.Loaded -> TableCardLayout(
                tableValues = tableWorldCupFinalsUiState.data,
                title = stringResource(id = R.string.all_finals_of_world_cup)
            )
            is AppUiState.Error -> Message(message = tableWorldCupFinalsUiState.message)
        }

    }
}

@Composable
fun FinalMatchLayout(match: Match) {
    Card(
        modifier = Modifier.padding(8.dp),
        border = BorderStroke(2.dp, Color.White)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(bottom = 8.dp)
        ) {

            Title(text = stringResource(id = R.string.final_match))

            Spacer(modifier = Modifier.height(8.dp))

            MatchInfo(match = match)

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 4.dp),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TeamMatchLayout(
                    match = match,
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Composable
fun TableCardLayout(
    tableValues: List<List<TableValues>>,
    title: String
) {
    Card(
        modifier = Modifier.padding(8.dp),
        border = BorderStroke(2.dp, Color.White),
        backgroundColor = MaterialTheme.colors.background
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(bottom = 8.dp)
        ) {

            Title(text = title)

            Table(tableValues = tableValues)
        }
    }
}
