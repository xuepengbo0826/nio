package com.learn.vertx;

import com.learn.vertx.annotation.Action;
import com.learn.vertx.controller.BookController;
import com.learn.vertx.util.Load;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Hello world!
 *
 */
public class VertxApp{

    public static Map<Object[], Object> methodMap = new ConcurrentHashMap<>();

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
    }*/

    public static Handler<RoutingContext> requestHandler(Method method){
        Handler<RoutingContext> requestHandler = new Handler<RoutingContext>() {
            @Override
            public void handle(RoutingContext event) {
                try {
                    method.invoke(BookController.class, event);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        };
        return requestHandler;
    }

    public static void main( String[] args ) {
        String packget = "com.learn.vertx.controller";
        Load.loadClass(packget);
        Vertx vertx = Vertx.vertx();
        Router router = Router.router(vertx);
        router.route("/*").handler(BodyHandler.create());
        for (Map.Entry<Object[], Object> objectEntry : methodMap.entrySet()) {
            router.route((HttpMethod) objectEntry.getKey()[1], objectEntry.getKey()[0].toString()).handler(requestHandler((Method) objectEntry.getValue()));
            /*System.out.println(objectEntry.getValue());*/
        }
        /*router.get("/query").handler(BookController :: queryBook);
        router.get("/add/:name/:author").handler(BookController :: addBook);*/
        vertx.createHttpServer().requestHandler(router :: accept).listen(8090);
    }
}
