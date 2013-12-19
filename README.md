# JsonPath implementations microbenchmark for the JVM

## Use case

Input is byte arrays.

If the parser can't guess encoding by itself, we try to decode in the most efficient possible way, but encoding time is accounted for.

Paths are precompiled as it's the use case for Gatling (they are cached)

## tl;dr

* **Gatling implementation is almost 3 TIMES FASTER than Jayway**
* Jackson currently perfoms slower than Boon (~20% slower) on these samples
* If encoding is one included in the RFC (UTF8, UTF16... but not ISO), it can be efficient to let Jackson do the decoding

## How to

Build with `mvn clean package`

Run with `java -jar target/microbenchmarks.jar ".*" -wi 2 -i 10 -f 2 -t 8`

## Figures

Here are the results on my machine:

* OS X 10.9
* Hotspot 1.7.0_45
* Intel Core i7 2,7 GHz

```
Benchmark                                                      Mode Thr     Count  Sec         Mean   Mean error    Units
GatlingJacksonBenchmark.parseBytesPrecompiledRoundRobin       thrpt   8        20    1    93072,818     4120,802    ops/s
GatlingBoonBenchmark.parseBytesPrecompiledRoundRobin          thrpt   8        20    1    92354,272     7493,019    ops/s
GatlingBoonBenchmark.parseCharsPrecompiledRoundRobin          thrpt   8        20    1    84029,673     4189,809    ops/s
GatlingJsonSmartBenchmark.parseStringPrecompiledRoundRobin    thrpt   8        20    1    74342,808     3171,457    ops/s
GatlingJacksonBenchmark.parseStringPrecompiledRoundRobin      thrpt   8        20    1    73463,343     4708,547    ops/s
JaywayJacksonBenchmark.parseBytesPrecompiledRoundRobin        thrpt   8        20    1    53042,612    16835,683    ops/s
JaywayJacksonBenchmark.parseStringPrecompiledRoundRobin       thrpt   8        20    1    43823,404    14019,257    ops/s
```
