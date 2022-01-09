package com.redis.wmp.redis.web.controller;

import com.redis.wmp.redis.web.domain.book.Book;
import com.redis.wmp.redis.web.domain.book.BookRepository;
import com.redis.wmp.redis.web.dto.BookRequestDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/redis")
@RequiredArgsConstructor
public class RedisController {

    private final BookRepository bookRepository;

    @PostMapping
    public void save(@RequestBody BookRequestDto bookRequestDto)
    {
        Book book = new Book();
        book.setTitle(bookRequestDto.getTitle());
        book.setContent(bookRequestDto.getContent());

        bookRepository.save(book);
    }

}
