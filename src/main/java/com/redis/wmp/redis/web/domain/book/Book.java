package com.redis.wmp.redis.web.domain.book;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Id;
import java.io.Serializable;

@Getter
@Setter
public class Book implements Serializable {
    private Long id;
    private String title;
    private String content;
}
