package com.issoft.service;

import com.issoft.entity.News;
import com.issoft.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;
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
        news.setDateTime(LocalDateTime.now());
        newsRepository.save(news);
    }

    @Override
    public News receiveNews(Long id) {
        return newsRepository.findById(id).get();
    }

    @Override
    public Set<News> receiveAllNews() {
        Iterable<News> newsIterable = newsRepository.findAll();
        return StreamSupport.stream(newsIterable.spliterator(), false).collect(Collectors.toSet());
    }

    @Override
    public Set<News> receiveAllNewsByAuthorId(Long id) {
        return newsRepository.findAllNewsByAuthorId(id);
    }

    @Override
    public void updateNews(News news) {
        news.setDateTime(LocalDateTime.now());
        newsRepository.save(news);
    }

    @Override
    public void deleteNews(Long id) {
        newsRepository.deleteById(id);
    }
}
