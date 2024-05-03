package com.example.currentlocation

class MyLocation(
    var latitude: Double,
    var longitude: Double
) {

    fun distance(l2: MyLocation): Double {
        val phi1 = this.latitude * Math.PI / 180
        val phi2 = l2.latitude * Math.PI / 180
        val deltaG = (this.longitude - l2.longitude) * Math.PI / 180
        val d = Math.acos(
            Math.sin(phi1) * Math.sin(phi2) + Math.cos(phi1) * Math.cos(phi2) * Math.cos(deltaG)
        ) * EARTH_RADIUS
        return d
    }

    companion object {
        private const val EARTH_RADIUS = 6371e3
    }

}