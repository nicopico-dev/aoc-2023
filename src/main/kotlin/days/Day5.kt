package days

class Day5: Day(5) {
    override fun partOne(): Any {
        TODO("Not yet implemented")
    }
    
    fun loadAlmanac(): Almanac {
        return parseAlmanac(inputList)
    }
    
    data class Almanac(
        val seeds: List<Int>,
        val seedToSoil: IdMapSection,
        val soilToFertilizer: IdMapSection,
        val fertilizerToWater: IdMapSection,
        val waterToLight: IdMapSection,
        val lightToTemperature: IdMapSection,
        val temperatureToHumidity: IdMapSection,
        val humidityToLocation: IdMapSection,
    )
    
    data class IdMap(
        val destinationStart: Int,
        val sourceStart: Int,
        val rangeLength: Int,
    ) {
        val destinationEnd: Int = destinationStart + rangeLength
        val sourceEnd: Int = sourceStart + rangeLength
    }
    
    data class IdMapSection(
        val data: List<IdMap>
    )
    
    companion object {
        private val mapIdRegex = Regex("(\\d+) (\\d+) (\\d+)")
        
        private fun parseAlmanac(data: List<String>): Almanac {
            // First line, ignoring "seeds: "
            val seeds = data[0]
                .substring(7)
                .split(" ")
                .map(String::toInt)
            
            val idMaps: MutableMap<String, MutableList<IdMap>> = mutableMapOf()
            lateinit var idMapKey: String
            
            for (line in data) {
                when {
                    line.startsWith("seeds: ") -> continue
                    line.isEmpty() -> idMapKey = ""
                    line.endsWith(" map:") -> idMapKey = line.replace(" map:", "")
                    else -> {
                        val (destinationStart, sourceStart, rangeLength) =
                            mapIdRegex.matchEntire(line)!!.destructured
                        idMaps.getOrPut(idMapKey, ::mutableListOf).add(
                            IdMap(
                                destinationStart = destinationStart.toInt(),
                                sourceStart = sourceStart.toInt(),
                                rangeLength = rangeLength.toInt(),
                            )
                        )
                    }
                }
            }
            
            return Almanac(
                seeds = seeds,
                seedToSoil = IdMapSection(idMaps["seed-to-soil"] ?: emptyList()),
                soilToFertilizer = IdMapSection(idMaps["soil-to-fertilizer"] ?: emptyList()),
                fertilizerToWater = IdMapSection(idMaps["fertilizer-to-water"] ?: emptyList()),
                waterToLight = IdMapSection(idMaps["water-to-light"] ?: emptyList()),
                lightToTemperature = IdMapSection(idMaps["light-to-temperature"] ?: emptyList()),
                temperatureToHumidity = IdMapSection(idMaps["temperature-to-humidity"] ?: emptyList()),
                humidityToLocation = IdMapSection(idMaps["humidity-to-location"] ?: emptyList()),
            )
        }
    }
}