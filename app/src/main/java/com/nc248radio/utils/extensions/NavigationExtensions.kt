package com.nc248radio.utils.extensions

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.NavGraph

fun NavController.navigateSafely(direction: NavDirections) {
    val currentDestination = this.currentDestination
    if (currentDestination != null) {
        val navAction = currentDestination.getAction(direction.actionId)
        if (navAction != null) {
            val destinationId: Int = navAction.destinationId.orEmpty()
            val currentNode: NavGraph? = if (currentDestination is NavGraph) currentDestination else currentDestination.parent
            if (destinationId != 0 && currentNode != null && currentNode.findNode(destinationId) != null) {
                this.navigate(direction)
            }
        }
    }
}

fun NavController.navigateSafely(@IdRes resId: Int) {
    val currentDestination = this.currentDestination
    if (currentDestination != null) {
        val navAction = currentDestination.getAction(resId)
        if (navAction != null) {
            val destinationId: Int = navAction.destinationId.orEmpty()
            val currentNode: NavGraph? = if (currentDestination is NavGraph) currentDestination else currentDestination.parent
            if (destinationId != 0 && currentNode != null && currentNode.findNode(destinationId) != null) {
                this.navigate(resId)
            }
        }
    }
}

fun NavController.navigateSafely(@IdRes resId: Int, args: Bundle?) {
    val currentDestination = this.currentDestination
    if (currentDestination != null) {
        val navAction = currentDestination.getAction(resId)
        if (navAction != null) {
            val destinationId: Int = navAction.destinationId.orEmpty()
            val currentNode: NavGraph? = if (currentDestination is NavGraph) currentDestination else currentDestination.parent
            if (destinationId != 0 && currentNode != null && currentNode.findNode(destinationId) != null) {
                this.navigate(resId, args)
            }
        }
    }
}

fun Int?.orEmpty(default: Int = 0): Int {
    return this ?: default
}