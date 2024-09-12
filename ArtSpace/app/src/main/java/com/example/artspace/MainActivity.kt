package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtSpaceTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.LightGray)
                ) {
                    ArtSpace()
                }
            }
        }
    }
}

@Preview
@Composable
fun ArtSpace() {
    var currentArtwork by remember { mutableStateOf(Picture.APPLE_TREE) }

    Column(
        modifier = Modifier
            .statusBarsPadding()
            .padding(horizontal = 32.dp)
            .safeDrawingPadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when(currentArtwork) {
            Picture.APPLE_TREE -> {
                ArtworkDisplay(
                    R.drawable.appletree,
                    R.string.apple_tree_description
                )
                ArtworkDescription(
                    R.string.apple_tree_title,
                    R.string.apple_tree_artist,
                    R.string.apple_tree_year
                )
                ButtonRow(
                    { currentArtwork = Picture.EMPTY_ROAD },
                    { currentArtwork = Picture.BEACH_SUNSET },
                )
            }
            Picture.BEACH_SUNSET -> {
                ArtworkDisplay(
                    R.drawable.beachsunset,
                    R.string.beach_sunset_description
                )
                ArtworkDescription(
                    R.string.beach_sunset_title,
                    R.string.beach_sunset_artist,
                    R.string.beach_sunset_year
                )
                ButtonRow(
                    { currentArtwork = Picture.APPLE_TREE },
                    { currentArtwork = Picture.EMPTY_ROAD },
                )
            }

            Picture.EMPTY_ROAD -> {
                ArtworkDisplay(
                    R.drawable.emptyroad,
                    R.string.empty_road_description
                )
                ArtworkDescription(
                    R.string.empty_road_title,
                    R.string.empty_road_artist,
                    R.string.empty_road_year
                )
                ButtonRow(
                    { currentArtwork = Picture.BEACH_SUNSET },
                    { currentArtwork = Picture.APPLE_TREE },
                )
            }
        }
    }
}

@Composable
fun ArtworkDisplay(@DrawableRes image: Int, @StringRes imageDescription: Int) {
    Surface(
        modifier = Modifier
            .background(Color.White)
            .padding(all = 16.dp)
    ) {
        Image(
            painter = painterResource(image),
            contentDescription = stringResource(imageDescription)
        )
    }

}

@Composable
fun ArtworkDescription(@StringRes artworkTitle: Int, @StringRes artworkArtist: Int, @StringRes artworkYear: Int) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .align(Alignment.CenterStart)
                .background(Color.Gray)
                .padding(horizontal = 4.dp, vertical = 4.dp)
        ) {
            Text(
                text = stringResource(artworkTitle),
                fontSize = 20.sp,
                modifier = Modifier.padding(horizontal = 4.dp)
            )
            Spacer(Modifier.height(4.dp))
            Row (
                modifier = Modifier.padding(horizontal = 4.dp)
            ) {
                Text(
                    text = stringResource(artworkArtist),
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(Modifier.width(8.dp))
                Text(
                    text = "(${stringResource(artworkYear)})",
                    fontSize = 10.sp
                )
            }
        }
    }
}

@Composable
fun ButtonRow(onClickPrevious: () -> Unit, onClickNext: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 64.dp)
    ) {
        Button(onClick = onClickPrevious) { Text(stringResource(R.string.previous_button)) }
        Button(onClick = onClickNext) { Text(stringResource(R.string.next_button)) }
    }
}

enum class Picture {
    APPLE_TREE, BEACH_SUNSET, EMPTY_ROAD
}