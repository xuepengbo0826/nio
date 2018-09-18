package com.learn.vertx;

import com.learn.vertx.controller.BookController;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Hello world!
 *
 */
public class VertxApp{

    /*public static Handler<HttpServerRequest> routerHandler(Vertx vertx){
        Handler<HttpServerRequest> httpServerRequestHandler = new Handler<HttpServerRequest>() {
            @Override
            public void handle(HttpServerRequest event) {
                Router router = Router.router(vertx);
                router.get("/query").handler(requestHandler());
                router.accept(event);
            }
        };
        return httpServerRequestHandler;
    }

    public static Handler<RoutingContext> requestHandler(){
        Handler<RoutingContext> requestHandler = new Handler<RoutingContext>() {
            @Override
            public void handle(RoutingContext event) {
                BookController.queryBook(event);
            }
        };
        return requestHandler;
    }*/

    public static void main( String[] args ) {
        Map map = new ConcurrentHashMap();
        Vertx vertx = Vertx.vertx();
        Router router = Router.router(vertx);
        router.get("/query").handler(BookController :: queryBook);
        router.get("/add/:name/:author").handler(BookController :: addBook);
        vertx.createHttpServer().requestHandler(router :: accept).listen(8090);
    }
}
