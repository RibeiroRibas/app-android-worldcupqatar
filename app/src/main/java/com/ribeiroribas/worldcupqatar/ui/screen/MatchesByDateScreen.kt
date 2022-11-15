package com.ribeiroribas.worldcupqatar.ui.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ribeiroribas.worldcupqatar.R
import com.ribeiroribas.worldcupqatar.model.Match
import com.ribeiroribas.worldcupqatar.ui.component.*
import com.ribeiroribas.worldcupqatar.ui.state.AppUiState
import com.ribeiroribas.worldcupqatar.ui.state.MatchesByDateUiState
import com.ribeiroribas.worldcupqatar.ui.state.TeamPerformance
import com.ribeiroribas.worldcupqatar.ui.viewmodel.MatchesByDateViewModel
import com.ribeiroribas.worldcupqatar.util.getEventDates
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun MatchesByDateScreen() {
    val matchesByDateViewModel: MatchesByDateViewModel = hiltViewModel()
    Column(
        modifier = Modifier.verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = matchesByDateViewModel.month.uppercase(),
            modifier = Modifier.padding(top = 8.dp)
        )
        MatchDayRow(
            selectedDate = matchesByDateViewModel.selectedDate,
            onMonthChanged = { matchesByDateViewModel.updateMonth(it) },
            onDayClick = {
                matchesByDateViewModel.getMatchesByDate(it)
                matchesByDateViewModel.changeSelectedDate(it)
            }
        )
        when (val matchesByDateState =
            matchesByDateViewModel.matchesByDateUiState.collectAsState().value) {
            is AppUiState.Empty -> {}

            is AppUiState.Loading -> CircularProgress()

            is AppUiState.Loaded -> MatchesColumn(matchesByDateUiState = matchesByDateState.data)

            is AppUiState.Error -> Message(message = matchesByDateState.message)
        }
    }
}

@Composable
fun MatchDayRow(
    modifier: Modifier = Modifier,
    selectedDate: LocalDate,
    onMonthChanged: (String) -> Unit,
    onDayClick: (LocalDate) -> Unit,
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = modifier
    ) {
        items(getEventDates()) { date ->
            onMonthChanged(date.format(DateTimeFormatter.ofPattern(stringResource(id = R.string.format_MMMM))))
            MatchDateElement(
                selectedDate = selectedDate,
                eventDate = date,
                onDayClick = onDayClick
            )
        }
    }
}

@Composable
fun MatchDateElement(
    selectedDate: LocalDate,
    eventDate: LocalDate,
    onDayClick: (LocalDate) -> Unit,

    ) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        OutlinedButton(
            onClick = {
                onDayClick(eventDate)
            },
            modifier = Modifier
                .padding(4.dp)
                .heightIn(50.dp)
                .widthIn(50.dp),
            border = BorderStroke(1.dp, Color.White),
            shape = MaterialTheme.shapes.large,
            colors =
            if (selectedDate == eventDate) {
                ButtonDefaults.outlinedButtonColors(
                    backgroundColor = MaterialTheme.colors.secondary
                )
            } else {
                ButtonDefaults.outlinedButtonColors(
                    backgroundColor = MaterialTheme.colors.primary,
                    contentColor = MaterialTheme.colors.onPrimary
                )
            }
        ) {
            Text(
                text = eventDate.format(DateTimeFormatter.ofPattern(stringResource(id = R.string.format_dd))),
                textAlign = TextAlign.Center
            )
        }
        Text(
            text = eventDate.format(DateTimeFormatter.ofPattern(stringResource(id = R.string.format_E))),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun MatchesColumn(
    matchesByDateUiState: MatchesByDateUiState,
    modifier: Modifier = Modifier
) {
    Text(
        style = MaterialTheme.typography.h6,
        text = matchesByDateUiState.stage,
        modifier = modifier.padding(top = 12.dp)
    )
    matchesByDateUiState.matchAndTeamsPerformance.forEach { matchAndTeamsPerformance ->
        Column {
            MatchCardElement(
                match = matchAndTeamsPerformance.match,
                teamsPerformance = matchAndTeamsPerformance.teamsPerformance
            )
        }
    }

}


@Composable
fun ColumnScope.MatchCardElement(
    modifier: Modifier = Modifier,
    match: Match,
    teamsPerformance: List<TeamPerformance>,
) {
    var expanded by rememberSaveable { mutableStateOf(false) }
    Card(
        modifier = modifier.padding(8.dp),
        border = BorderStroke(1.dp, Color.White),
        backgroundColor = if (expanded) {
            MaterialTheme.colors.primary
        } else {
            MaterialTheme.colors.background
        }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .weight(1f)
        ) {
            MatchInfo(getValues(match = match))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.padding(4.dp))
                TeamMatchElement(
                    match = match,
                    modifier = Modifier.weight(1f)
                )
                IconButton(onClick = { expanded = !expanded }) {
                    Icon(
                        imageVector = if (expanded) {
                            ImageVector.vectorResource(id = R.drawable.ic_expand_less)
                        } else {
                            ImageVector.vectorResource(id = R.drawable.ic_expand_more)
                        },
                        contentDescription = if (expanded) {
                            stringResource(R.string.show_less)
                        } else {
                            stringResource(R.string.show_more)
                        }
                    )
                }
            }
            if (expanded) {
                TeamPerformance(teamsPerformance = teamsPerformance)
            }
        }
    }
}

@Composable
private fun TeamPerformance(teamsPerformance: List<TeamPerformance>) {
    teamsPerformance.forEach { teamPerformance ->
        Divider(color = Color.White)
        Column(modifier = Modifier.padding(top = 4.dp)) {
            Text(
                text = stringResource(
                    id = if (teamsPerformance.indexOf(teamPerformance) == 0) {
                        R.string.world_cup_performance
                    } else {
                        R.string.qualifier_performance
                    }
                ),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Table(tableValues = teamPerformance.tableValues)

            teamPerformance.teamsMatches.forEach { teamMatches ->
                TeamMatchesRow(matches = teamMatches)
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
    }
}
