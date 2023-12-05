package days

class Day5: Day(5) {
    override fun partOne(): Any {
        val almanac = loadAlmanac()
        return almanac.seeds
            .map { almanac.getSeedLocation(it) }
            .min()
    }
    
    fun loadAlmanac(): Almanac {
        return parseAlmanac(inputList)
    }
    
    data class Almanac(
            val seeds: List<Int>,
            val seedToSoil: AlmanacSection,
            val soilToFertilizer: AlmanacSection,
            val fertilizerToWater: AlmanacSection,
            val waterToLight: AlmanacSection,
            val lightToTemperature: AlmanacSection,
            val temperatureToHumidity: AlmanacSection,
            val humidityToLocation: AlmanacSection,
    ) {
        fun getSeedLocation(seed: Int): Int {
            return seedToSoil.getMatchingIdFor(seed)
                .let { soilToFertilizer.getMatchingIdFor(it) }
                .let { fertilizerToWater.getMatchingIdFor(it) }
                .let { waterToLight.getMatchingIdFor(it) }
                .let { lightToTemperature.getMatchingIdFor(it) }
                .let { temperatureToHumidity.getMatchingIdFor(it) }
                .let { humidityToLocation.getMatchingIdFor(it) }
        }
    }

    data class Mapping(
        val destinationStart: Int,
        val sourceStart: Int,
        val rangeLength: Int,
    ) {
        private val sourceEnd: Int = sourceStart + rangeLength -1

        private fun isMapping(source: Int): Boolean {
            return source in sourceStart..sourceEnd
        }

        fun getMatchingId(source: Int): Int? {
            if (!isMapping(source)) return null

            val delta = source - sourceStart
            return destinationStart + delta
        }
    }
    
    data class AlmanacSection(val data: List<Mapping>) {
        fun getMatchingIdFor(source: Int): Int {
            return data
                .firstNotNullOfOrNull {
                    it.getMatchingId(source)
                } ?: source
        }
    }
    
    companion object {
        private val mappingRegex = Regex("(\\d+) (\\d+) (\\d+)")
        
        private fun parseAlmanac(data: List<String>): Almanac {
            // First line, ignoring "seeds: "
            val seeds = data[0]
                .substring(7)
                .split(" ")
                .map(String::toInt)
            
            val sectionMap: MutableMap<String, MutableList<Mapping>> = mutableMapOf()
            lateinit var sectionKey: String
            
            for (line in data) {
                when {
                    line.startsWith("seeds: ") -> continue
                    line.isEmpty() -> sectionKey = ""
                    line.endsWith(" map:") -> sectionKey = line.replace(" map:", "")
                    else -> {
                        val (destinationStart, sourceStart, rangeLength) =
                            mappingRegex.matchEntire(line)!!.destructured
                        sectionMap.getOrPut(sectionKey, ::mutableListOf).add(
                            Mapping(
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
                seedToSoil = AlmanacSection(sectionMap["seed-to-soil"] ?: emptyList()),
                soilToFertilizer = AlmanacSection(sectionMap["soil-to-fertilizer"] ?: emptyList()),
                fertilizerToWater = AlmanacSection(sectionMap["fertilizer-to-water"] ?: emptyList()),
                waterToLight = AlmanacSection(sectionMap["water-to-light"] ?: emptyList()),
                lightToTemperature = AlmanacSection(sectionMap["light-to-temperature"] ?: emptyList()),
                temperatureToHumidity = AlmanacSection(sectionMap["temperature-to-humidity"] ?: emptyList()),
                humidityToLocation = AlmanacSection(sectionMap["humidity-to-location"] ?: emptyList()),
            )
        }
    }
}