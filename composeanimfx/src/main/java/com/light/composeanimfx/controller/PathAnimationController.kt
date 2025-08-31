package com.light.composeanimfx.controller

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import com.light.composeanimfx.scope.AnimationSequenceScope
import com.light.composeanimfx.scope.ParallelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * A powerful animation controller for managing path animations in Jetpack Compose.
 * 
 * This class provides a high-level API for animating multiple paths with various configurations
 * including sequential, parallel, and staggered animations. It's designed to work seamlessly
 * with Jetpack Compose's animation system.
 *
 * @property pathCount The total number of paths that will be animated
 * @property scope The coroutine scope in which animations will be launched (defaults to Main dispatcher)
 */


class PathAnimationController(
    private val pathCount: Int,
    val scope: CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)
) {
    private val runningJobs = mutableListOf<Job>()

    /**
     * The alpha value for fill animations (0f to 1f)
     */
    val fillAlpha = Animatable(0f)
    
    /**
     * List of Animatable objects representing the progress of each path (0f to 1f)
     */
    val pathProgress = List(pathCount) { Animatable(0f) }

    /**
     * Animates a single path with default duration (1000ms)
     * 
     * @param index The index of the path to animate (0-based)
     */
    fun animatePath(
        index: Int,
    ) {
        launchJob {
            pathProgress[index].animateTo(1f)
        }
    }

    /**
     * Animates a single path with specified duration
     * 
     * @param index The index of the path to animate (0-based)
     * @param duration Duration of the animation in milliseconds (default: 1000ms)
     */
    fun animatePath(
        index: Int,
        duration: Int = 1000
    ) {
        launchJob {
            pathProgress[index].animateTo(1f, tween(duration))
        }
    }

    /**
     * Animates a single path with specified duration and delay
     * 
     * @param index The index of the path to animate (0-based)
     * @param duration Duration of the animation in milliseconds (default: 0ms)
     * @param delay Delay before starting the animation in milliseconds (default: 0ms)
     */
    fun animatePath(
        index: Int,
        duration: Int = 0,
        delay: Int = 0
    ) {
        launchJob {
            delay(delay.toLong())
            pathProgress[index].animateTo(1f, tween(duration))
        }
    }



    /**
     * Animates multiple paths with default settings
     * 
     * @param indices List of path indices to animate
     */
    fun animatePaths(
        indices: List<Int>,
    ) {
        indices.forEach {
            scope.launch {
                pathProgress[it].animateTo(1f)
            }
        }
    }

    /**
     * Animates multiple paths with specified duration
     * 
     * @param indices List of path indices to animate
     * @param duration Duration of the animation in milliseconds (default: 0ms)
     */
    fun animatePaths(
        indices: List<Int>,
        duration: Int = 0,
    ) {
        indices.forEach {
            scope.launch {
                pathProgress[it].animateTo(1f, tween(duration))
            }
        }
    }


    /**
     * Animates multiple paths with specified duration and delay
     * 
     * @param indices List of path indices to animate
     * @param duration Duration of the animation in milliseconds (default: 0ms)
     * @param delay Delay before starting the animation in milliseconds (default: 0ms)
     */
    fun animatePaths(
        indices: List<Int>,
        duration: Int = 0,
        delay: Int = 0
    ) {
        indices.forEach {
            scope.launch {
                delay(delay.toLong())
                pathProgress[it].animateTo(1f, tween(duration))
            }
        }
    }




    /**
     * Animates all paths together with default settings
     */
    fun animatePathsTogether() {
        animatePaths(pathProgress.indices.toList())
    }

    /**
     * Animates all paths together with specified duration
     * 
     * @param duration Duration of the animation in milliseconds (default: 0ms)
     */
    fun animatePathsTogether(
        duration: Int = 0
    ) {
        animatePaths(pathProgress.indices.toList(), duration)
    }

    /**
     * Animates all paths together with specified duration and delay
     * 
     * @param duration Duration of the animation in milliseconds (default: 0ms)
     * @param delay Delay before starting the animation in milliseconds (default: 0ms)
     */
    fun animatePathsTogether(
        duration: Int = 0,
        delay: Int = 0
    ) {
        animatePaths(pathProgress.indices.toList(), duration, delay)
    }

    /**
     * Animates paths one after another with a delay between each
     * 
     * @param indices List of path indices to animate in sequence
     * @param delayBetween Delay between each path animation in milliseconds (default: 500ms)
     */
    fun animatePathsStaggered(
        indices: List<Int>,
        delayBetween: Int = 500,
    ) {
        launchJob {
            delay(delayBetween.toLong())
            indices.forEachIndexed { i, index ->
                scope.launch {
                    delay(i * delayBetween.toLong())
                    animatePath(index, 0)
                }
            }
        }
    }

    /**
     * Animates paths one after another with specified duration and delay between each
     * 
     * @param indices List of path indices to animate in sequence
     * @param duration Duration of each path animation in milliseconds (default: 1000ms)
     * @param delayBetween Delay between each path animation in milliseconds (default: 500ms)
     */
    fun animatePathsStaggered(
        indices: List<Int>,
        duration: Int = 1000,
        delayBetween: Int = 500,
    ) {
        launchJob {
            delay(delayBetween.toLong())
            indices.forEachIndexed { i, index ->
                scope.launch {
                    delay(i * delayBetween.toLong())
                    animatePath(index, duration)
                }
            }
        }
    }

    /**
     * Animates all paths one after another with specified duration and delay between each
     * 
     * @param duration Duration of each path animation in milliseconds (default: 0ms)
     * @param delayBetween Delay between each path animation in milliseconds (default: 500ms)
     */
    fun animateAllPathsStaggered(
        duration: Int = 0,
        delayBetween: Int = 500
    ) {
        animatePathsStaggered(
            pathProgress.indices.toList(),
            duration,
            delayBetween
        )
    }

    /**
     * Stops all currently running animations
     */
    fun stopAll() {
        runningJobs.forEach {
            it.cancel()
        }
        runningJobs.clear()
    }

    /**
     * Resets all animations to their initial state (progress = 0)
     */
    fun resetAll() {
        stopAll()
        scope.launch {
            pathProgress.forEach {
                it.snapTo(0f)
            }
            fillAlpha.snapTo(0f)
        }
    }

    /**
     * Completes all animations immediately (progress = 1)
     */
    fun completeAll() {
        stopAll()
        scope.launch {
            pathProgress.forEach {
                it.snapTo(1f)
            }
        }
    }

    /**
     * Animates the fill with default duration (600ms)
     */
    fun animateFill() {
        launchJob {
            fillAlpha.animateTo(1f)
        }
    }

    /**
     * Animates the fill with specified duration
     * 
     * @param duration Duration of the fill animation in milliseconds (default: 600ms)
     */
    fun animateFill(duration: Int = 600) {
        launchJob {
            fillAlpha.animateTo(1f, tween(duration))
        }
    }


    /**
     * Internal function to launch a coroutine and track it for cancellation
     */
    private fun launchJob(
        block: suspend CoroutineScope.() -> Unit
    ) {
        val job = scope.launch(block = block)
        runningJobs.add(job)
        job.invokeOnCompletion{
            runningJobs.remove(job)
        }
    }

    /**
     * Creates a sequence of animations that run one after another
     * 
     * @param block Lambda with animation sequence definition
     */
    fun sequence(block: AnimationSequenceScope.() -> Unit) {
        val scope = AnimationSequenceScope(this)
        block(scope)
    }

    /**
     * Creates a group of animations that run in parallel
     * 
     * @param block Lambda with parallel animation definitions
     */
    fun parallel(
        block: ParallelScope.() -> Unit
    ) {
        val scope = ParallelScope(this)
        block(scope)
        scope.launch()
    }

}