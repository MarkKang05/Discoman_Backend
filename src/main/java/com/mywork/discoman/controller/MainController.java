package com.mywork.discoman.controller;

import com.mywork.discoman.service.RedisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequiredArgsConstructor
@RequestMapping("/redis")
@Slf4j
public class MainController {

    private final RedisService redisService;

    @PostMapping("/")
    public void startRediss(@RequestBody HashMap<String, String> body){
        log.debug("Post");
        redisService.setValues(body.get("name"), body.get("age"));
    }

    @GetMapping("/")
    public String startRedis(@RequestParam String name){
        return redisService.getValues(name);
    }
}
