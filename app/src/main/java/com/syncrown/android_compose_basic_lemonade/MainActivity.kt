package com.syncrown.android_compose_basic_lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.syncrown.android_compose_basic_lemonade.ui.theme.AndroidcomposebasicLemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidcomposebasicLemonadeTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    LemonadeApp()
                }
            }
        }
    }

    @Preview
    @Composable
    fun LemonadeApp() {
        var currentState by remember { mutableIntStateOf(1) }
        var squeezeCount by remember { mutableIntStateOf(0) }

        when (currentState) {
            1 -> {
                LemonadeTextAndImage(
                    textResourceId = R.string.tap_lemon_description,
                    imageResourceId = R.drawable.lemon_tree,
                    contentDescriptionResourceId = R.string.lemon_tree,
                    onImageClick = {
                        currentState = 2
                        squeezeCount = (2..4).random()
                    }
                )
            }

            2 -> {
                LemonadeTextAndImage(
                    textResourceId = R.string.squeeze_lemon_description,
                    imageResourceId = R.drawable.lemon_squeeze,
                    contentDescriptionResourceId = R.string.lemon_squeeze,
                    onImageClick = {
                        squeezeCount--
                        if (squeezeCount == 0) {
                            currentState = 3
                        }
                    }
                )
            }

            3 -> {
                LemonadeTextAndImage(
                    textResourceId = R.string.drink_lemon_description,
                    imageResourceId = R.drawable.lemon_drink,
                    contentDescriptionResourceId = R.string.lemon_drink,
                    onImageClick = {
                       currentState = 4
                    }
                )
            }

            4 -> {
                LemonadeTextAndImage(
                    textResourceId = R.string.tap_empty_glass_description,
                    imageResourceId = R.drawable.lemon_restart,
                    contentDescriptionResourceId = R.string.lemon_restart,
                    onImageClick = {
                        currentState = 1
                    }
                )
            }
        }


    }

    @Composable
    fun LemonadeTextAndImage(
        textResourceId: Int,
        imageResourceId: Int,
        contentDescriptionResourceId: Int,
        onImageClick: () -> Unit,
        modifier: Modifier = Modifier
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Button (
                onClick = onImageClick,
                shape = RoundedCornerShape(40.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer)
            ) {
                Image(
                    painter = painterResource(imageResourceId),
                    contentDescription = stringResource(contentDescriptionResourceId),
                )
            }


            Spacer(Modifier.height(24.dp))
            Text(
                text = stringResource(textResourceId),
                fontSize = 18.sp,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }

}

