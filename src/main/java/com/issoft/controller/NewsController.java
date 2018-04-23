package com.issoft.controller;

import com.issoft.entity.News;
import com.issoft.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/news")
public class NewsController {

    private NewsService newsService;

    @Autowired
    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @PostMapping(path = "/create", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Void> createNews(@RequestBody News news) {
        newsService.createNews(news);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/receive_news/{newsId}")
    public ResponseEntity<News> receiveNews(@PathVariable Long newsId) {
        return ResponseEntity.ok(newsService.receiveNews(newsId));
    }

    @GetMapping("/all")
    public ResponseEntity<Set<News>> receiveAllNews() {
        return ResponseEntity.ok(newsService.receiveAllNews());
    }

    @GetMapping("/all/user_id/{authorId}")
    public ResponseEntity<Set<News>> receiveAllNewsByAuthorId(@PathVariable Long authorId) {
        return ResponseEntity.ok(newsService.receiveAllNewsByAuthorId(authorId));
    }

    @PutMapping("/update")
    public ResponseEntity<Void> updateNews(@RequestBody News news) {
        newsService.updateNews(news);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteNews(Long id) {
        newsService.deleteNews(id);
        return ResponseEntity.ok().build();
    }
}
