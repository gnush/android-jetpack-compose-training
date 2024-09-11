package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme
import com.example.lemonade.ui.theme.Lime
import com.example.lemonade.LemonadeStep.*
import com.example.lemonade.ui.theme.LimeAccent1

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeTheme {
                LemonadeApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun LemonadeApp() {
    var currentStep by remember { mutableStateOf(TREE) }
    var squeezes by remember { mutableStateOf(0) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {Text(stringResource(R.string.top_bar_text), fontWeight = FontWeight.Bold)},
                colors = TopAppBarDefaults.largeTopAppBarColors(containerColor = LimeAccent1)
            )
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.tertiaryContainer),
            color = MaterialTheme.colorScheme.background
        ) {
            when (currentStep) {
                TREE -> ImageButtonWithText(
                    image = painterResource(R.drawable.lemon_tree),
                    imageDescription = stringResource(R.string.lemon_tree_content_description),
                    text = stringResource(R.string.lemon_tree_tap_text),
                    onClick = { currentStep = LEMON }
                )
                LEMON -> ImageButtonWithText(
                    image = painterResource(R.drawable.lemon_squeeze),
                    imageDescription = stringResource(R.string.lemon_content_description),
                    text = stringResource(R.string.lemon_tap_text),
                    onClick = {
                        if (squeezes == 3) {
                            squeezes = 0
                            currentStep = LEMONADE
                        } else {
                            squeezes++
                        }
                    }
                )
                LEMONADE -> ImageButtonWithText(
                    image = painterResource(R.drawable.lemon_drink),
                    imageDescription = stringResource(R.string.lemonade_content_description),
                    text = stringResource(R.string.lemonade_tap_text),
                    onClick = { currentStep = EMPTY_GLASS }
                )
                EMPTY_GLASS -> ImageButtonWithText(
                    image = painterResource(R.drawable.lemon_restart),
                    imageDescription = stringResource(R.string.empty_glass_content_description),
                    text = stringResource(R.string.empty_glass_tap_text),
                    onClick = { currentStep = TREE }
                )
            }
        }
    }
}

@Composable
fun ImageButtonWithText(image: Painter, imageDescription: String, text: String, onClick: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = onClick,
            colors = ButtonDefaults.buttonColors(containerColor = Lime),
            shape = RoundedCornerShape(dimensionResource(R.dimen.button_corner_radius))
        ) {
            Image(
                painter = image,
                contentDescription = imageDescription,
                modifier = Modifier
                    .padding(dimensionResource(R.dimen.button_interior_padding))
                    .height(dimensionResource(R.dimen.button_image_height))
                    .width(dimensionResource(R.dimen.button_image_width))
            )
        }
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_vertical)))
        Text(
            text = text,
            fontSize = 18.sp,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

enum class LemonadeStep {
    TREE, LEMON, LEMONADE, EMPTY_GLASS
}