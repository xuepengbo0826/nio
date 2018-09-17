package com.learn.vertx;

import io.vertx.core.Handler;
import io.vertx.core.MultiMap;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.*;
import io.vertx.core.net.NetSocket;
import io.vertx.core.net.SocketAddress;

import javax.net.ssl.SSLPeerUnverifiedException;
import javax.security.cert.X509Certificate;

/**
 * Hello world!
 *
 */
public class VertxApp{

    public static Handler<HttpServerRequest> req(){
        Handler<HttpServerRequest> requestHandler = new Handler<HttpServerRequest>() {
            @Override
            public void handle(HttpServerRequest event) {
                event.response().end("Hello World!");
            }
        };
        return requestHandler;
    }

    public static void main( String[] args ) {
        Vertx.factory.vertx().createHttpServer().requestHandler(req()).listen(8080);
    }

}
