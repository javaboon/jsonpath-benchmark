# JsonPath implementations microbenchmark for the JVM

## Use case

Input is byte arrays.

If the parser can't guess encoding by itself, we try to decode in the most efficient possible way, but encoding time is accounted for.

Paths are precompiled as it's the use case for Gatling (they are cached)

## tl;dr

* Gatling roxx
* If encoding is one included in the RFC (UTF8, UTF16... but not ISO), it can be efficient to let Jackson do the decoding

## How to

Build with `mvn clean package`

Run with `java -jar target/microbenchmarks.jar ".*" -wi 2 -i 10 -f 2 -t 8`

## Figures

Here are the results on my machine:

* OS X 10.9
* Hotspot 1.7.0_45
* Intel Core i7 2,7 GHz

Benchmark                                                     Mode Thr     Count  Sec         Mean   Mean error    Units
GatlingJacksonBenchmark.parseBytesPrecompiledRoundRobin       thrpt   8        10    1   256418,290    29865,931    ops/s
GatlingJsonSmartBenchmark.parseStringPrecompiledRoundRobin    thrpt   8        10    1   198775,973    18876,667    ops/s
GatlingJacksonBenchmark.parseStringPrecompiledRoundRobin      thrpt   8        10    1   179512,335    18839,311    ops/s
JaywayJacksonBenchmark.parseBytesPrecompiledRoundRobin        thrpt   8        10    1    73440,395    62832,360    ops/s
JaywayJacksonBenchmark.parseStringPrecompiledRoundRobin       thrpt   8        10    1    56084,558    51682,445    ops/s
