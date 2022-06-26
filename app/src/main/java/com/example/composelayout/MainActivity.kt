package com.example.composelayout

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
// TODO 여기 캡쳐하면 좋을듯...!!
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintLayout
import coil.compose.rememberImagePainter
import com.example.composelayout.ui.theme.ComposeLayoutTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeLayoutTheme {
                SampleMain()
            }
        }
    }
}

@Composable
fun SampleMain() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "ComposeLayout")
                },
                actions = {
                    IconButton(onClick = {

                    }) {
                        Icon(Icons.Filled.Favorite, contentDescription = null)
                    }
                }
            )
        }) {
        //TODO Constraint를 활용한 뷰간의 간격 테스트
        //TODO Header
        Column{
            ConstraintLayoutContent()
            //TODO Bddy
            BodyList()
        }
    }
}

@Preview
@Composable
fun SampleMainPreview() {
    ComposeLayoutTheme {
        SampleMain()
    }
}


@Composable
fun ConstraintLayoutContent() {
    ConstraintLayout {
        val (button, text, image) = createRefs()
        Text(
            text = "Compose Layout",
            fontSize = 30.sp,
            modifier = Modifier.constrainAs(text) {
                top.linkTo(parent.top, margin = 32.dp)
            }
        )
        Button(
            onClick = {

            },
            modifier = Modifier.constrainAs(button) {
                top.linkTo(text.bottom, margin = 48.dp)
            }
        ) {
            Text("BUTTON")
        }
        Box(
            modifier = Modifier
                .width(100.dp)
                .height(100.dp)
                .background(color = androidx.compose.ui.graphics.Color.Black)
                .constrainAs(image) {
                    top.linkTo(text.bottom, margin = 14.dp)
                }
        )
    }
}

@Preview
@Composable
fun ContentPreview() {
    ComposeLayoutTheme {
        ConstraintLayoutContent()
    }
}

@Composable
fun BodyList() {
    val scrollState = rememberLazyListState()
    LazyColumn(state = scrollState) {
        items(100) {
            ListItem(it)
        }
    }
}


@Composable
fun ListItem(index: Int) {
    //TODO Modifier 적용
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 14.dp)
            .clickable {
                Log.d("PJS", "TEST")
            }
    ) {
        Image(
            painter = rememberImagePainter(
                data = "https://developer.android.com/images/brand/Android_Robot.png"
            ),
            contentDescription = "Android Logo",
            modifier = Modifier.size(50.dp)
        )
        Spacer(Modifier.width(10.dp))
        Text("Item #$index", style = MaterialTheme.typography.subtitle1)
    }
}