package com.light.composeanimfx.scope

import com.light.composeanimfx.controller.PathAnimationController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * A scope that allows running multiple animation sequences in parallel.
 * 
 * This class provides a way to define multiple animation sequences that will be executed
 * concurrently when launched. Each sequence added via [sequence] will run in its own coroutine.
 *
 * @property controller The [PathAnimationController] that manages the animation execution.
 */
class ParallelScope internal constructor(
    private val controller: PathAnimationController
) {

    private val sequences = mutableListOf<suspend CoroutineScope.() -> Unit>()

    /**
     * Adds a new animation sequence to be executed in parallel with other sequences.
     * 
     * @param block A lambda with receiver that defines the animation sequence using [AnimationSequenceScope].
     * The sequence will be executed in parallel with other sequences when [launch] is called.
     */
    fun sequence(
        block: AnimationSequenceScope.() -> Unit
    ) {
        val seqBlock: suspend CoroutineScope.() -> Unit = {
            val seqScope = AnimationSequenceScope(controller)
            seqScope.block()
        }
        sequences.add(seqBlock)
    }

    /**
     * Launches all added animation sequences in parallel using coroutines.
     * 
     * This method starts all sequences that were added via [sequence] concurrently.
     * Each sequence runs in its own coroutine, allowing for true parallel execution.
     * 
     * @see sequence To add animation sequences to be launched.
     */
    internal fun launch() {
        controller.scope.launch {
            sequences.forEach { seq ->
                launch {
                    seq()
                }
            }
        }
    }

}