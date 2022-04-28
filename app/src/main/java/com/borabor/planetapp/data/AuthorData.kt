package com.borabor.planetapp.data

import com.borabor.planetapp.R
import com.borabor.planetapp.model.Author

object AuthorData {
    val authorList = listOf(
        Author(
            imageDrawable = R.drawable.bora_photo,
            name = "BORA BOR",
            title = "Electrical & Electronics Engineer",
            description = "Born in 1998. Interested in Android development",
            gitHubLink = "https://github.com/bbor98"
        ),
        Author(
            imageDrawable = R.drawable.burak_photo,
            name = "BURAK ERTAŞ",
            title = "Electrical & Electronics Engineer",
            description = "Born in 1996. Interested in Flutter, iOS, Android development",
            gitHubLink = "https://github.com/ErtasBurak"
        )
    )
}