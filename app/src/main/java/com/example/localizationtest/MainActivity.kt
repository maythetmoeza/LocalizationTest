package com.example.localizationtest

import android.app.LocaleManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.LocaleList
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.localizationtest.ui.theme.LocalizationTestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LocalizationTestTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("May Thet")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {

    val context = LocalContext.current
    Column{
        Text(text = stringResource(id = R.string.welcome))
        Text(text = stringResource(id = R.string.name)+" $name", modifier = modifier)
        Text(text = stringResource(id = R.string.language))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = {
                changeLocale(context, "en")
            }) {
                Text(text = stringResource(id = R.string.english))
            }
            Button(onClick = {
                changeLocale(context, "jv")
            }) {
                Text(text = stringResource(id = R.string.japanese))
            }
            Button(onClick = {
                changeLocale(context, "my")
            }) {
                Text(text = stringResource(id = R.string.myanmar))
            }
        }
    }
}

fun changeLocale(context: Context, localeString: String){
    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        context.getSystemService(LocaleManager::class.java)
            .applicationLocales = LocaleList.forLanguageTags(localeString)
    }
    else {
        context.resources.configuration.setLocale(LocaleList.forLanguageTags(localeString)[0])
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LocalizationTestTheme {
        Greeting("Android")
    }
}