package rg.info.storagelocator.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import rg.info.storagelocator.ui.theme.StorageLocatorTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputComponent(
    value: String,
    onValueChange: (String) -> Unit,
    text: String,
    enabled: Boolean = true,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            label = { Text(text) },
            singleLine = true,
            enabled = enabled,
        )
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun InputComponentPreview() {
    StorageLocatorTheme {
        InputComponent(
            value = "value",
            onValueChange = {},
            text = "text",
        )
    }
}

@Preview(showBackground = true)
@Composable
fun InputComponentPreviewDark() {
    StorageLocatorTheme(darkTheme = true) {
        InputComponent(
            value = "value",
            onValueChange = {},
            text = "text",
        )
    }
}