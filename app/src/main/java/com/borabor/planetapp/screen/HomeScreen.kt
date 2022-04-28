package com.borabor.planetapp.screen

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.borabor.planetapp.R
import com.borabor.planetapp.data.PlanetData
import com.borabor.planetapp.model.Planet
import com.borabor.planetapp.ui.theme.PlanetAppTheme
import com.borabor.planetapp.util.OrderBy
import com.borabor.planetapp.util.Screen
import com.borabor.planetapp.util.SortBy
import com.borabor.planetapp.util.thousandSeparators
import kotlinx.coroutines.flow.MutableStateFlow

private val currentSortType = MutableStateFlow(SortBy.NAME)
private val currentOrderType = MutableStateFlow(OrderBy.ASC)

private val isAreaVisible = MutableStateFlow(true)
private val isTempVisible = MutableStateFlow(true)
private val isDistVisible = MutableStateFlow(true)
private val isPeriodVisible = MutableStateFlow(true)
private val isMassVisible = MutableStateFlow(true)
private val isAgeVisible = MutableStateFlow(true)
private val isDescrVisible = MutableStateFlow(true)

@Composable
fun HomeScreen(navController: NavController) {
    var openVisibilityDialog by remember { mutableStateOf(false) }
    var openSortDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.app_name)
                    )
                },
                actions = {
                    IconButton(onClick = { navController.navigate(Screen.Author.route) }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_outline_info_24),
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                    IconButton(onClick = { openVisibilityDialog = true }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_baseline_visibility_24),
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                    IconButton(onClick = { openSortDialog = true }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_baseline_sort_24),
                            contentDescription = null,
                            tint = Color.White
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
        PlanetList()
    }

    if (openVisibilityDialog) VisibilityDialog(
        onDismiss = { openVisibilityDialog = false },
        onConfirm = { area, temp, dist, period, mass, age, desc ->
            isAreaVisible.value = area
            isTempVisible.value = temp
            isDistVisible.value = dist
            isPeriodVisible.value = period
            isMassVisible.value = mass
            isAgeVisible.value = age
            isDescrVisible.value = desc
            openVisibilityDialog = false
        }
    )

    if (openSortDialog) {
        SortDialog(onDismiss = { openSortDialog = false }) { newSortType, newOrderType ->
            currentSortType.value = newSortType
            currentOrderType.value = newOrderType
            openSortDialog = false
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PlanetList() {
    val list = when (currentOrderType.collectAsState().value) {
        OrderBy.ASC -> PlanetData.planetList.sortedWith(compareBy {
            when (currentSortType.collectAsState().value) {
                SortBy.NAME -> it.name
                SortBy.SURF_AREA -> it.surfaceArea
                SortBy.SURF_TEMP -> it.surfaceTemperature[0]
                SortBy.ORBIT_DIST -> it.orbitDistance
                SortBy.ORBIT_PER -> it.orbitPeriod
                SortBy.MASS -> it.mass
                SortBy.AGE -> it.age
            }
        })
        OrderBy.DESC -> PlanetData.planetList.sortedWith(compareByDescending {
            when (currentSortType.collectAsState().value) {
                SortBy.NAME -> it.name
                SortBy.SURF_AREA -> it.surfaceArea
                SortBy.SURF_TEMP -> it.surfaceTemperature[0]
                SortBy.ORBIT_DIST -> it.orbitDistance
                SortBy.ORBIT_PER -> it.orbitPeriod
                SortBy.MASS -> it.mass
                SortBy.AGE -> it.age
            }
        })
    }

    LazyColumn(contentPadding = PaddingValues(16.dp)) {
        items(items = list, key = { it.name }) { planet ->
            PlanetItem(
                planet = planet, modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .animateItemPlacement()
                    .animateContentSize(
                        animationSpec = tween(
                            durationMillis = 400,
                            easing = LinearOutSlowInEasing
                        )
                    )
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PlanetItem(planet: Planet, modifier: Modifier) {
    var isExpanded by remember { mutableStateOf(false) }

    Card(
        onClick = { isExpanded = !isExpanded },
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
        backgroundColor = Color.Black,
        border = BorderStroke(1.dp, Color.White),
        elevation = 8.dp
    ) {
        Image(
            painter = painterResource(id = planet.imageDrawable),
            contentDescription = null,
            modifier =
            if (isExpanded) Modifier.fillMaxHeight()
            else Modifier.height(64.dp),
            alignment = Alignment.TopCenter,
            contentScale = ContentScale.Crop,
            alpha = 0.5f
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = planet.name,
                    modifier = Modifier.weight(6f),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                val rotationState by animateFloatAsState(targetValue = if (isExpanded) 180f else 0f)
                IconButton(
                    onClick = { isExpanded = !isExpanded },
                    modifier = Modifier
                        .alpha(ContentAlpha.medium)
                        .weight(1f)
                        .rotate(rotationState)
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = null
                    )
                }
            }
            if (isExpanded) {
                Spacer(modifier = Modifier.height(4.dp))
                Column {
                    if (isAreaVisible.collectAsState().value) {
                        Row {
                            Text(
                                text = stringResource(id = R.string.surface_area),
                                fontSize = MaterialTheme.typography.body2.fontSize,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = stringResource(id = R.string.km_square, planet.surfaceArea.thousandSeparators()),
                                fontSize = MaterialTheme.typography.body2.fontSize,
                                fontWeight = FontWeight.Light
                            )
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                    }
                    if (isTempVisible.collectAsState().value) {
                        Row {
                            Text(
                                text = stringResource(id = R.string.surface_temperature),
                                fontSize = MaterialTheme.typography.body2.fontSize,
                                fontWeight = FontWeight.Bold
                            )
                            val surfaceTemperatureText =
                                if (planet.surfaceTemperature.size > 1) planet.surfaceTemperature.joinToString(separator = stringResource(id = R.string.to))
                                else planet.surfaceTemperature[0].toString()
                            Text(
                                text = stringResource(id = R.string.centigrade_degree, surfaceTemperatureText),
                                fontSize = MaterialTheme.typography.body2.fontSize,
                                fontWeight = FontWeight.Light
                            )
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                    }
                    if (isDistVisible.collectAsState().value) {
                        Row {
                            Text(
                                text = stringResource(id = R.string.orbit_distance),
                                fontSize = MaterialTheme.typography.body2.fontSize,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = stringResource(id = R.string.km, planet.orbitDistance.thousandSeparators()),
                                fontSize = MaterialTheme.typography.body2.fontSize,
                                fontWeight = FontWeight.Light
                            )
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                    }
                    if (isPeriodVisible.collectAsState().value) {
                        Row {
                            Text(
                                text = stringResource(id = R.string.orbit_period),
                                fontSize = MaterialTheme.typography.body2.fontSize,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = stringResource(id = R.string.days, planet.orbitPeriod.thousandSeparators()),
                                fontSize = MaterialTheme.typography.body2.fontSize,
                                fontWeight = FontWeight.Light
                            )
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                    }
                    if (isMassVisible.collectAsState().value) {
                        Row {
                            Text(
                                text = stringResource(id = R.string.mass),
                                fontSize = MaterialTheme.typography.body2.fontSize,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = stringResource(id = R.string.kg, planet.mass.thousandSeparators()),
                                fontSize = MaterialTheme.typography.body2.fontSize,
                                fontWeight = FontWeight.Light
                            )
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                    }
                    if (isAgeVisible.collectAsState().value) {
                        Row {
                            Text(
                                text = stringResource(id = R.string.age),
                                fontSize = MaterialTheme.typography.body2.fontSize,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = stringResource(id = R.string.years_old, planet.age.thousandSeparators()),
                                fontSize = MaterialTheme.typography.body2.fontSize,
                                fontWeight = FontWeight.Light
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                    if (isDescrVisible.collectAsState().value)
                        Text(
                            text = planet.description,
                            fontWeight = FontWeight.Light,
                            style = MaterialTheme.typography.body2
                        )
                }
            }
        }
    }
}

@Composable
fun VisibilityDialog(
    onDismiss: () -> Unit,
    onConfirm: (Boolean, Boolean, Boolean, Boolean, Boolean, Boolean, Boolean) -> Unit
) {
    var area by remember { mutableStateOf(isAreaVisible.value) }
    var temp by remember { mutableStateOf(isTempVisible.value) }
    var dist by remember { mutableStateOf(isDistVisible.value) }
    var period by remember { mutableStateOf(isPeriodVisible.value) }
    var mass by remember { mutableStateOf(isMassVisible.value) }
    var age by remember { mutableStateOf(isAgeVisible.value) }
    var descr by remember { mutableStateOf(isDescrVisible.value) }

    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = { Text(text = stringResource(id = R.string.visibility)) },
        text = {
            Column {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .clickable { area = !area }
                ) {
                    Checkbox(checked = area, onCheckedChange = { area = !area })
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = stringResource(id = R.string.surface_area),
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                }
                Row(
                    Modifier
                        .fillMaxWidth()
                        .clickable { temp = !temp }
                ) {
                    Checkbox(checked = temp, onCheckedChange = { temp = !temp })
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = stringResource(id = R.string.surface_temperature),
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                }
                Row(
                    Modifier
                        .fillMaxWidth()
                        .clickable { dist = !dist }
                ) {
                    Checkbox(checked = dist, onCheckedChange = { dist = !dist })
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = stringResource(id = R.string.orbit_distance),
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                }
                Row(
                    Modifier
                        .fillMaxWidth()
                        .clickable { period = !period }
                ) {
                    Checkbox(checked = period, onCheckedChange = { period = !period })
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = stringResource(id = R.string.orbit_period),
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                }
                Row(
                    Modifier
                        .fillMaxWidth()
                        .clickable { mass = !mass }
                ) {
                    Checkbox(checked = mass, onCheckedChange = { mass = !mass })
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = stringResource(id = R.string.mass),
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                }
                Row(
                    Modifier
                        .fillMaxWidth()
                        .clickable { age = !age }
                ) {
                    Checkbox(checked = age, onCheckedChange = { age = !age })
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = stringResource(id = R.string.age),
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                }
                Row(
                    Modifier
                        .fillMaxWidth()
                        .clickable { descr = !descr }
                ) {
                    Checkbox(checked = descr, onCheckedChange = { descr = !descr })
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = stringResource(id = R.string.description),
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                }
            }
        },
        confirmButton = {
            TextButton(onClick = { onConfirm(area, temp, dist, period, mass, age, descr) }) {
                Text(stringResource(id = R.string.confirm))
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismiss() }) {
                Text(stringResource(id = R.string.cancel))
            }
        })
}

@Composable
fun SortDialog(onDismiss: () -> Unit, onConfirm: (SortBy, OrderBy) -> Unit) {
    var sortBy by remember { mutableStateOf(currentSortType.value) }
    var orderBy by remember { mutableStateOf(currentOrderType.value) }

    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = { Text(stringResource(id = R.string.sort_by)) },
        text = {
            Column {
                SortRadioOptions(onOptionSelected = { sortBy = it })
                Divider(Modifier.fillMaxWidth())
                OrderRadioOptions(onOptionSelected = { orderBy = it })
            }
        },
        confirmButton = {
            TextButton(onClick = { onConfirm(sortBy, orderBy) }) {
                Text(stringResource(id = R.string.confirm))
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismiss() }) {
                Text(stringResource(id = R.string.cancel))
            }
        }
    )
}

@Composable
fun SortRadioOptions(onOptionSelected: (SortBy) -> Unit) {
    val sortOptions = listOf(
        stringResource(id = R.string.name),
        stringResource(id = R.string.surface_area),
        stringResource(id = R.string.surface_temperature),
        stringResource(id = R.string.orbit_distance),
        stringResource(id = R.string.orbit_period),
        stringResource(id = R.string.mass),
        stringResource(id = R.string.age),
    )

    var isSelected by remember {
        mutableStateOf(false)
    }

    var selectedOption by remember {
        mutableStateOf(SortBy.NAME)
    }

    Column {
        sortOptions.mapIndexed { index, s ->
            Row(
                Modifier
                    .fillMaxWidth()
                    .clickable {
                        isSelected = true
                        selectedOption = enumValues<SortBy>()[index]
                        onOptionSelected(enumValues<SortBy>()[index])
                    }
            ) {
                RadioButton(
                    selected = (
                            if (isSelected) selectedOption == enumValues<SortBy>()[index]
                            else enumValues<SortBy>()[index] == currentSortType.collectAsState().value
                            ),
                    onClick = {
                        isSelected = true
                        selectedOption = enumValues<SortBy>()[index]
                        onOptionSelected(enumValues<SortBy>()[index])
                    }
                )
                Text(
                    text = s,
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .align(Alignment.CenterVertically)
                )
            }
        }
    }
}

@Composable
fun OrderRadioOptions(onOptionSelected: (OrderBy) -> Unit) {
    val orderOptions = listOf(
        stringResource(id = R.string.ascending),
        stringResource(id = R.string.descending)
    )

    var isSelected by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf(OrderBy.ASC) }

    Row(Modifier.fillMaxWidth()) {
        orderOptions.mapIndexed { index, s ->
            Row(Modifier.clickable {
                isSelected = true
                selectedOption = enumValues<OrderBy>()[index]
                onOptionSelected(enumValues<OrderBy>()[index])
            }
            ) {
                RadioButton(
                    selected = (
                            if (isSelected) selectedOption == enumValues<OrderBy>()[index]
                            else enumValues<OrderBy>()[index] == currentOrderType.collectAsState().value
                            ),
                    onClick = {
                        isSelected = true
                        selectedOption = enumValues<OrderBy>()[index]
                        onOptionSelected(enumValues<OrderBy>()[index])
                    }
                )
                Text(
                    text = s,
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .align(Alignment.CenterVertically)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    PlanetAppTheme {
        HomeScreen(navController = rememberNavController())
    }
}