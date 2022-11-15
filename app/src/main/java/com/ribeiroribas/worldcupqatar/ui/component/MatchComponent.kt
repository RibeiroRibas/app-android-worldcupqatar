package com.ribeiroribas.worldcupqatar.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.ribeiroribas.worldcupqatar.R
import com.ribeiroribas.worldcupqatar.model.Match
import com.ribeiroribas.worldcupqatar.ui.theme.getColorByGroup
import com.ribeiroribas.worldcupqatar.util.formatLocalDate
import com.ribeiroribas.worldcupqatar.util.formatLocalTime

@Composable
fun TeamMatchesLazyRow(matches: List<Match>) {
    LazyRow(
        verticalAlignment = Alignment.CenterVertically
    ) {
        for (match in matches) {
            item {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    MatchInfo(match)
                    Row(
                        horizontalArrangement = Arrangement.SpaceAround,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        TeamMatchLayout(
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
fun TeamMatchLayout(
    match: Match,
    modifier: Modifier
) {
    TeamScore(
        teamName = match.team1,
        score = match.scoreTeam1,
        teamColor = getColorByGroup(match.groupTeam1),
        arrangement = Arrangement.End,
        modifier = modifier
    )
    Text(text = stringResource(id = R.string.versus))
    TeamScore(
        teamName = match.team2,
        score = match.scoreTeam2,
        teamColor = getColorByGroup(match.groupTeam2),
        arrangement = Arrangement.Start,
        modifier = modifier
    )
}

@Composable
private fun TeamScore(
    modifier: Modifier,
    teamName: String,
    score: Int?,
    teamColor: Color,
    arrangement: Arrangement.Horizontal,
) {
    Surface(
        color = teamColor,
        border = BorderStroke(1.dp, Color.White),
        modifier = modifier.padding(horizontal = 4.dp)
    ) {
        Row(horizontalArrangement = arrangement) {
            if (arrangement == Arrangement.End) {
                TeamName(team = teamName)
                TeamScore(score = score)
            } else {
                TeamScore(score = score)
                TeamName(team = teamName)
            }
        }
    }
}

@Composable
private fun TeamName(team: String) {
    Text(
        text = team,
        maxLines = 1,
        modifier = Modifier.padding(4.dp),
        overflow = TextOverflow.Ellipsis
    )
}

@Composable
private fun TeamScore(score: Int?) {
    Surface(
        color = Color.White,
        modifier = Modifier.widthIn(32.dp)
    ) {
        Text(
            text = score?.toString() ?: stringResource(id = R.string.empty_string),
            color = Color.Black,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 4.dp, bottom = 4.dp)
        )
    }
}

@Composable
fun MatchInfo(match: Match) {
    val values: List<String?> = getValues(match)
    var text = AnnotatedString("")
    val divider = stringResource(id = R.string.divider)
    values.forEachIndexed { index, value ->
        value?.let {
            text = buildAnnotatedString {
                append(text)
                append(it)
                if (index != (values.size - 1))
                    append(divider)
            }
        }
    }
    CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
        Text(text = text)
    }
}

@Composable
private fun getValues(match: Match): List<String?> {
    return listOf(
        formatLocalDate(
            evenDate = match.date,
            format = stringResource(id = R.string.format_E)
        ),
        formatLocalDate(
            evenDate = match.date,
            format = stringResource(id = R.string.format_dd_MM)
        ),
        formatLocalTime(match.matchTime)
    )
}