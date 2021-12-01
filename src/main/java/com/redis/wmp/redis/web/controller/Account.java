package com.redis.wmp.redis.web.controller;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Entity
public class Account {
    @Id
    private Long id;
    private String name;
}
