package com.light.composeanimfx

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawStyle
import com.light.composeanimfx.controller.PathAnimationController
import com.light.composeanimfx.model.ControllableAnimatedPaths

/**
 * A builder class for creating and configuring [ControllableAnimatedPaths] with a fluent API.
 *
 * This class provides a convenient way to configure and create animations for a list of paths
 * with various transformations like scaling, translation, and color customization.
 *
 * @property paths The list of [Path] objects to be animated.
 * @constructor Creates a new PathAnimationBuilder with the specified paths.
 * @param paths The list of paths to be used in the animation.
 */
class PathAnimationBuilder(private val paths: List<Path>) {

    private var colors: List<Color> = List(paths.size) { Color.Black }
    private var scaleX: Float = 1f
    private var scaleY: Float = 1f
    private var pivotX: Float = 0f
    private var pivotY: Float = 0f
    private var translateX: Float = 0f
    private var translateY: Float = 0f
    private var drawStyles: List<DrawStyle>? = null



    /**
     * Sets a single color for all paths in the animation.
     *
     * @param color The [Color] to apply to all paths.
     * @return This builder instance for method chaining.
     */
    fun withColor(color: Color): PathAnimationBuilder {
        this.colors = List(paths.size) { color }
        return this
    }

    /**
     * Sets the colors for each path in the animation.
     *
     * @param colors A list of [Color] objects corresponding to each path.
     * @return This builder instance for method chaining.
     */
    fun withColors(colors: List<Color>): PathAnimationBuilder {
        this.colors = colors
        return this
    }

    /**
     * Sets a uniform scale for both X and Y axes.
     *
     * @param scale The scale factor to apply to both X and Y dimensions.
     * @return This builder instance for method chaining.
     */
    fun withScale(scale: Float): PathAnimationBuilder {
        this.scaleX = scale
        this.scaleY = scale
        return this
    }

    /**
     * Sets individual scale factors for X and Y axes.
     *
     * @param scaleX The scale factor for the X dimension.
     * @param scaleY The scale factor for the Y dimension.
     * @return This builder instance for method chaining.
     */
    fun withScale(scaleX: Float, scaleY: Float): PathAnimationBuilder {
        this.scaleX = scaleX
        this.scaleY = scaleY
        return this
    }

    /**
     * Sets the pivot point for scaling transformations.
     *
     * @param x The x-coordinate of the pivot point.
     * @param y The y-coordinate of the pivot point.
     * @return This builder instance for method chaining.
     */
    fun withPivot(x: Float, y: Float): PathAnimationBuilder {
        this.pivotX = x
        this.pivotY = y
        return this
    }

    /**
     * Sets the translation values for the paths.
     *
     * @param x The translation value along the X axis.
     * @param y The translation value along the Y axis.
     * @return This builder instance for method chaining.
     */
    fun withTranslation(x: Float, y: Float): PathAnimationBuilder {
        this.translateX = x
        this.translateY = y
        return this
    }

    /**
     * Sets a uniform draw style for all paths in the animation.
     *
     * @param drawStyle The [DrawStyle] to apply to all paths (e.g., [androidx.compose.ui.graphics.drawscope.Fill] or [androidx.compose.ui.graphics.drawscope.Stroke]).
     * @return This builder instance for method chaining.
     */
    fun withDrawStyle(drawStyle: DrawStyle): PathAnimationBuilder {
        this.drawStyles = List(paths.size){ drawStyle }
        return this
    }

    /**
     * Sets individual draw styles for each path in the animation.
     *
     * @param drawStyles A list of [DrawStyle] objects corresponding to each path.
     *                  The list can contain null values to use default styles for specific paths.
     * @return This builder instance for method chaining.
     */
    fun withDrawStyles(drawStyles: List<DrawStyle?>): PathAnimationBuilder {
        this.drawStyles = drawStyles as List<DrawStyle>?
        return this
    }


    /**
     * Builds and returns a [PathAnimationController] with the configured parameters.
     *
     * This method creates a [ControllableAnimatedPaths] composable with all the specified
     * transformations and returns its associated [PathAnimationController].
     *
     * @return A [PathAnimationController] that can be used to control the animation.
     */
    @Composable
    fun build(): PathAnimationController {
        return ControllableAnimatedPaths(
            paths = paths,
            colors = colors,
            scaleX = scaleX,
            scaleY = scaleY,
            pivotX =  pivotX,
            pivotY =  pivotY,
            translateX = translateX,
            translateY = translateY,
            drawStyles = drawStyles
        )
    }

}

/**
 * Creates a new [PathAnimationBuilder] with the specified paths.
 *
 * This is a convenience function that provides a more idiomatic way to start building
 * a path animation.
 *
 * @param paths The list of [Path] objects to be animated.
 * @return A new [PathAnimationBuilder] instance.
 */
fun buildPathAnimation(paths: List<Path>): PathAnimationBuilder = PathAnimationBuilder(paths)