package kuke.board.view.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import kuke.board.view.entity.ArticleViewCount;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ArticleViewCountBackUpRepositoryTest {
    @Autowired
    ArticleViewCountBackUpRepository articleViewCountBackUpRepository;
    @PersistenceContext
    EntityManager entityManager;

    @Test
    @Transactional
    void updateViewCountTest() {
        ArticleViewCount articleViewCount = articleViewCountBackUpRepository.save(
                ArticleViewCount.init(1L, 0L)
        );
        entityManager.flush();
        entityManager.clear();

        int result1 = articleViewCountBackUpRepository.updateViewCount(1L, 100L);
        int result2 = articleViewCountBackUpRepository.updateViewCount(1L, 300L);
        int result3 = articleViewCountBackUpRepository.updateViewCount(1L, 200L);

        assertEquals(result1, 1);
        assertEquals(result2, 1);
        assertEquals(result3, 0);

        ArticleViewCount articleViewCount1 = articleViewCountBackUpRepository.findById(1L).get();
        assertEquals(articleViewCount1.getViewCount(), 300L);
    }

}