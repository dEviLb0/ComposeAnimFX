package com.light.composeanimfx.model

import android.R.attr.pivotX
import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathMeasure
import androidx.compose.ui.graphics.drawscope.DrawStyle
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.withTransform
import com.light.composeanimfx.controller.PathAnimationController
import kotlinx.coroutines.CoroutineScope

/**
 * A composable that renders and animates multiple paths with individual control over their animation progress.
 *
 * This composable takes a list of paths and corresponding colors, applies transformations (scale and translation),
 * and renders them with animation progress controlled by the returned [PathAnimationController].
 *
 * @param paths List of [Path] objects to be animated. Each path will be drawn with its corresponding color.
 * @param colors List of [Color] objects corresponding to each path in the [paths] parameter.
 * @param scaleX Horizontal scale factor for all paths.
 * @param scaleY Vertical scale factor for all paths.
 * @param pivotX X-coordinate of the pivot point for scaling, relative to the path's coordinate space.
 * @param pivotY Y-coordinate of the pivot point for scaling, relative to the path's coordinate space.
 * @param translateX Horizontal translation to apply to all paths after scaling.
 * @param translateY Vertical translation to apply to all paths after scaling.
 * @return [PathAnimationController] that can be used to control the animation progress of each path.
 *         The controller provides individual control over each path's animation progress.
 *
 * @see PathAnimationController For controlling the animation progress of each path.
 *
 * @sample com.light.composeanimfx.samples.ControllableAnimatedPathsSample
 */
@Composable
fun ControllableAnimatedPaths(
    paths: List<Path>,
    colors: List<Color>,
    scaleX: Float,
    scaleY: Float,
    pivotX: Float,
    pivotY: Float,
    translateX: Float,
    translateY: Float,
    drawStyles: List<DrawStyle>?
): PathAnimationController {
    val scope = rememberCoroutineScope()
    val controller = remember { PathAnimationController(paths.size, scope) }

    Canvas(
        modifier = Modifier.fillMaxSize()
    ) {
        withTransform(
            {
                scale(scaleX, scaleY, pivot = Offset(pivotX, pivotY))
                translate(translateX, translateY)
            }
        ) {
            paths.forEachIndexed { index, path ->
                val pm = PathMeasure().apply { setPath(path, false) }
                val dst = Path()
                val progress = controller.pathProgress.getOrNull(index)?.value ?: 0f
                pm.getSegment(0f, pm.length * progress, dst, true)
                drawPath(dst, colors[index], style = drawStyles?.getOrNull(index) ?: Fill)
            }
        }
    }
    return controller
}