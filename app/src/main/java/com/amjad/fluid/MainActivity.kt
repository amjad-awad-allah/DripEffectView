package com.amjad.fluid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.amjad.dripeffectview.Dripeffectview_m

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.primary
                ) {
                    Dripeffectview_m(
                        modifier = Modifier.fillMaxSize(),
                        maxSpeed = 15f,
                        starCountPerSecond = 10,
                        animationDuration = 5000,
                        dropText = "⚽",
//                        dropImageResId = R.drawable.circle_shape,
//                        maxImageSize = 20
                    )


                }
            }
        }
    }
}
