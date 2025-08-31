# ComposeAnimFX 

**ComposeAnimFX** is an Android library built with **Jetpack Compose** that makes it easy to animate **SVG paths** or vector shapes.  
It lets you animate strokes, fills, and chain animations with different strategies (sequential, parallel, staggered).

---

## Features
- Animate vector **paths** (SVG-like)
- Stroke animation (progressive drawing)
- Fill animation (fade-in after stroke)
- Run animations:
  - Sequential (one after another)
  - Parallel (at the same time)
  - Staggered (with delays)
- Simple controller-based API
- Built for **Jetpack Compose**

---

## Installation (via Jitpack)
```gradle
repositories {
    maven { url = uri("https://jitpack.io") }
}

dependencies {
    implementation("com.github.devilb0:ComposeAnimFX:0.1.0-alpha")
}
