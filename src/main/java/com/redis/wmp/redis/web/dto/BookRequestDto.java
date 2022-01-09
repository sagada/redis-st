package com.redis.wmp.redis.web.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookRequestDto {
    private String title;
    private String content;
}
