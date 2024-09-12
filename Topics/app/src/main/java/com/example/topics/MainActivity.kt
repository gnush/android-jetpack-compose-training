package com.example.topics

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.topics.model.Topic
import com.example.topics.ui.theme.TopicsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TopicsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize().statusBarsPadding(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TopicGrid(modifier = Modifier.padding(
                        start = dimensionResource(R.dimen.padding_small),
                        top = dimensionResource(R.dimen.padding_small),
                        end = dimensionResource(R.dimen.padding_small)
                    ))
                }
            }
        }
    }
}

@Composable
fun TopicGrid(modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small)),
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)),
        modifier = modifier
    ) {
        items(Datasource.topics) { topic ->
            TopicGridItem(topic)
        }
    }
}

@Composable
fun TopicGridItem(topic: Topic, modifier: Modifier = Modifier) {
    Card(modifier = modifier) {
        Row {
            Image(
                painter = painterResource(topic.image),
                contentDescription = null,
                modifier = Modifier
                    .size(width = 68.dp, height = 68.dp)
                    .aspectRatio(1f),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp)
            ) {
                Text(
                    text = stringResource(topic.title),
                    style = MaterialTheme.typography.bodyMedium
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(top = 8.dp)
                ) {
                    Image(painter = painterResource(R.drawable.ic_grain), null)
                    Text(
                        text = topic.numItems.toString(),
                        style = MaterialTheme.typography.labelMedium,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }
        }
    }
//    Card {
//        Row {
//            Box {
//                Image(
//                    painter = painterResource(id = topic.image),
//                    contentDescription = null,
//                    modifier = modifier
//                        .size(width = 68.dp, height = 68.dp)
//                        .aspectRatio(1f),
//                    contentScale = ContentScale.Crop
//                )
//            }
//
//            Column {
//                Text(
//                    text = stringResource(id = topic.title),
//                    style = MaterialTheme.typography.bodyMedium,
//                    modifier = Modifier.padding(
//                        start = dimensionResource(R.dimen.padding_medium),
//                        top = dimensionResource(R.dimen.padding_medium),
//                        end = dimensionResource(R.dimen.padding_medium),
//                        bottom = dimensionResource(R.dimen.padding_small)
//                    )
//                )
//                Row(verticalAlignment = Alignment.CenterVertically) {
//                    Icon(
//                        painter = painterResource(R.drawable.ic_grain),
//                        contentDescription = null,
//                        modifier = Modifier
//                            .padding(start = dimensionResource(R.dimen.padding_medium))
//                    )
//                    Text(
//                        text = topic.numItems.toString(),
//                        style = MaterialTheme.typography.labelMedium,
//                        modifier = Modifier.padding(start = dimensionResource(R.dimen.padding_small))
//                    )
//                }
//            }
//        }
//    }
}

@Preview
@Composable
fun TopicGridItemPreview() {
    TopicGridItem(Topic(R.string.architecture, 58, R.drawable.architecture))
}