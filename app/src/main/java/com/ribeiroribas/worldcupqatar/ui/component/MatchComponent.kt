package com.ribeiroribas.worldcupqatar.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.ribeiroribas.worldcupqatar.R
import com.ribeiroribas.worldcupqatar.model.Match
import com.ribeiroribas.worldcupqatar.ui.theme.getColorByGroup

@Composable
fun TeamMatchesRow(matches: List<Match>) {
    LazyRow(
        verticalAlignment = Alignment.CenterVertically
    ) {
        for (match in matches) {
            item {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    MatchInfo(values = getValues(match))
                    Row(
                        horizontalArrangement = Arrangement.SpaceAround,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        TeamMatchElement(
                            match = match,
                            modifier = Modifier.widthIn(125.dp)
                        )
                    }
                }
            }
        }
    }
}
@Composable
fun TeamMatchElement(
    match: Match,
    modifier: Modifier
) {
    TeamScoreLayout(
        team = match.team1,
        score = match.scoreTeam1,
        color = getColorByGroup(match.groupTeam1),
        arrangement = Arrangement.End,
        modifier = modifier
    )
    Text(text = stringResource(id = R.string.versus))
    TeamScoreLayout(
        team = match.team2,
        score = match.scoreTeam2,
        color = getColorByGroup(match.groupTeam2),
        arrangement = Arrangement.Start,
        modifier = modifier
    )
}

@Composable
fun TeamScoreLayout(
    modifier: Modifier,
    team: String,
    score: Int?,
    color: Color,
    arrangement: Arrangement.Horizontal,
) {
    Surface(
        color = color,
        border = BorderStroke(1.dp, Color.White),
        modifier = modifier.padding(horizontal = 4.dp)
    ) {
        Row(horizontalArrangement = arrangement) {
            if (arrangement == Arrangement.End) {
                TeamName(team = team)
                TeamScore(score = score)
            } else {
                TeamScore(score = score)
                TeamName(team = team)
            }
        }
    }
}

@Composable
private fun TeamName(
    team: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = team,
        maxLines = 1,
        modifier = modifier.padding(4.dp),
        overflow = TextOverflow.Ellipsis
    )
}

@Composable
private fun TeamScore(
    score: Int?,
    modifier: Modifier = Modifier
) {
    Surface(
        color = Color.White,
        modifier = modifier.widthIn(32.dp)
    ) {
        Text(
            text = score?.toString() ?: stringResource(id = R.string.empty_string),
            color = Color.Black,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 4.dp, bottom = 4.dp)
        )
    }
}