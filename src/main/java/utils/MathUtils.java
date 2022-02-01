package utils;

import java.util.stream.LongStream;

public class MathUtils {

    public static long factorial(int n) {
        return LongStream.rangeClosed(1, n)
            .reduce(1, (long x, long y) -> x * y);
    }
}
