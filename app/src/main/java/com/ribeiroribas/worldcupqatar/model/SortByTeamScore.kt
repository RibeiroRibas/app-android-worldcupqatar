package com.ribeiroribas.worldcupqatar.model

class SortByTeamScore : Comparator<TeamScore> {
    override fun compare(team1Point: TeamScore?, team2Point: TeamScore?): Int {
        team1Point?.let { team1Score ->
            team2Point?.let { team2Points ->
                if (team1Score.points < team2Points.points) {
                    return 1
                } else if (team1Score.points > team2Points.points) {
                    return -1
                } else if (team1Score.goalsDifference < team2Points.goalsDifference) {
                    return 1
                } else if (team1Score.goalsDifference > team2Points.goalsDifference) {
                    return -1
                } else if (team1Score.goalsScored < team2Points.goalsScored) {
                    return 1
                } else if (team1Score.goalsScored > team2Points.goalsScored) {
                    return -1
                } else {
                    return 0
                }
            }
        }
        return 0
    }
}