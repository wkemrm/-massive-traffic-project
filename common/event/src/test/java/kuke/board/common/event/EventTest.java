package kuke.board.common.event;

import kuke.board.common.event.payload.ArticleCreatedEventPayload;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class EventTest {
    @Test
    void serde() {
        // given()
        ArticleCreatedEventPayload payload = ArticleCreatedEventPayload.builder()
                .articleId(1L)
                .title("title")
                .content("content")
                .boardId(1L)
                .writerId(1L)
                .createdAt(LocalDateTime.now())
                .modifiedAt(LocalDateTime.now())
                .bardArticleCount(23L)
                .build();

        Event<EventPayload> event = Event.of(1234L, EventType.ARTICLE_CREATED, payload);

        String json = event.toJson();
        System.out.println("json = " + json);

        // when
        Event<EventPayload> result = Event.fromJson(json);

        // then
        assertEquals(result.getEventId(), event.getEventId());
        assertEquals(result.getType(), event.getType());
        assertEquals(result.getPayload().getClass(), event.getPayload().getClass());

        ArticleCreatedEventPayload resultPayload = (ArticleCreatedEventPayload) result.getPayload();
        assertEquals(resultPayload.getArticleId(), payload.getArticleId());
        assertEquals(resultPayload.getTitle(), payload.getTitle());
        assertEquals(resultPayload.getCreatedAt(), payload.getCreatedAt());
    }
}