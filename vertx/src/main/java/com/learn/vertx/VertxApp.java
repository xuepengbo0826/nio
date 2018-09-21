package com.learn.vertx;

import com.englishtown.vertx.guice.GuiceVerticleFactory;
import com.englishtown.vertx.guice.GuiceVerticleLoader;
import com.google.inject.Guice;
import com.learn.vertx.controller.BookController;
import com.learn.vertx.module.GuiceModule;
import com.learn.vertx.module.GuiceVerticle;
import io.vertx.core.*;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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

    public static Handler<RoutingContext> requestHandler(Method method, Class clazz){
        Handler<RoutingContext> requestHandler = new Handler<RoutingContext>() {
            @Override
            public void handle(RoutingContext event) {
                try {
                    method.invoke(clazz, event);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        };
        return requestHandler;
    }

    public static void main( String[] args ) throws Exception {
        /*Injector injector = Guice.createInjector();*/
        /*BookController bookController = injector.getInstance(BookController.class);*/
       /* String packget = "com.learn.vertx.controller";
        *//*Load.loadClass(packget);*//*
        Vertx vertx = Vertx.vertx();
        Router router = Router.router(vertx);
        for (Map.Entry<Object[], Object> objectEntry : methodMap.entrySet()) {
            *//*objectEntry.getKey()[2].getClass() clazz = injector.getInstance(objectEntry.getKey()[2]);*//*
            *//*Class clazz = injector.getInstance(((Class) objectEntry.getKey()[2]).getClass());*//*
            *//*router.route((HttpMethod) objectEntry.getKey()[1], objectEntry.getKey()[0].toString()).handler(requestHandler((Method) objectEntry.getValue(), clazz));*//*
        }*/
        /*router.get("/query").handler(BookController :: queryBook);
        router.get("/add/:name/:author").handler(BookController :: addBook);*/
        /*router.get("/query").handler(BookController:: say);
        Guice.createInjector(new GuiceVerticle());*/
        GuiceVerticle guiceVerticle = new GuiceVerticle();
        GuiceModule guiceModule = new GuiceModule();
        GuiceVerticleFactory guiceVerticleFactory = new GuiceVerticleFactory();
        GuiceVerticleLoader guiceVerticleLoader = new GuiceVerticleLoader(guiceVerticle.getClass().getName(), ClassLoader.getSystemClassLoader(), Guice.createInjector(guiceModule));
        Verticle verticle = guiceVerticleFactory.createVerticle(guiceVerticle.getClass().getName(), ClassLoader.getSystemClassLoader());
        guiceVerticleLoader.init(verticle.getVertx(), verticle.getVertx().getOrCreateContext());
        Vertx vertx = verticle.getVertx();
        Router router = Router.router(vertx);
        router.get("/query").handler(BookController:: say);
        vertx.createHttpServer().requestHandler(router :: accept).listen(8090, res -> {
            if(res.succeeded()){
                System.out.println("启动完成！");
            }else if(res.failed()){
                System.out.println("启动错误！");
            }
        });
        guiceVerticleLoader.start();
        /*vertx.createHttpServer().listen(8090);*/
    }
}
