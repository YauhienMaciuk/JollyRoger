package com.issoft.repository;

import com.issoft.entity.News;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface NewsRepository extends CrudRepository<News, Long> {

    List<News> findAllNewsByAuthorId(Long id);
}
