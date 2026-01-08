package com.mubashshir.lokal.ui.player.mini

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.SkipNext
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.mubashshir.lokal.player.viewmodel.PlayerViewModel
import com.mubashshir.lokal.ui.theme.Dimens

@Composable
fun MiniPlayerBar(
    onNavigateToFullPlayer: () -> Unit,
    playerViewModel: PlayerViewModel = hiltViewModel()
) {
    val uiState by playerViewModel.uiState.collectAsState()

    if (uiState.currentSong != null) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Dimens.Spacing8)
                .clickable { onNavigateToFullPlayer() },
            elevation = CardDefaults.cardElevation(
                defaultElevation = Dimens.Spacing4
            )
        ) {
            Row(
                modifier = Modifier
                    .padding(Dimens.Spacing8)
                    .height(Dimens.Spacing64),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AsyncImage(
                    model = uiState.currentSong?.image?.medium,
                    contentDescription = null,
                    modifier = Modifier
                        .size(Dimens.Spacing48)
                        .clip(MaterialTheme.shapes.small),
                    contentScale = ContentScale.Crop
                )
                Spacer(
                    modifier = Modifier.width(
                        Dimens.Spacing8
                    )
                )
                Column(
                    modifier = Modifier.weight(
                        1f
                    )
                ) {
                    Text(
                        text = uiState.currentSong?.name
                            ?: "",
                        style = MaterialTheme.typography.titleMedium,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = uiState.currentSong?.primaryArtists
                            ?: "",
                        style = MaterialTheme.typography.bodySmall,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.End,
                ) {
                    IconButton(onClick = { playerViewModel.togglePlayPause() }) {
                        Icon(
                            imageVector = if (uiState.isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
                            contentDescription = if (uiState.isPlaying) "Pause" else "Play"
                        )
                    }
                    IconButton(onClick = { playerViewModel.playNext() }) {
                        Icon(
                            imageVector = Icons.Default.SkipNext,
                            contentDescription = "Next"
                        )
                    }
                }
            }
        }
    }
}
