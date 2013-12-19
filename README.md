# JsonPath implementations microbenchmark for the JVM

## Use case

Input is byte arrays.

If the parser can't guess encoding by itself, we try to decode in the most efficient possible way, but encoding time is accounted for.

Paths are precompiled as it's the use case for Gatling (they are cached)

## tl;dr

* **Gatling with Boon is up 3 TIMES FASTER than Jayway**
* Boon currently performs better than Jackson (~20% faster) on those samples

## How to

Build with `mvn clean package`

Run with `java -jar target/microbenchmarks.jar ".*" -wi 2 -i 10 -f 2 -t 8`

## Figures


Here are the results on my machine:

* OS X 10.9
* Hotspot 1.7.0_45
* Intel Core i7 2,7 GHz

```
Benchmark                                                                 Mode Thr     Count  Sec         Mean   Mean error    Units
i.g.b.j.GatlingBoonSuperFastBenchmark.parseCharsPrecompiledRoundRobin    thrpt   8        10    1   169711.303     7459.674    ops/s
i.g.b.j.GatlingBoonSuperFastBenchmark.parseBytesPrecompiledRoundRobin    thrpt   8        10    1   168660.442     4926.140    ops/s

i.g.b.j.GatlingBoonFastBenchmark.parseBytesPrecompiledRoundRobin         thrpt   8        10    1   140900.438     1319.706    ops/s
i.g.b.j.GatlingBoonFastBenchmark.parseCharsPrecompiledRoundRobin         thrpt   8        10    1   130726.245    22760.074    ops/s

i.g.b.j.GatlingJacksonBenchmark.parseBytesPrecompiledRoundRobin          thrpt   8        10    1   104000.898     4232.683    ops/s
i.g.b.j.GatlingJacksonBenchmark.parseStringPrecompiledRoundRobin         thrpt   8        10    1    82846.282     3028.245    ops/s

i.g.b.j.GatlingJsonSmartBenchmark.parseStringPrecompiledRoundRobin       thrpt   8        10    1    77459.185     3531.564    ops/s

i.g.b.j.JaywayJacksonBenchmark.parseBytesPrecompiledRoundRobin           thrpt   8        10    1    65411.597    20290.173    ops/s
i.g.b.j.JaywayJacksonBenchmark.parseStringPrecompiledRoundRobin          thrpt   8        10    1    55474.618    17516.355    ops/s
```