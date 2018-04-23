package com.issoft.service;

import com.issoft.entity.News;
import com.issoft.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class NewsServiceImpl implements NewsService {

    private NewsRepository newsRepository;

    @Autowired
    public NewsServiceImpl(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    @Override
    public void createNews(News news) {
        news.setDateTime(Instant.now());
        newsRepository.save(news);
    }

    @Override
    public News receiveNews(Long id) {
        return newsRepository.findById(id).get();
    }

    @Override
    public List<News> receiveAllNews() {
        Iterable<News> newsIterable = newsRepository.findAll();
        return StreamSupport.stream(newsIterable.spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public List<News> receiveAllNewsByAuthorId(Long id) {
        return newsRepository.findAllNewsByAuthorId(id);
    }

    @Override
    public void updateNews(News news) {
        news.setDateTime(Instant.now());
        newsRepository.save(news);
    }

    @Override
    public void deleteNews(Long id) {
        newsRepository.deleteById(id);
    }
}
