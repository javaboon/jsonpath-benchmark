# JsonPath implementations microbenchmark for the JVM

## Use case

Input is byte arrays.

If the parser can't guess encoding by itself, we try to decode in the most efficient possible way, but encoding time is accounted for.

Paths are precompiled as it's the use case for Gatling (they are cached)

## tl;dr

* **Gatling with Boon is up 3 TIMES FASTER than Jayway**
* Boon currently performs better than Jackson (~30% to ~200% faster) on these samples
* Using Boon is 30% faster parsing bytes and up to 2x faster parsing strings than Jackson.
* Gatling and Jackson are faster than Jayway by up to 2x.
* Gatling and Boon are up to 3x faster than Jayway.
* Rick likes coffee.

## How to

Build with `mvn clean package`

Run with `java -jar target/microbenchmarks.jar ".*" -wi 2 -i 10 -f 2 -t 8`

## Figures


Here are the results on my machine:

* OS X 10.9
* Hotspot 1.7.0_45
* Intel Core i7 2,7 GHz

```
Benchmark                                                         Mode Thr     Count  Sec         Mean   Mean error    Units
Gatling BoonSuperFastBenchmark.parseCharsPrecompiledRoundRobin    thrpt   8        10    1   169711.303     7459.674    ops/s
Gatling BoonSuperFastBenchmark.parseBytesPrecompiledRoundRobin    thrpt   8        10    1   168660.442     4926.140    ops/s

Gatling BoonFastBenchmark.parseBytesPrecompiledRoundRobin         thrpt   8        10    1   140900.438     1319.706    ops/s
Gatling BoonFastBenchmark.parseCharsPrecompiledRoundRobin         thrpt   8        10    1   130726.245    22760.074    ops/s

Gatling JacksonBenchmark.parseBytesPrecompiledRoundRobin          thrpt   8        10    1   104000.898     4232.683    ops/s
Gatling JacksonBenchmark.parseStringPrecompiledRoundRobin         thrpt   8        10    1    82846.282     3028.245    ops/s

Gatling JsonSmartBenchmark.parseStringPrecompiledRoundRobin       thrpt   8        10    1    77459.185     3531.564    ops/s

Jayway JacksonBenchmark.parseBytesPrecompiledRoundRobin           thrpt   8        10    1    65411.597    20290.173    ops/s
Jayway JacksonBenchmark.parseStringPrecompiledRoundRobin          thrpt   8        10    1    55474.618    17516.355    ops/s
```

References:
https://github.com/javaboon/boon
