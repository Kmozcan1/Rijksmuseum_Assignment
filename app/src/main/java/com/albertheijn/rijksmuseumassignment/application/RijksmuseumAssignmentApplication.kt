package com.albertheijn.rijksmuseumassignment.application

import android.app.Application
import com.albertheijn.rijksmuseumassignment.BuildConfig
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class RijksmuseumAssignmentApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}