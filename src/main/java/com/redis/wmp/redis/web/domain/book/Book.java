package com.redis.wmp.redis.web.domain.book;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Id;

@Getter @Setter
@RedisHash("book")
public class Book {

    @Id
    private Long id;
    private String title;
    private String price;
}
