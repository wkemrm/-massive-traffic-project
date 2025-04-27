package kuke.board.articleread.controller;

import kuke.board.articleread.service.ArticleReadService;
import kuke.board.articleread.service.response.ArticleReadPageResponse;
import kuke.board.articleread.service.response.ArticleReadResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/articles")
public class ArticleReadController {
    private final ArticleReadService articleReadService;

    @GetMapping("/{articleId}")
    public ArticleReadResponse read(@PathVariable("articleId") Long articleId) {
        return articleReadService.read(articleId);
    }

    @GetMapping
    public ArticleReadPageResponse readAll(
            @RequestParam("boardId") Long boardId,
            @RequestParam("page") Long page,
            @RequestParam("pageSize") Long pageSize
    ) {
        return articleReadService.readAll(boardId, page, pageSize);
    }

    @GetMapping("/infinite-scroll")
    public List<ArticleReadResponse> readInfiniteScroll(
            @RequestParam("boardId") Long boardId,
            @RequestParam("pageSize") Long pageSize,
            @RequestParam(value = "lastArticleId", required = false) Long lastArticleId
    ) {
        return articleReadService.readAllInfiniteScroll(boardId, lastArticleId, pageSize);
    }
}
