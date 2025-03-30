package com.example.setsofbricks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.setsofbricks.data.BricksSet
import com.example.setsofbricks.data.bricksSets
import com.example.setsofbricks.ui.theme.SetsOfBricksTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SetsOfBricksTheme {
                Scaffold( modifier = Modifier.fillMaxSize() ) { innerPadding ->
                    BricksSetsList(innerPadding)
                }
            }
        }
    }
}

@Composable
fun BricksSetsList(paddingValues: PaddingValues) {
    LazyColumn(
        modifier = Modifier.padding(paddingValues)
    ) {
        items(bricksSets) {
            BricksSetItem(
                bricksSet = it,
                modifier = Modifier.padding(dimensionResource(R.dimen.padding_small))
            )
        }
    }
}

@Composable
fun BricksSetItem(bricksSet: BricksSet, modifier: Modifier = Modifier) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioNoBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(R.dimen.padding_small))
            ) {
                BricksSetIcon(bricksSet.iconResourceId, bricksSet.iconDescriptionResourceId)
                BricksSetGeneralInformation(bricksSet.nameResourceId, bricksSet.descriptionResourceId)
                Spacer(modifier = Modifier.weight(1f))
                BricksSetItemButton(
                    expanded = expanded,
                    onClick = { expanded = !expanded }
                )
            }
            if (expanded) {
                BricksSetDetailInformation(
                    bricksSet.appearanceResourceId,
                    bricksSet.sizeResourceId,
                    modifier = Modifier.padding(
                        start = dimensionResource(R.dimen.padding_medium),
                        end = dimensionResource(R.dimen.padding_medium),
                        top = dimensionResource(R.dimen.padding_small),
                        bottom = dimensionResource(R.dimen.padding_medium)
                    )
                )
            }
        }
    }
}

@Composable
fun BricksSetItemButton(
    expanded: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Icon(
            imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
            contentDescription = if (expanded) stringResource(R.string.expand_less_button_content_description) else stringResource(R.string.expand_more_button_content_description),
            tint = MaterialTheme.colorScheme.secondary
        )
    }
}

@Composable
fun BricksSetIcon(
    @DrawableRes setIcon: Int,
    @StringRes iconContentDescription: Int,
    modifier: Modifier = Modifier
) {
    Image(
        modifier = modifier
            .size(dimensionResource(R.dimen.image_size))
            .padding(dimensionResource(R.dimen.padding_small))
            .clip(MaterialTheme.shapes.small),
        contentScale = ContentScale.Fit,
        painter = painterResource(setIcon),
        contentDescription = stringResource(iconContentDescription)
    )
}

@Composable
fun BricksSetGeneralInformation(
    @StringRes setName: Int,
    @StringRes setDescription: Int,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(setName),
            style = MaterialTheme.typography.displayMedium
        )
        Text(
            text = stringResource(setDescription),
            style = MaterialTheme.typography.displaySmall
        )
    }
}

@Composable
fun BricksSetDetailInformation(
    @StringRes setAppearance: Int,
    @StringRes setSize: Int,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {
        Column {
            Text(
                text = stringResource(R.string.set_origin),
                style = MaterialTheme.typography.labelSmall
            )
            Text(
                text = stringResource(R.string.set_size),
                style = MaterialTheme.typography.labelSmall
            )
        }
        Spacer(modifier = Modifier.width(dimensionResource(R.dimen.padding_medium)))
        Column {
            Text(
                text = stringResource(setAppearance),
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = stringResource(setSize),
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SetsOfBricksTheme {
        Scaffold( modifier = Modifier.fillMaxSize() ) { innerPadding ->
            BricksSetsList(innerPadding)
        }
    }
}