package com.issoft.service

import com.issoft.entity.News
import com.issoft.entity.User
import org.apache.commons.lang.RandomStringUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Stepwise

import java.time.LocalDateTime
import java.util.function.Consumer

@Stepwise
@SpringBootTest
class NewsServiceTest extends Specification {

    @Autowired
    private NewsService newsService

    @Autowired
    private UserService userService

    @Shared
    private News news

    @Shared
    private Long authorId

    def setup() {
        User testUser = new UserServiceSpec().createUser()
        userService.createUser(testUser)
        authorId = testUser.id
        news = createNews()
    }

    def "create and receive news"() {
        given:
            newsService.createNews(news)
        when:
            News createdNews = newsService.receiveNews(news.id)
        then:
            createdNews != null
            createdNews.id == news.id
            createdNews.authorId == news.authorId
            createdNews.title == news.title
            createdNews.description == news.description
            createdNews.text == news.text
    }

    def "update news"() {
        given:
            newsService.createNews(news)
            news.setTitle("New title")
            news.setDescription("New description")
            news.setText("News Text")
            newsService.updateNews(news)
        when:
            News updatedNews = newsService.receiveNews(news.id)
        then:
            updatedNews.title == news.title
            updatedNews.description == news.description
            updatedNews.text == news.text
    }

    def "delete news"() {
        given:
            newsService.createNews(news)
        when:
            News existingNews = newsService.receiveNews(news.id)
        then:
            existingNews != null
        when:
            newsService.deleteNews(news.id)
            newsService.receiveNews(news.id)
        then:
            thrown(NoSuchElementException)
    }

    def "receive all news"() {
        given:
            News firstNews = createNews()
            News secondNews = createNews()
            News thirdNews = createNews()

            newsService.createNews(firstNews)
            newsService.createNews(secondNews)
            newsService.createNews(thirdNews)
        when:
            Iterable<News> newsIterableFromRepository = newsService.receiveAllNews()
        then:
            newsIterableFromRepository != null
            newsIterableFromRepository.asList().isEmpty() == false
            Consumer<News> newsConsumer = new Consumer<News>() {
                @Override
                void accept(News news) {
                    news != null
                }
            }
            newsIterableFromRepository.asList().forEach(newsConsumer)
    }

    def "receive all news by authorId"() {
        given:
            News firstNews = createNews()
            News secondNews = createNews()
            News thirdNews = createNews()

            newsService.createNews(firstNews)
            newsService.createNews(secondNews)
            newsService.createNews(thirdNews)
        when:
            Iterable<News> newsByAuthorIdIterableFromRepository = newsService.receiveAllNewsByAuthorId(authorId)
        then:
            newsByAuthorIdIterableFromRepository != null
            newsByAuthorIdIterableFromRepository.asList().isEmpty() == false
            Consumer<News> newsConsumer = new Consumer<News>() {
                @Override
                void accept(News news) {
                    news != null
                    news.getAuthorId() == firstNews.getAuthorId()
                }
            }
            newsByAuthorIdIterableFromRepository.asList().forEach(newsConsumer)
    }

    private News createNews() {
        String title = RandomStringUtils.randomAlphabetic(10)
        String description = RandomStringUtils.randomAlphabetic(10)
        String text = RandomStringUtils.randomAlphabetic(10)
        LocalDateTime dateTime = LocalDateTime.now()
        News news = new News()
        news.setAuthorId(authorId)
        news.setTitle(title)
        news.setDescription(description)
        news.setText(text)
        news.setDateTime(dateTime)
        return news
    }
}