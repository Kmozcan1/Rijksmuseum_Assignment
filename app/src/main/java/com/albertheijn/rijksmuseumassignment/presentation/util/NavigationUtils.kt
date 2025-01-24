package com.albertheijn.rijksmuseumassignment.presentation.util

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.navigation.NavBackStackEntry

fun slidingNavigationEnterAnimation():
        AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition =
    {
        slideIntoContainer(
            AnimatedContentTransitionScope.SlideDirection.Left,
            animationSpec = tween(durationMillis = TRANSACTION_ANIMATION_TWEEN)
        )
    }

fun slidingNavigationExitAnimation():
        AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition =
    {
        slideOutOfContainer(
            AnimatedContentTransitionScope.SlideDirection.Left,
            animationSpec = tween(durationMillis = TRANSACTION_ANIMATION_TWEEN)
        )
    }

fun slidingNavigationPopEnterAnimation():
        AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition =
    {
        slideIntoContainer(
            AnimatedContentTransitionScope.SlideDirection.Right,
            animationSpec = tween(durationMillis = TRANSACTION_ANIMATION_TWEEN)
        )
    }

fun slidingNavigationPopExitAnimation():
        AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition =
    {
        slideOutOfContainer(
            AnimatedContentTransitionScope.SlideDirection.Right,
            animationSpec = tween(durationMillis = TRANSACTION_ANIMATION_TWEEN)
        )
    }