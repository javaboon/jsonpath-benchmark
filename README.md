# JsonPath implementations microbenchmark for the JVM

## Use case

Input is byte arrays.

If the parser can't guess encoding by itself, we try to decode in the most efficient possible way, but encoding time is accounted for.

Paths are precompiled as it's the use case for Gatling (they are cached)

## tl;dr

* **Gatling implementation is almost 3 TIMES FASTER than Jayway**
* Jackson currently perfoms better than Boon (~20% faster) on those samples
* If encoding is one included in the RFC (UTF8, UTF16... but not ISO), it can be efficient to let Jackson do the decoding

## How to

Build with `mvn clean package`

Run with `java -jar target/microbenchmarks.jar ".*" -wi 2 -i 10 -f 2 -t 8`

## Figures

Here are the results on my machine:

* OS X 10.9
* Hotspot 1.7.0_45
* Intel Core i7 2,7 GHz

Benchmark                                                      Mode Thr     Count  Sec         Mean   Mean error    Units
GatlingJacksonBenchmark.parseBytesPrecompiledRoundRobin       thrpt   8        10    1   164196,598     7202,818    ops/s
GatlingBoonBenchmark.parseCharsPrecompiledRoundRobin          thrpt   8        10    1   137262,157    15393,496    ops/s
GatlingJsonSmartBenchmark.parseStringPrecompiledRoundRobin    thrpt   8        10    1   136520,517    12308,920    ops/s
GatlingJacksonBenchmark.parseStringPrecompiledRoundRobin      thrpt   8        10    1   130905,740    11600,990    ops/s
GatlingBoonBenchmark.parseBytesPrecompiledRoundRobin          thrpt   8        10    1   104094,400    15263,525    ops/s
JaywayJacksonBenchmark.parseBytesPrecompiledRoundRobin        thrpt   8        10    1    64422,260    48903,909    ops/s
JaywayJacksonBenchmark.parseStringPrecompiledRoundRobin       thrpt   8        10    1    45082,837    38283,830    ops/s
