package com.ivanovdev.feature.common.util

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class WeightTransformation(private val suffix: String) : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {

        val result = text + AnnotatedString(suffix)

        return TransformedText(result, OffsetMapping.Identity)
    }
}