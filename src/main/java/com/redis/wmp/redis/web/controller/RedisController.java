package com.redis.wmp.redis.web.controller;

import com.redis.wmp.redis.web.domain.book.Book;
import com.redis.wmp.redis.web.domain.book.BookRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/redis")
@RequiredArgsConstructor
public class RedisController {

    private final BookRepository bookRepository;

    @PostMapping("/{title}")
    public void save(@PathVariable String title)
    {
        Book book = new Book();
        book.setTitle(title);
        bookRepository.save(book);
    }

}
