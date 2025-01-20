package com.albertheijn.rijksmuseumassignment.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.albertheijn.rijksmuseumassignment.R
import com.albertheijn.rijksmuseumassignment.presentation.theme.RijksmuseumAssignmentTheme

@Composable
fun TopBarScreen(
    topBar: @Composable () -> Unit,
    content: @Composable () -> Unit
) {
    RijksmuseumAssignmentTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = { topBar() }
        ) { paddingValues ->
            Box(modifier = Modifier.padding(paddingValues)) {
                content()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FlowTopBar(
    navController: NavController,
    hasBackIcon: Boolean = false,
    titleContent: @Composable () -> Unit
) {
    CenterAlignedTopAppBar(
        title = titleContent,
        navigationIcon = {
            when (hasBackIcon) {
                true -> IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        painter = painterResource(R.drawable.ic_arrow_back_24),
                        contentDescription = "Go back"
                    )
                }
                false -> { /* No navigation icon */ }
            }

        }
    )
}
