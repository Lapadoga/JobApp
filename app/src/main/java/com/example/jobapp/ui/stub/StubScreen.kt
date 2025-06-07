package com.example.jobapp.ui.stub

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.jobapp.R
import com.example.jobapp.ui.theme.Grey2
import com.example.jobapp.ui.theme.Title1

@Composable
fun StubScreen() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = stringResource(R.string.stub_screen),
            style = Title1,
            color = Grey2,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, locale = "ru")
private fun StubScreenPreview() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = stringResource(R.string.stub_screen),
            style = Title1,
            color = Grey2,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}