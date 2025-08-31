package com.light.composeanimfx.scope

import com.light.composeanimfx.controller.PathAnimationController

/**
 * Utility class for creating sequential animation sequences.
 * Automatically manages delays between animations to create smooth sequences.
 *
 * @property controller The animation controller used to execute animations
 * @property currentDelay Cumulative delay since the start of the sequence (in milliseconds)
 */
internal class AnimationSequenceScope internal constructor(
    private val controller: PathAnimationController
) {
    private var currentDelay = 0L

    /**
     * Animates a specific path with default duration.
     * @param index The index of the path to animate
     */
    fun animatePath(index: Int) {
        controller.animatePath(index)
    }

    /**
     * Animates a specific path with custom duration.
     * @param index The index of the path to animate
     * @param duration Animation duration in milliseconds
     */
    fun animatePath(index: Int, duration: Int) {
        controller.animatePath(index, duration)
        currentDelay += duration
    }

    /**
     * Animates a specific path with custom duration and delay.
     * The delay is added to the sequence's cumulative delay.
     *
     * @param index The index of the path to animate
     * @param duration Animation duration in milliseconds
     * @param delay Delay before starting the animation in milliseconds
     */
    fun animatePath(index: Int, duration: Int, delay: Int) {
        controller.animatePath(index, duration, (currentDelay + delay).toInt())
        currentDelay += duration + delay
    }

    /**
     * Animates multiple paths simultaneously.
     * @param indices List of path indices to animate
     * @param duration Animation duration in milliseconds
     */
    fun animatePathsTogether(indices: List<Int>, duration: Int) {
        controller.animatePathsTogether(duration)
        currentDelay += duration
    }

    /**
     * Animates multiple paths simultaneously with a custom delay.
     * @param indices List of path indices to animate
     * @param duration Animation duration in milliseconds
     * @param delay Delay before starting the animation in milliseconds
     */
    fun animatePathsTogether(indices: List<Int>, duration: Int, delay: Int) {
        controller.animatePathsTogether(duration, (currentDelay + delay).toInt())
        currentDelay += duration + delay
    }

    /**
     * Animates multiple paths one after another with a delay between each.
     * @param indices List of path indices to animate
     * @param delayBetween Delay between each animation in milliseconds
     */
    fun animatePathsStaggered(indices: List<Int>, delayBetween: Int) {
        controller.animatePathsStaggered(indices, delayBetween)
        currentDelay += (indices.size * delayBetween)
    }

    /**
     * Animates multiple paths one after another with custom duration and delay.
     * @param indices List of path indices to animate
     * @param durationPerPath Duration of each animation in milliseconds
     * @param delayBetween Delay between each animation in milliseconds
     */
    fun animatePathsStaggered(indices: List<Int>, durationPerPath: Int, delayBetween: Int) {
        controller.animatePathsStaggered(indices, durationPerPath, delayBetween)
        currentDelay += (indices.size * (delayBetween + durationPerPath))
    }

    /**
     * Animates all available paths sequentially.
     * @param durationPerPath Duration of each animation in milliseconds
     * @param delayBetween Delay between each animation in milliseconds
     */
    fun animateAllPathsStaggered(durationPerPath: Int, delayBetween: Int) {
        controller.animateAllPathsStaggered(durationPerPath, delayBetween)
        currentDelay += (controller.pathProgress.size * (delayBetween + durationPerPath))
    }

    /**
     * Animates the filling of paths with default parameters.
     */
    fun animateFill() {
        controller.animateFill()
    }

    /**
     * Animates the filling of paths with custom duration and delay.
     * @param duration Animation duration in milliseconds
     * @param delay Delay before starting the animation in milliseconds
     */
    fun animateFill(duration: Int, delay: Int) {
        controller.animateFill()
        currentDelay += duration + delay
    }
}