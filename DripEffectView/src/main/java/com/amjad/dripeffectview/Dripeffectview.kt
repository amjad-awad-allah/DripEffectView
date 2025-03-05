package com.amjad.dripeffectview

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.core.content.res.ResourcesCompat
import kotlinx.coroutines.delay
import kotlin.random.Random

@Composable
fun Dripeffectview(
    modifier: Modifier = Modifier,
    maxSpeed: Float = 15f,
    starCountPerSecond: Int = 10,
    animationDuration: Int = 5000,
    dropText: String? = "*",
    dropImageResId: Int? = null,
    maxImageSize: Int = 50 // Maximum image size in dp
) {
    val context = LocalContext.current
    val density = LocalDensity.current

    // Load the image once and prioritize it if available
    val dropImage = remember(dropImageResId) {
        dropImageResId?.let { resId ->
            val drawable: Drawable? = ResourcesCompat.getDrawable(context.resources, resId, null)
            drawable?.let { drawableToBitmap(it) }
        }
    }

    val stars = remember { mutableStateListOf<Star>() }
    val intervalMillis = (1000 / starCountPerSecond.coerceAtLeast(1)).toLong()
    val maxPxSize = with(density) { maxImageSize.dp.toPx() } // Convert max size to pixels

    // Generate falling stars at a fixed interval
    LaunchedEffect(Unit) {
        while (true) {
            stars.add(
                Star(
                    x = with(density) { Random.nextFloat() * 360.dp.toPx() },
                    y = 0f,
                    size = Random.nextFloat() * maxPxSize.coerceAtMost(10f) + 5f,
                    speed = Random.nextFloat() * maxSpeed + 5f
                )
            )
            delay(intervalMillis)
        }
    }

    // Update star positions over time
    LaunchedEffect(Unit) {
        while (true) {
            stars.replaceAll { star ->
                star.copy(y = star.y + star.speed)
            }
            stars.removeAll { it.y > density.run { 800.dp.toPx() } }
            delay(16L)
        }
    }

    Canvas(modifier = modifier.fillMaxSize()) {
        stars.forEach { star ->
            if (dropImage != null) {
                drawImage(
                    image = dropImage,
                    dstOffset = IntOffset(star.x.toInt(), star.y.toInt()),
                    dstSize = IntSize(maxPxSize.toInt(), maxPxSize.toInt())
                )
            } else if (!dropText.isNullOrEmpty()) {
                drawContext.canvas.nativeCanvas.drawText(
                    dropText,
                    star.x,
                    star.y,
                    Paint().apply {
                        color = Color.White.toArgb()
                        textSize = star.size * 10f
                        textAlign = Paint.Align.CENTER
                    }
                )
            } else {
                drawCircle(
                    color = Color.White,
                    center = Offset(star.x, star.y),
                    radius = star.size
                )
            }
        }
    }
}

// Convert any Drawable to an ImageBitmap
fun drawableToBitmap(drawable: Drawable): ImageBitmap {
    return if (drawable is BitmapDrawable) {
        drawable.bitmap.asImageBitmap()
    } else {
        val bitmap = Bitmap.createBitmap(
            drawable.intrinsicWidth.takeIf { it > 0 } ?: 100,
            drawable.intrinsicHeight.takeIf { it > 0 } ?: 100,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)
        bitmap.asImageBitmap()
    }
}

// Data class representing a falling star
data class Star(
    val x: Float,
    val y: Float,
    val size: Float,
    val speed: Float
)
