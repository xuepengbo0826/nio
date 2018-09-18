package com.learn.vertx.controller;

import com.learn.vertx.vo.Book;
import io.vertx.core.json.Json;
import io.vertx.ext.web.RoutingContext;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: xuepengbo
 * @Date: 2018/9/18 14:49
 * @Description:
 */
public class BookController {

    private static List<Book> bookList = new ArrayList<>();

    public static void queryBook(RoutingContext routingContext) {
        routingContext.response().putHeader("content-type", "application/json; charset=utf-8").end(Json.encode(bookList));
    }

    public static void addBook(RoutingContext routingContext) {
        String name = routingContext.request().getParam("name");
        String author = routingContext.request().getParam("author");
        bookList.add(Book.builder().author(name).name(author).build());
        queryBook(routingContext);
    }
}
