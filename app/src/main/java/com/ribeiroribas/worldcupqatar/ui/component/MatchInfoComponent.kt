package com.ribeiroribas.worldcupqatar.ui.component

import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.buildAnnotatedString
import com.ribeiroribas.worldcupqatar.R
import com.ribeiroribas.worldcupqatar.model.Match
import com.ribeiroribas.worldcupqatar.util.formatLocalDate
import com.ribeiroribas.worldcupqatar.util.formatLocalTime

@Composable
fun MatchInfo(
    values: List<String?>
) {
    var text = AnnotatedString("")
    val divider = stringResource(id = R.string.divider)
    values.forEach { value ->
        value?.let {
            text = buildAnnotatedString {
                append(text)
                append(it)
                if (values.indexOf(value) != (values.size - 1))
                    append(divider)
            }
        }
    }
    CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
        Text(text = text)
    }
}

@Composable
fun getValues(match: Match): List<String?> {
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