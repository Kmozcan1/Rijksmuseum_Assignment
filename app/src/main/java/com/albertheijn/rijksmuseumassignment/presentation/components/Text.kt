package com.albertheijn.rijksmuseumassignment.presentation.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import com.albertheijn.rijksmuseumassignment.presentation.theme.Font

@Composable
fun TextHeader(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    textDecoration: TextDecoration? = null,
    textAlign: TextAlign? = null,
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    bold: Boolean = false,
    italic: Boolean = false,
    onTextLayout: (TextLayoutResult) -> Unit = { },
) {
    Text(
        text = text,
        modifier = modifier.testTag("TextHeader"),
        color = color,
        textDecoration = textDecoration,
        textAlign = textAlign,
        overflow = overflow,
        softWrap = softWrap,
        maxLines = maxLines,
        fontFamily = Font.arial,
        onTextLayout = onTextLayout,
        style = MaterialTheme.typography.headlineMedium.copy(
            fontWeight = when {
                bold -> FontWeight.Bold
                else -> FontWeight.Normal
            },
            fontStyle = when {
                italic -> FontStyle.Italic
                else -> FontStyle.Normal
            }
        )
    )
}

@Composable
fun TextPrimary(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    textDecoration: TextDecoration? = null,
    textAlign: TextAlign? = null,
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    bold: Boolean = false,
    italic: Boolean = false,
    onTextLayout: (TextLayoutResult) -> Unit = {},
) {
    Text(
        text = text,
        modifier = modifier.testTag("TextPrimary"),
        color = color,
        textDecoration = textDecoration,
        textAlign = textAlign,
        overflow = overflow,
        softWrap = softWrap,
        maxLines = maxLines,
        onTextLayout = onTextLayout,
        fontFamily = Font.arial,
        style = MaterialTheme.typography.bodyLarge.copy(
            fontWeight = when {
                bold -> FontWeight.Bold
                else -> FontWeight.Normal
            },
            fontStyle = when {
                italic -> FontStyle.Italic
                else -> FontStyle.Normal
            }
        )
    )
}

@Composable
fun TextSecondary(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    textDecoration: TextDecoration? = null,
    textAlign: TextAlign? = null,
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    bold: Boolean = false,
    italic: Boolean = false,
    onTextLayout: (TextLayoutResult) -> Unit = {},
) {
    Text(
        text = text,
        modifier = modifier.testTag("TextSecondary"),
        color = color,
        textDecoration = textDecoration,
        textAlign = textAlign,
        overflow = overflow,
        softWrap = softWrap,
        maxLines = maxLines,
        onTextLayout = onTextLayout,
        fontFamily = Font.arial,
        style = MaterialTheme.typography.bodyMedium.copy(
            fontWeight = when {
                bold -> FontWeight.Bold
                else -> FontWeight.Normal
            },
            fontStyle = when {
                italic -> FontStyle.Italic
                else -> FontStyle.Normal
            }
        )
    )
}
