package com.borabor.planetapp.data

import com.borabor.planetapp.R
import com.borabor.planetapp.model.Author

object AuthorData {
    val authorList = listOf(
        Author(
            imageDrawable = R.drawable.bora_photo,
            name = "BORA BOR",
            location = "Istanbul, Turkey",
            title = "Electrical & Electronics Engineer",
            description = "Born in Istanbul in 1998. Interested in Android development.",
            githubLink = "https://github.com/bbor98",
            linkedinLink = "https://www.linkedin.com/in/borabor/"
        ),
        Author(
            imageDrawable = R.drawable.burak_photo,
            name = "BURAK ERTAŞ",
            location = "Istanbul, Turkey",
            title = "Electrical & Electronics Engineer",
            description = "Born in Kiel in 1996. Interested in Flutter, iOS, Android development.",
            githubLink = "https://github.com/ErtasBurak",
            linkedinLink = "https://www.linkedin.com/in/burakertas25/"
        )
    )
}