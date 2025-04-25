package kuke.board.hotarticle;

import kuke.board.hotarticle.repository.HotArticleListRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class HotArticleListRepositoryTest {
    @Autowired
    HotArticleListRepository hotArticleListRepository;

    @Test
    void addTest() throws InterruptedException {
        // given
        LocalDateTime time = LocalDateTime.of(2024, 7, 23, 0, 0);
        long limit = 3;

        // when
        hotArticleListRepository.add(1L, time, 2L, limit, Duration.ofSeconds(3));
        hotArticleListRepository.add(2L, time, 3L, limit, Duration.ofSeconds(3));
        hotArticleListRepository.add(3L, time, 1L, limit, Duration.ofSeconds(3));
        hotArticleListRepository.add(4L, time, 5L, limit, Duration.ofSeconds(3));
        hotArticleListRepository.add(5L, time, 4L, limit, Duration.ofSeconds(3));

        // then
        List<Long> articleIds = hotArticleListRepository.readAll("20240723");

        assertEquals(articleIds.size(), limit);
        assertEquals(articleIds.get(0), 4L);
        assertEquals(articleIds.get(1), 5L);
        assertEquals(articleIds.get(2), 2L);

        TimeUnit.SECONDS.sleep(5);

        assertEquals(hotArticleListRepository.readAll("20240723").size(), 0);
    }

}