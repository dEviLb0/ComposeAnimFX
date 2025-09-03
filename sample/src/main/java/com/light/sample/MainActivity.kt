package com.light.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.drawscope.Stroke
import com.light.composeanimfx.buildPathAnimation


/**
 * MainActivity is a demonstration activity showcasing the capabilities of the ComposeAnimFX library.
 * This sample demonstrates how to create and control path-based animations using the library's
 * declarative API with Jetpack Compose.
 *
 * Key features demonstrated:
 * - Creating path-based animations from SVG paths
 * - Configuring animation properties (scale, pivot, translation)
 * - Styling paths with different drawing styles
 * - Sequencing and parallel execution of animations
 * - Staggered animations for multiple paths
 *
 * @see com.light.composeanimfx.buildPathAnimation
 * @see com.light.composeanimfx.PathAnimationBuilder
 * @see com.light.composeanimfx.controller.PathAnimationController
 */
class MainActivity : ComponentActivity() {
    /**
     * Called when the activity is starting. This is where most initialization should go.
     * Sets up the Compose UI and configures the path animations.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously
     * being shut down then this Bundle contains the data it most recently supplied
     * in onSaveInstanceState(Bundle). Note: Otherwise it is null.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Surface(
                modifier = Modifier
                    .fillMaxSize()
            ) {

                // Define the drawing style for the paths (stroke width, color, etc.)
                var drawStyle = Stroke(
                    width = 2f
                )

                // Configure the animation controller with path data and visual properties
                val controller = buildPathAnimation(Paths.toList())
                    .withColors(Paths.pathsColors())  // Set colors for each path
                    .withScale(3f)                    // Scale the entire drawing
                    .withPivot(10f, 10f)              // Set rotation/scaling pivot point
                    .withTranslation(120f, 300f)      // Position the drawing
                    .withDrawStyle(drawStyle)          // Apply the drawing style (stroke/fill)
                    .build()                           // Finalize and create the controller

                // The drawing is already rendered at this point
                // The controller is used to manage animations on the rendered paths
                LaunchedEffect(Unit) {
                    // Run multiple animation sequences in parallel
                    controller.parallel {
                        // Sequence 1: Animate paths one after another
                        sequence {
                            animatePath(0, 2000)  // Animate path at index 0 for 2000ms
                            animatePath(3, 2000)  // Then animate path at index 3
                            animatePath(5, 2000)  // Then animate path at index 5
                        }

                        // Sequence 2: Animate multiple paths with staggered timing
                        sequence {
                            // Animate paths 1, 2, 6, and 4 with 500ms delay between each
                            // Each animation lasts 2000ms
                            animatePathsStaggered(
                                listOf(1,2,6,4),
                                500
                            )
                        }

                    }

                }
            }
        }
    }
}


