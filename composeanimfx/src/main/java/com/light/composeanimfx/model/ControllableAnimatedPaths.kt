package com.light.composeanimfx.model

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
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.withTransform
import com.light.composeanimfx.controller.PathAnimationController

@Composable
fun ControllableAnimatedPaths(
    paths: List<Path>,
    colors: List<Color>,
): PathAnimationController {
    val scope = rememberCoroutineScope()
    val controller = remember { PathAnimationController(paths.size, scope) }

    Canvas(
        modifier = Modifier.fillMaxSize()
    ) {
        paths.forEachIndexed { index, path ->
            val pm = PathMeasure().apply { setPath(path, false) }
            val dst = Path()
            val progress = controller.pathProgress.getOrNull(index)?.value ?: 0f
            pm.getSegment(0f, pm.length * progress, dst, true)
            drawPath(dst, colors[index], style = Fill)
        }
    }

    return controller
}

@Composable
fun ControllableAnimatedPaths(
    paths: List<Path>,
    colors: List<Color>,
    scaleX: Float,
    scaleY: Float,
): PathAnimationController {
    val scope = rememberCoroutineScope()
    val controller = remember { PathAnimationController(paths.size, scope) }

    Canvas(
        modifier = Modifier.fillMaxSize()
    ) {
        withTransform(
            {
                scale(scaleX, scaleY)
            }
        ) {
            paths.forEachIndexed { index, path ->
                val pm = PathMeasure().apply { setPath(path, false) }
                val dst = Path()
                val progress = controller.pathProgress.getOrNull(index)?.value ?: 0f
                pm.getSegment(0f, pm.length * progress, dst, true)
                drawPath(dst, colors[index], style = Fill)
            }
        }
    }

    return controller
}

@Composable
fun ControllableAnimatedPaths(
    paths: List<Path>,
    colors: List<Color>,
    scaleX: Float,
    scaleY: Float,
    pivotX: Float,
    pivotY: Float,
): PathAnimationController {
    val scope = rememberCoroutineScope()
    val controller = remember { PathAnimationController(paths.size, scope) }

    Canvas(
        modifier = Modifier.fillMaxSize()
    ) {
        withTransform(
            {
                scale(scaleX, scaleY, pivot = Offset(pivotX, pivotY))
            }
        ) {
            paths.forEachIndexed { index, path ->
                val pm = PathMeasure().apply { setPath(path, false) }
                val dst = Path()
                val progress = controller.pathProgress.getOrNull(index)?.value ?: 0f
                pm.getSegment(0f, pm.length * progress, dst, true)
                drawPath(dst, colors[index], style = Fill)
            }
        }
    }

    return controller
}

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
                drawPath(dst, colors[index], style = Fill)
            }
        }
    }

    return controller
}