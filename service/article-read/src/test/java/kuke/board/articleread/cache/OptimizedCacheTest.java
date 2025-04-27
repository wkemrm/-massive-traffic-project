package kuke.board.articleread.cache;

import lombok.*;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class OptimizedCacheTest {
    @Test
    void parseDataTest() {
        parseDateTest("data", 10);
        parseDateTest(3L, 10);
        parseDateTest(3, 10);
        parseDateTest(new OptimizedCacheTest.TestClass("hihi"), 10);
    }

    void parseDateTest(Object data, long ttlSeconds) {
        // given
        OptimizedCache optimizedCache = OptimizedCache.of(data, Duration.ofSeconds(ttlSeconds));
        System.out.println("optimizedCache = " + optimizedCache);

        // when
        Object resolvedData = optimizedCache.parseData(data.getClass());

        // then
        System.out.println("resolvedData = " + resolvedData);
        assertEquals(resolvedData, data);
    }

    @Test
    void isExpiredTest() {
        assertEquals(OptimizedCache.of("data", Duration.ofDays(-30)).isExpired(), true);
        assertEquals(OptimizedCache.of("data", Duration.ofDays(30)).isExpired(), false);
    }

    @Getter
    @ToString
    @EqualsAndHashCode
    @NoArgsConstructor
    @AllArgsConstructor
    static class TestClass {
        String testData;
    }
}