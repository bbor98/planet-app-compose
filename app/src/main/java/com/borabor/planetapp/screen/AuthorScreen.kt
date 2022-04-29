package com.borabor.planetapp.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.borabor.planetapp.R
import com.borabor.planetapp.data.AuthorData
import com.borabor.planetapp.model.Author
import com.borabor.planetapp.ui.theme.PlanetAppTheme

@Composable
fun AuthorScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        verticalArrangement = Arrangement.Center
    ) {
        AuthorItem(item = AuthorData.authorList[0])
        Spacer(modifier = Modifier.height(16.dp))
        AuthorItem(item = AuthorData.authorList[1])
    }
}

@Composable
fun AuthorItem(item: Author) {
    val str = "GitHub"
    val startIndex = str.indexOf("GitHub")
    val endIndex = startIndex + 6
    val uriHandler = LocalUriHandler.current

    val annotatedLinkString: AnnotatedString = buildAnnotatedString {
        append(str)
        addStyle(
            style = SpanStyle(
                color = Color(0xff64B5F6),
                textDecoration = TextDecoration.Underline
            ),
            start = startIndex,
            end = endIndex
        )
        addStringAnnotation(
            tag = "URL",
            annotation = item.gitHubLink,
            start = startIndex,
            end = endIndex
        )
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row(modifier = Modifier.padding(8.dp)) {
            Image(
                painter = painterResource(id = item.imageDrawable),
                contentDescription = null,
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = item.name,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = item.title)
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = item.description,
                    fontSize = MaterialTheme.typography.body2.fontSize
                )
                Spacer(modifier = Modifier.height(4.dp))
                ClickableText(
                    text = annotatedLinkString,
                    onClick = {
                        annotatedLinkString
                            .getStringAnnotations("URL", it, it)
                            .firstOrNull()?.let { stringAnnotation ->
                                uriHandler.openUri(stringAnnotation.item)
                            }
                    })
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AuthorScreenPreview() {
    PlanetAppTheme {
        AuthorScreen(navController = rememberNavController())
    }
}