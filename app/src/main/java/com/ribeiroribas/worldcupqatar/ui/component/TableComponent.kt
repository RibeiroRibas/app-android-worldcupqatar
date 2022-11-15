package com.ribeiroribas.worldcupqatar.ui.component

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.ribeiroribas.worldcupqatar.ui.state.TableValues

@Composable
fun Table(tableValues: List<List<TableValues>>) {
    Surface(
        modifier = Modifier.padding(all = 8.dp),
        border = BorderStroke(1.dp, Color.White),
        color = Color.Black
    ) {
        Column {
            tableValues.forEach { tableValue ->
                Row(modifier = Modifier.fillMaxWidth()) {
                    tableValue.forEach { value ->
                        TableCell(
                            text = value.text,
                            cellColor = if (tableValues.indexOf(tableValue) == 0) {
                                Color.LightGray.copy(0.7f)
                            } else {
                                Color.LightGray.copy(0.8f)
                            },
                            modifier = Modifier.weight(value.cellWeight)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun TableCell(
    text: String,
    cellColor: Color,
    modifier: Modifier
) {
    Surface(
        modifier = modifier
            .heightIn(30.dp),
        color = cellColor
    ) {
        val context = LocalContext.current
        Text(
            text = text,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.body2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .padding(all = 1.dp)
                .clickable {
                    showToast(
                        context = context,
                        text = text
                    )
                },
            maxLines = 1
        )
    }
}

private fun showToast(
    context: Context,
    text: String
) {
    Toast
        .makeText(
            context,
            text,
            Toast.LENGTH_LONG
        ).show()
}
