package com.zez.elklog;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Purple
 * @Date 2020/8/13
 */
@Slf4j
@RestController
public class test {

    @GetMapping("/test1")
    public String test(){
        log.info("你好啊e");
        log.warn("This is a warn message!");
        log.error("This is error message!");
        return "server被调用了！";
    }
}
