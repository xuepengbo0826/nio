package com.learn.vertx.controller;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.learn.vertx.service.BookService;
import com.learn.vertx.vo.Book;
import io.vertx.core.Vertx;
import io.vertx.core.json.Json;
import io.vertx.ext.web.RoutingContext;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: xuepengbo
 * @Date: 2018/9/18 14:49
 * @Description:
 */
@Singleton
public class BookController {

    @Inject
    private static BookService bookService;

    private static List<Book> bookList = new ArrayList<>();

    @GET
    @Path(value = "book")
    public static void queryBook() {
        System.out.println("aaaa");
    }

    public static void addBook(RoutingContext routingContext) {
        System.out.println(routingContext.getBodyAsString());
        String name = routingContext.request().getParam("name");
        String author = routingContext.request().getParam("author");
        bookList.add(Book.builder().author(name).name(author).build());
    }

    public static void say(RoutingContext routingContext){
        bookService.say();
    }
}
