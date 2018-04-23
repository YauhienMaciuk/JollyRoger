package com.issoft.service;

import com.issoft.entity.News;

import java.util.Set;

public interface NewsService {

    void createNews(News news);

    News receiveNews(Long id);

    Set<News> receiveAllNews();

    Set<News> receiveAllNewsByAuthorId(Long id);

    void updateNews(News news);

    void deleteNews(Long id);
}
