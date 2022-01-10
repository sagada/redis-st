package com.redis.wmp.redis.web.controller;

import com.redis.wmp.redis.web.domain.book.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {

    private final RedisTemplate<Long,Book> redisTemplate;

    public void save(Book book)
    {
        redisTemplate.opsForValue().set(book.getId(), book);
    }
}
