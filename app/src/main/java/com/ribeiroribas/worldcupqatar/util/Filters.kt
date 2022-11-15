package com.ribeiroribas.worldcupqatar.util

import com.ribeiroribas.worldcupqatar.model.Match
import java.util.stream.Collectors

fun filterByTeamName(
    matches: List<Match>,
    teamName: String
): List<Match> = matches
    .stream()
    .filter { match ->
        match.team1 == teamName
                || match.team2 == teamName
    }.collect(Collectors.toList())