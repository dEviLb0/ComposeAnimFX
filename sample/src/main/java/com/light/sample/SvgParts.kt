package com.light.sample

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.vector.PathParser
import androidx.compose.ui.graphics.vector.toPath

/**
 * Contains SVG path data and color definitions for the TikTok logo animation.
 * This file provides the visual components used in the animation demonstration.
 */

/**
 * Contains the raw SVG path data for different parts of the TikTok logo.
 * Each path represents a distinct visual component that can be animated separately.
 */
object TiktokLogoPaths {
    val farleftSmallArc = PathParser().parsePathString("M92.54,105.74v-10.46c-3.63-.58-7.3-.89-10.98-.94-35.6-.08-67.11,22.99-77.8,56.95s1.93,70.92,31.15,91.25c-21.89-23.43-27.97-57.53-15.52-87.07,12.45-29.54,41.1-49.02,73.15-49.72h0Z").toNodes()
    val cyanMusicNote = PathParser().parsePathString("M94.51,224.26c19.9-.03,36.26-15.71,37.13-35.59V11.23h32.42c-.66-3.71-.98-7.47-.94-11.23h-44.34v177.27c-.74,19.98-17.14,35.82-37.13,35.85-5.98-.05-11.85-1.52-17.15-4.29,6.96,9.65,18.12,15.38,30.02,15.44Z").toNodes()
    val upRightCyan = PathParser().parsePathString("M224.61,71.44v-9.86c-11.93,0-23.6-3.52-33.53-10.12,8.71,10.13,20.48,17.14,33.53,19.98h0Z").toNodes()
    val upRightRed = PathParser().parsePathString("M191.08,51.46c-9.79-11.15-15.18-25.47-15.18-40.31h-11.83c3.09,16.57,12.86,31.15,27.01,40.31h0Z").toNodes()
    val redFaux = PathParser().parsePathString("M81.56,138.67c-17.08.09-31.91,11.79-35.96,28.38-4.06,16.59,3.7,33.81,18.81,41.77-8.19-11.31-9.35-26.25-2.99-38.68,6.35-12.43,19.14-20.25,33.1-20.24,3.72.05,7.42.62,10.98,1.72v-45.11c-3.63-.54-7.3-.83-10.98-.86h-1.97v34.3c-3.58-.96-7.28-1.39-10.98-1.29Z").toNodes()
    val redRightMusicNote = PathParser().parsePathString("M224.61,71.44v34.3c-22.09-.04-43.6-7.03-61.49-19.98v90.13c-.09,44.98-36.58,81.39-81.56,81.39-16.7.03-33-5.12-46.65-14.75,22.78,24.5,58.23,32.56,89.37,20.33,31.14-12.23,51.61-42.28,51.62-75.73v-89.88c17.95,12.86,39.49,19.76,61.58,19.72v-44.17c-4.32-.01-8.63-.47-12.86-1.37Z").toNodes()
    val blackMusicNote = PathParser().parsePathString("M163.12,175.89v-90.13c17.95,12.87,39.49,19.77,61.58,19.72v-34.3c-13.05-2.76-24.85-9.68-33.62-19.72-14.16-9.16-23.93-23.73-27.01-40.31h-32.42v177.52c-.65,15.84-11.26,29.53-26.43,34.11s-31.59-.95-40.89-13.78c-15.11-7.96-22.87-25.18-18.81-41.77,4.06-16.59,18.88-28.29,35.96-28.38,3.72.03,7.42.61,10.98,1.72v-34.3c-32.23.55-61.1,20.08-73.58,49.81-12.48,29.72-6.23,64.01,15.95,87.41,13.78,9.31,30.11,14.13,46.74,13.81,44.98,0,81.46-36.41,81.56-81.39Z").toNodes()
}

/**
 * Manages the collection of paths and their corresponding colors for the TikTok logo animation.
 * This object provides utility methods to access and manipulate the logo's visual components.
 */
object Paths {
    val path1 = TiktokLogoPaths.farleftSmallArc.toPath().apply { cubicTo(200f,300f,400f,700f,600f,500f) }
    val path2 = TiktokLogoPaths.cyanMusicNote.toPath().apply { cubicTo(200f,300f,400f,700f,600f,500f) }
    val path3 = TiktokLogoPaths.upRightCyan.toPath().apply { cubicTo(200f,300f,400f,700f,600f,500f) }
    val path4 = TiktokLogoPaths.upRightRed.toPath().apply { cubicTo(200f,300f,400f,700f,600f,500f) }
    val path5 = TiktokLogoPaths.redFaux.toPath().apply { cubicTo(200f,300f,400f,700f,600f,500f) }
    val path6 = TiktokLogoPaths.redRightMusicNote.toPath().apply { cubicTo(200f,300f,400f,700f,600f,500f) }
    val path7 = TiktokLogoPaths.blackMusicNote.toPath().apply { cubicTo(200f,300f,400f,700f,600f,500f) }

    /**
     * Converts all SVG paths to a list of Compose Path objects.
     * Each path is enhanced with additional curve data for smoother animations.
     *
     * @return List of Path objects representing the TikTok logo components
     */
    fun toList(): List<Path> {
        return listOf(
            path1,
            path2,
            path3,
            path4,
            path5,
            path6,
            path7
        )
    }

    /**
     * Defines the color scheme for each path in the TikTok logo.
     * Matches the official TikTok color scheme with blue, red, and black components.
     *
     * @return List of Color objects corresponding to each path in the logo
     */
    fun pathsColors(): List<Color> {
        return listOf(
            TikTokColors.TIKTOK_BLUE,
            TikTokColors.TIKTOK_BLUE,
            TikTokColors.TIKTOK_BLUE,
            TikTokColors.TIKTOK_RED,
            TikTokColors.TIKTOK_RED,
            TikTokColors.TIKTOK_RED,
            TikTokColors.TIKTOK_BLACK
        )
    }
}

/**
 * Defines the official TikTok color palette.
 * These colors are used to maintain brand consistency in the animation.
 */
object TikTokColors {
    /** The signature TikTok cyan/blue color */
    val TIKTOK_BLUE = Color(0xFF25f4ee)
    
    /** The signature TikTok red/pink color */
    val TIKTOK_RED = Color(0xFFfe2c55)
    
    /** Standard black used for text and outlines */
    val TIKTOK_BLACK = Color(0xFF000000)
}