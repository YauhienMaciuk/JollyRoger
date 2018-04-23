package com.issoft.controller;

import com.issoft.dto.NewsDto;
import com.issoft.entity.News;
import com.issoft.service.NewsService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/news")
public class NewsController {

    private final NewsService newsService;
    private final ModelMapper modelMapper;

    public NewsController(NewsService newsService, ModelMapper modelMapper) {
        this.newsService = newsService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    public ResponseEntity<Void> createNews(@RequestBody NewsDto newsDto) {
        News news = convertToNews(newsDto);
        newsService.createNews(news);
        return ResponseEntity.created(URI.create("news/?id=" + news.getId())).build();
    }

    @GetMapping("/{newsId}")
    public ResponseEntity<News> receiveNews(@PathVariable Long newsId) {
        return ResponseEntity.ok(newsService.receiveNews(newsId));
    }

    @GetMapping("/all")
    public ResponseEntity<List<News>> receiveAllNews() {
        return ResponseEntity.ok(newsService.receiveAllNews());
    }

    @GetMapping("/all/{authorId}")
    public ResponseEntity<List<News>> receiveAllNewsByAuthorId(@PathVariable Long authorId) {
        return ResponseEntity.ok(newsService.receiveAllNewsByAuthorId(authorId));
    }

    @PutMapping
    public ResponseEntity<Void> updateNews(@RequestBody NewsDto newsDto) {
        newsService.updateNews(convertToNews(newsDto));
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNews(@PathVariable Long id) {
        newsService.deleteNews(id);
        return ResponseEntity.noContent().build();
    }

    private News convertToNews(NewsDto newsDto) {
        return modelMapper.map(newsDto, News.class);
    }
}
