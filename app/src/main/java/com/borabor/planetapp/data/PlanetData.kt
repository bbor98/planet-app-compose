package com.borabor.planetapp.data

import com.borabor.planetapp.R
import com.borabor.planetapp.model.Planet

object PlanetData {
    val planetList = listOf(
        Planet(
            imageDrawable = R.drawable.mercury,
            name = "Mercury",
            surfaceArea = 74800000,
            surfaceTemperature = listOf(-173, 427),
            orbitDistance = 57910000,
            orbitPeriod = 87.97,
            mass = 330104000000,
            age = 4503000000,
            description = "Mercury—the smallest planet in our solar system and closest to the Sun—is only slightly larger than Earth's Moon. Mercury is the fastest planet, zipping around the Sun every 88 Earth days."
        ),
        Planet(
            imageDrawable = R.drawable.venus,
            name = "Venus",
            surfaceArea = 460200000,
            surfaceTemperature = listOf(462),
            orbitDistance = 108200000,
            orbitPeriod = 224.70,
            mass = 4867320000000,
            age = 4503000000,
            description = "Venus spins slowly in the opposite direction from most planets. A thick atmosphere traps heat in a runaway greenhouse effect, making it the hottest planet in our solar system."
        ),
        Planet(
            imageDrawable = R.drawable.earth,
            name = "Earth",
            surfaceArea = 510100000,
            surfaceTemperature = listOf(-88, 58),
            orbitDistance = 149598262,
            orbitPeriod = 365.26,
            mass = 5972190000000,
            age = 4543000000,
            description = "Earth—our home planet—is the only place we know of so far that’s inhabited by living things. It's also the only planet in our solar system with liquid water on the surface."
        ),
        Planet(
            imageDrawable = R.drawable.mars,
            name = "Mars",
            surfaceArea = 144800000,
            surfaceTemperature = listOf(-87,-5),
            orbitDistance = 227943824,
            orbitPeriod = 686.98,
            mass = 641693000000,
            age = 4603000000,
            description = "Mars is a dusty, cold, desert world with a very thin atmosphere. There is strong evidence Mars was—billions of years ago—wetter and warmer, with a thicker atmosphere."
        ),
        Planet(
            imageDrawable = R.drawable.jupiter,
            name = "Jupiter",
            surfaceArea = 61420000000000,
            surfaceTemperature = listOf(-108),
            orbitDistance = 778340821,
            orbitPeriod = 4332.82,
            mass = 1898130000000000,
            age = 4603000000,
            description = "Jupiter is more than twice as massive than the other planets of our solar system combined. The giant planet's Great Red spot is a centuries-old storm bigger than Earth."
        ),
        Planet(
            imageDrawable = R.drawable.saturn,
            name = "Saturn",
            surfaceArea = 4270000000000,
            surfaceTemperature = listOf(-139),
            orbitDistance = 1426666422,
            orbitPeriod = 10755.70,
            mass = 568319000000000,
            age = 4503000000,
            description = "Adorned with a dazzling, complex system of icy rings, Saturn is unique in our solar system. The other giant planets have rings, but none are as spectacular as Saturn's."
        ),
        Planet(
            imageDrawable = R.drawable.uranus,
            name = "Uranus",
            surfaceArea = 8083000000000,
            surfaceTemperature = listOf(-197),
            orbitDistance = 2870658186,
            orbitPeriod = 30687.15,
            mass = 86810300000000,
            age = 4503000000,
            description = "Uranus—seventh planet from the Sun—rotates at a nearly 90-degree angle from the plane of its orbit. This unique tilt makes Uranus appear to spin on its side."
        ),
        Planet(
            imageDrawable = R.drawable.neptune,
            name = "Neptune",
            surfaceArea = 7618000000000,
            surfaceTemperature = listOf(-201),
            orbitDistance =4498396441,
            orbitPeriod = 60190.03,
            mass = 102410000000000,
            age = 4503000000,
            description = "Neptune—the eighth and most distant major planet orbiting our Sun—is dark, cold and whipped by supersonic winds. It was the first planet located through mathematical calculations, rather than by telescope."
        )
    )
}