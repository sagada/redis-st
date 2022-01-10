package com.redis.wmp.redis.web.domain;

import com.redis.wmp.redis.web.controller.BookService;
import com.redis.wmp.redis.web.domain.book.Book;
import com.redis.wmp.redis.web.domain.book.dto.BookRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/redis")
@RequiredArgsConstructor
public class RedisController {

    private final BookService bookService;

    @PostMapping
    public void save(@RequestBody BookRequestDto bookRequestDto)
    {
        Book book = new Book();
        book.setTitle(bookRequestDto.getTitle());
        book.setContent(bookRequestDto.getContent());

        bookService.save(book);
    }

}
