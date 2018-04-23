package com.issoft.service;

import com.issoft.entity.News;

import java.util.List;

public interface NewsService {

    void createNews(News news);

    News receiveNews(Long id);

    List<News> receiveAllNews();

    List<News> receiveAllNewsByAuthorId(Long id);

    void updateNews(News news);

    void deleteNews(Long id);
}
