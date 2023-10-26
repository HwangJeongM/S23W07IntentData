package kr.ac.kumoh.ce.s20191283.s23w07intentdata

import android.app.Activity
import android.content.Intent
import android.media.Image
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import kr.ac.kumoh.ce.s20191283.s23w07intentdata.ui.theme.S23W07IntentDataTheme

class AnotherActivity : ComponentActivity() {
    companion object {
        const val IMAGE_NAME = "image name"
        const val IMAGE_RESULT = "image result"

        const val LIKE = 100
        const val DISLIKE = 101
        const val NONE = 0
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            S23W07IntentDataTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MechanicImage()
                }
            }
        }
    }
}

fun onResultClick(activity: Activity, imageName: String, imageResult: Int) {
    val intent = Intent()
    intent.putExtra(ImageActivity.IMAGE_NAME, imageName)
    intent.putExtra(ImageActivity.IMAGE_RESULT, imageResult)
    activity.setResult(AppCompatActivity.RESULT_OK, intent)
    activity.finish()
}

@Composable
fun MechanicImage(modifier: Modifier = Modifier) {
    val activity = LocalContext.current as Activity
    val intent = activity.intent
    val imageName = intent.getStringExtra(MainActivity.KEY_NAME) ?: "none"
    val res = when (imageName) {
        MainActivity.MOUNTAIN -> R.drawable.mountain
        MainActivity.SEA -> R.drawable.sea
        else -> R.drawable.ic_launcher_foreground
    }
    Image(
        modifier = Modifier.fillMaxSize(),
        painter = painterResource(id = res),
        contentScale = ContentScale.Crop,
        contentDescription = "Nature Image"
    )
    Row(
        modifier = modifier.run {
            fillMaxWidth()
                .wropContentHeight(Alignment.Bottom)
        },
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Button(onClick = {
            onResultClick(activity, imageName, AnotherActivity.LIKE)
        }) {
            Text("좋아요")
        }
        Button(onClick = {}) {
            onResultClick(activity, imageName, AnotherActivity.DISLIKE)
            Text("싫어요")
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    S23W07IntentDataTheme {
        Greeting("Android")
    }
}