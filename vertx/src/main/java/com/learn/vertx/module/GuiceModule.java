package com.learn.vertx.module;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.learn.vertx.controller.BookController;
import com.learn.vertx.service.BookService;

/**
 * @Auther: xuepengbo
 * @Date: 2018/9/21 18:06
 * @Description:
 */
public class GuiceModule implements Module {

    @Override
    public void configure(Binder binder) {
        binder.bind(BookService.class);
        binder.bind(BookController.class);
    }
}
