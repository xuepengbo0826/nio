package com.learn.vertx.controller;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.learn.vertx.service.BookService;

/**
 * @Auther: xuepengbo
 * @Date: 2018/9/20 16:41
 * @Description:
 */
@Singleton
public class TestController {

    private static BookService bookService;

    @Inject
    public TestController(BookService bookService) {
        this.bookService = bookService;
    }

    public static void say(){
        bookService.say();
    }
}
