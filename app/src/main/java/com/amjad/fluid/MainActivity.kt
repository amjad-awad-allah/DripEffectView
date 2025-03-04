package com.amjad.fluid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.amjad.dripeffectview.CustomDripView

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.primary
                ) {
                    Box {
                        CustomDripView(
                            maxSpeed = 20f,
                            starCountPerSecond = 3,
                            animationDuration = 3000,
                            dropText = "⚽"
//                            dropImageResId = R.drawable.circle_shape



//                            dropImageResId = R.drawable.circle_shape,

                        )

                        Button(   colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Blue,  // Button color
                            contentColor = Color.White   ),
                            onClick = { /* Handle button click */ },
                            modifier = Modifier.align(Alignment.Center)
                        ) {
                            Text("Click Me")
                        }
                    }
                }
            }
        }
    }
}
