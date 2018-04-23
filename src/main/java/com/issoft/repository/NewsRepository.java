package com.issoft.repository;

import com.issoft.entity.News;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface NewsRepository extends CrudRepository<News, Long> {

    Set<News> findAllNewsByAuthorId(Long id);
}
