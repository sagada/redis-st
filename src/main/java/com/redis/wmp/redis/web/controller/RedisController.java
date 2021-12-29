//package com.redis.wmp.redis.web.controller;
//
//import lombok.Getter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/redis")
//public class RedisController {
//
//    private final RedisService redisService;
//
//    @Autowired
//    public RedisController(RedisService redisService)
//    {
//        this.redisService = redisService;
//    }
//
//    @PostMapping("/{str}")
//    public String insert(@PathVariable(value = "str") String str)
//    {
//        redisService.insert(str);
//
//        return "SUCCESS";
//    }
//
//    @GetMapping("/{str}")
//    public String get(@PathVariable(value = "str") String str)
//    {
//        return redisService.getStr(str);
//    }
//}
