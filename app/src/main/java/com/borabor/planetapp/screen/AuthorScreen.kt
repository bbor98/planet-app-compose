package com.borabor.planetapp.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.borabor.planetapp.R
import com.borabor.planetapp.data.AuthorData
import com.borabor.planetapp.model.Author
import com.borabor.planetapp.ui.theme.PlanetAppTheme

@Composable
fun AuthorScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.authors)) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = null
                        )
                    }
                },
                backgroundColor = Color.Black
            )
        }
    ) {
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            AuthorItem(item = AuthorData.authorList[0])
            Spacer(modifier = Modifier.height(16.dp))
            AuthorItem(item = AuthorData.authorList[1])
        }
    }
}

@Composable
fun AuthorItem(item: Author) {
    val github = "GitHub"
    val startIndexGithub = 0
    val endIndexGithub = github.length
    val tagGithub  = "GITHUB_URL"

    val annotatedLinkGithub: AnnotatedString = buildAnnotatedString {
        append(github)
        addStyle(
            style = SpanStyle(
                color = Color(0xff64B5F6),
                textDecoration = TextDecoration.Underline
            ),
            start = startIndexGithub,
            end = endIndexGithub
        )
        addStringAnnotation(
            tag = tagGithub,
            annotation = item.githubLink,
            start = startIndexGithub,
            end = endIndexGithub
        )
    }

    val linkedin = "LinkedIn"
    val startIndexLinkedin = 0
    val endIndexLinkedin = linkedin.length
    val tagLinkedin  = "LINKEDIN_URL"

    val annotatedLinkLinkedin: AnnotatedString = buildAnnotatedString {
        append(linkedin)
        addStyle(
            style = SpanStyle(
                color = Color(0xff64B5F6),
                textDecoration = TextDecoration.Underline
            ),
            start = startIndexLinkedin,
            end = endIndexLinkedin
        )
        addStringAnnotation(
            tag = tagLinkedin,
            annotation = item.linkedinLink,
            start = startIndexLinkedin,
            end = endIndexLinkedin
        )
    }

    val uriHandler = LocalUriHandler.current

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(8.dp),
        backgroundColor = Color.Black,
        border = BorderStroke(1.dp, Color.White),
        elevation = 8.dp
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = item.imageDrawable),
                contentDescription = null,
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
                    .border(1.dp, Color.White, CircleShape),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = item.name,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = item.title,
                    fontStyle = FontStyle.Italic,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = item.location,
                    color = Color.Gray,
                    fontSize = MaterialTheme.typography.body2.fontSize
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = item.description,
                    fontSize = MaterialTheme.typography.body2.fontSize,
                    textAlign = TextAlign.Justify
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row {
                    ClickableText(
                        text = annotatedLinkGithub,
                        onClick = {
                            annotatedLinkGithub
                                .getStringAnnotations(tagGithub, it, it)
                                .firstOrNull()?.let { stringAnnotation ->
                                    uriHandler.openUri(stringAnnotation.item)
                                }
                        })
                    Text(
                        text = stringResource(id = R.string.bullet_point),
                        modifier = Modifier.padding(
                            start = 8.dp,
                            end = 8.dp
                        )
                    )
                    ClickableText(
                        text = annotatedLinkLinkedin,
                        onClick = {
                            annotatedLinkLinkedin
                                .getStringAnnotations(tagLinkedin, it, it)
                                .firstOrNull()?.let { stringAnnotation ->
                                    uriHandler.openUri(stringAnnotation.item)
                                }
                        })
                }
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