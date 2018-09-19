package com.learn.vertx.controller;

import com.learn.vertx.annotation.Action;
import com.learn.vertx.annotation.Url;
import com.learn.vertx.vo.Book;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: xuepengbo
 * @Date: 2018/9/18 14:49
 * @Description:
 */
@Action(name = "book")
public class BookController {

    private static List<Book> bookList = new ArrayList<>();

    @Url(path = "book", method = HttpMethod.GET)
    public static void queryBook(RoutingContext routingContext) {
        routingContext.response().putHeader("content-type", "application/json; charset=utf-8").end(Json.encode(bookList));
    }

    @Url(path = "book/:name/:author", method = HttpMethod.POST)
    public static void addBook(RoutingContext routingContext) {
        System.out.println(routingContext.getBodyAsString());
        String name = routingContext.request().getParam("name");
        String author = routingContext.request().getParam("author");
        bookList.add(Book.builder().author(name).name(author).build());
        queryBook(routingContext);
    }
}
