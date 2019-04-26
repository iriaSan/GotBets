package com.iriasan.gotbets.core.platform

import android.content.Context
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Injectable class which handles device network connection.
 */
@Singleton
class NetworkHandler
@Inject constructor(private val context: Context) {
    // TODO: CHECK CONNECTION HERE
    val isConnected get() = true
    // TODO: CHECK THIS
}