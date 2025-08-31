package com.light.composeanimfx.scope

import com.light.composeanimfx.controller.PathAnimationController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

internal class ParallelScope internal constructor(
    private val controller: PathAnimationController
) {

    private val sequences = mutableListOf<suspend CoroutineScope.() -> Unit >()

    fun sequence(
        block: AnimationSequenceScope.() -> Unit
    ){
        val seqBlock: suspend CoroutineScope.() -> Unit = {
            val seqScope = AnimationSequenceScope(controller)
            seqScope.block()
        }
        sequences.add(seqBlock)
    }

    internal fun launch(){
        controller.scope.launch {
            sequences.forEach { seq ->
                launch{
                    seq()
                }
            }
        }
    }

}