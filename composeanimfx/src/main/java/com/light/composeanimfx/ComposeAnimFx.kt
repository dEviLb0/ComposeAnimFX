package com.light.composeanimfx

import com.light.composeanimfx.controller.PathAnimationController

object ComposeAnimFx {

    fun controller(pathCount: Int): PathAnimationController{
        return PathAnimationController(pathCount)
    }



}