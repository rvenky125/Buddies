package com.famas.buddies.android.feature_feed.buddy_overview.components

import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import coil.compose.AsyncImage
import com.google.accompanist.pager.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ParallaxImagePager(images: List<String>, autoSnap: Boolean = true, autoSnapInterval: Long = 1500) {
    val pagerState = rememberPagerState()
    val coroutine = rememberCoroutineScope()

    LaunchedEffect(key1 = pagerState.currentPage, block = {
        if (!autoSnap) {
            return@LaunchedEffect
        }
        Log.d("myTag", "Animate scrolling page: ${pagerState.currentPage} ${pagerState.pageCount}")
        delay(autoSnapInterval)
        coroutine.launch {
            if (pagerState.currentPage < pagerState.pageCount - 1) {
                pagerState.animateScrollToPage(pagerState.currentPage + 1)
            } else {
                pagerState.animateScrollToPage(0)
            }
        }
    })

    HorizontalPager(count = images.size, state = pagerState) { page ->
        AsyncImage(
            model = images[page],
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .animatedPageOffsetModifier(page, this),
            contentScale = ContentScale.Crop
        )
    }
}

@OptIn(ExperimentalPagerApi::class)
fun Modifier.animatedPageOffsetModifier(page: Int, scope: PagerScope): Modifier {
    return graphicsLayer {
        with(scope) {
            val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue

            // We animate the scaleX + scaleY, between 85% and 100%
            lerp(
                Dp(0.55f),
                Dp(1f),
                1f - pageOffset.coerceIn(0f, 1f)
            ).also { scale ->
                scaleX = scale.value
                scaleY = scale.value
            }

            // We animate the alpha, between 50% and 100%
            alpha = lerp(
                start = Dp(0.35f),
                stop = Dp(1f),
                fraction = 1f - pageOffset.coerceIn(0f, 1f)
            ).value
        }
    }
}