package com.github.blanexie.vxpt.example;

import com.github.blanexie.vxpt.ioc.annotation.Component;
import com.github.blanexie.vxpt.iocweb.annotation.Mapping;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.http.*;

import java.nio.charset.StandardCharsets;

@Component
public class B {

    @Mapping(path = "/url")
    HttpResponse getUrl(HttpRequest req) {
        FullHttpResponse response = new DefaultFullHttpResponse(req.protocolVersion(), HttpResponseStatus.OK,
                Unpooled.wrappedBuffer("CONTENT".getBytes(StandardCharsets.UTF_8)));
        return response;
    }
}
