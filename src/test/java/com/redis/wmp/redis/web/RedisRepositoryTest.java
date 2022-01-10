package com.redis.wmp.redis.web;

import com.redis.wmp.redis.web.domain.book.Book;
import com.redis.wmp.redis.web.domain.book.BookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
public class RedisRepositoryTest {

    @Autowired
    private BookRepository repository;

    @Autowired
    private RedisTemplate<String, Book> redisTemplate;
    private static Book given()
    {
        Book book = new Book();
        book.setId(1L);
        book.setTitle("title1");
        book.setContent("Content1");
        return book;
    }

    @Test
    @DisplayName("repository Test")
    void insert()
    {
        // given
        Book book = given();

        // when
        repository.save(book);
        Book newBook = repository.findById(book.getId()).orElseThrow(RuntimeException::new);

        // then
        Assertions.assertEquals(newBook.getContent(), "Content1");
    }

    @Test
    @DisplayName("redisTemplate 테스트")
    void templateTest()
    {
        // given
        Book book = given();

        // when
        redisTemplate.opsForValue().set("1L", book);
        Book book1 = redisTemplate.opsForValue().get(1L);

        //then
        Assertions.assertNotNull(book1.getContent());
        Assertions.assertEquals("Content1", book1.getContent());
    }
}
