package mn.dev.epa.ui

import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import mn.dev.epa.data.AppContainer
import mn.dev.epa.ui.theme.EPATheme

@Composable
fun ePAApp(
    appContainer: AppContainer,
    widthSizeClass: WindowWidthSizeClass,
) {
    EPATheme {
        Text(text = "Hello")
    }
}

@Preview
@Composable
fun DefaultPreview() {
    EPATheme {
        Text(text = "Hello")
    }
}

