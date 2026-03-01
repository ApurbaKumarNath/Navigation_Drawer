plugins {
    alias(libs.plugins.android.application) apply false

    // ONLY add Safe Args here:
    id("androidx.navigation.safeargs.kotlin") version "2.9.7" apply false
}
