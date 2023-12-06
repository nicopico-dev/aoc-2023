package days

private typealias Id = Long
private fun String.toId(): Id = toLong()

class Day5: Day(5) {
    override fun partOne(): Any {
        val almanac = loadAlmanac()
        return almanac.seeds
            .map { almanac.getSeedLocation(it) }
            .min()
    }
    
    override fun partTwo(): Any {
        val almanac = loadAlmanac()
        val seeds = almanac.seeds
            .windowed(2, 2)
            .flatMap {
                buildList(it[0], it[1])
            }

        return seeds
            .map { almanac.getSeedLocation(it) }
            .min()
    }

    fun loadAlmanac(): Almanac {
        return parseAlmanac(inputList)
    }
    
    data class Almanac(
            val seeds: List<Id>,
            val seedToSoil: AlmanacSection,
            val soilToFertilizer: AlmanacSection,
            val fertilizerToWater: AlmanacSection,
            val waterToLight: AlmanacSection,
            val lightToTemperature: AlmanacSection,
            val temperatureToHumidity: AlmanacSection,
            val humidityToLocation: AlmanacSection,
    ) {
        fun getSeedLocation(seed: Id): Id {
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
        val destinationStart: Id,
        val sourceStart: Id,
        val rangeLength: Id,
    ) {
        private val sourceEnd: Id = sourceStart + rangeLength -1

        private fun isMapping(source: Id): Boolean {
            return source in sourceStart..sourceEnd
        }

        fun getMatchingId(source: Id): Id? {
            if (!isMapping(source)) return null

            val delta = source - sourceStart
            return destinationStart + delta
        }
    }
    
    data class AlmanacSection(val data: List<Mapping>) {
        fun getMatchingIdFor(source: Id): Id {
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
                .map(String::toId)
            
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
                                destinationStart = destinationStart.toId(),
                                sourceStart = sourceStart.toId(),
                                rangeLength = rangeLength.toId(),
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

        fun buildList(start: Id, length: Id): List<Id> {
            return LongRange(start, start + length - 1).toList()
        }
    }
}