package com.redis.wmp.redis.web.domain.book;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Id;
import java.io.Serializable;

@Getter
@Setter
@RedisHash("book")
public class Book implements Serializable {
    @Id
    private Long id;
    private String title;
    private String content;
}
