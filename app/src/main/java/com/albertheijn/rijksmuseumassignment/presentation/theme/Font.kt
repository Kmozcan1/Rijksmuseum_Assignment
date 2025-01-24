package com.albertheijn.rijksmuseumassignment.presentation.theme

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import com.albertheijn.rijksmuseumassignment.R

object Font {
    val arial = FontFamily(
        Font(R.font.arial_bold, FontWeight.Bold),
        Font(R.font.arial_bold_italic, FontWeight.Bold, FontStyle.Italic),
        Font(R.font.arial_italic, FontWeight.Normal, FontStyle.Italic),
        Font(R.font.arial_regular, FontWeight.Normal)
    )
}