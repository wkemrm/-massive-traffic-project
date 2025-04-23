package kuke.board.comment.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommentPathTest {
    @Test
    void createChildCommentTest() {
        // 00000 <- 생성
        createChildCommentTest(CommentPath.create(""), null, "00000");

        // 00000
        //      00000 <- 생성
        createChildCommentTest(CommentPath.create("00000"), null, "0000000000");

        // 00000
        // 00001 <- 생성
        createChildCommentTest(CommentPath.create(""), "00000", "00001");

        // 0000z
        //      abcdz
        //           zzzzz
        //                zzzzz
        // abce0 <- 생성
        createChildCommentTest(CommentPath.create("0000z"), "0000zabcdzzzzzzzzzzz", "0000zabce0");
    }

    void createChildCommentTest(CommentPath commentPath, String descendantsTopPath, String expectedChildPath) {
        CommentPath childCommentPath = commentPath.createChildCommentPath(descendantsTopPath);
        assertEquals(childCommentPath.getPath(), expectedChildPath);
    }

    @Test
    void createChildCommentPathIfMaxDepthTest() {
        assertThrows(IllegalStateException.class,
                () -> CommentPath.create("zzzzz".repeat(5)).createChildCommentPath(null));
    }

    @Test
    void createChildCommentPathIfChunkOverflowTest() {
        // given
        CommentPath commentPath = CommentPath.create("");

        // when, then
        assertThrows(IllegalStateException.class,
                () -> commentPath.createChildCommentPath("zzzzz"));

    }
}