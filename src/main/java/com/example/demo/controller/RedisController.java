package com.example.demo.controller;

import com.example.demo.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RedisController {

    @Autowired
    private RedisService redisService;

    @PostMapping("/save")
    public void saveData(@RequestBody List<String> messages) {
        redisService.saveData(messages);
    }

    @GetMapping("/addNewData/{message}")
    public void addNewData(@PathVariable String message) {
        redisService.addNewData(message);
    }

    @GetMapping("/getData/{key}")
    public List<String> getData(@PathVariable String key) {
        return redisService.getValue(key);
    }
}
