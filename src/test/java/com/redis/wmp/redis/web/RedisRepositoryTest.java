package com.redis.wmp.redis.web;

import com.redis.wmp.redis.web.domain.book.Book;
import com.redis.wmp.redis.web.domain.book.BookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ClusterOperations;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Optional;

@SpringBootTest
public class RedisRepositoryTest {
    @Autowired
    private BookRepository repository;

    @Test
    void insert()
    {
        Book book = new Book();

        book.setTitle("title");
        book.setContent("Content");

        repository.save(book);


        Optional<Book> byId = repository.findById(book.getId());

        Assertions.assertFalse(byId.isPresent());
    }
}
