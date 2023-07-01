package com.example.api.server;

import com.example.api.endpoints.ProductEndpoints;
import com.example.api.interceptor.ProductEHInterceptor;
import io.grpc.ServerBuilder;
import io.grpc.ServerInterceptors;
import io.micronaut.context.event.BeanCreatedEvent;
import io.micronaut.context.event.BeanCreatedEventListener;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class GrpcServer implements BeanCreatedEventListener<ServerBuilder<?>> {

    @Inject private ProductEndpoints productEndpoints;
    @Inject private ProductEHInterceptor productEHInterceptor;

    @Override
    public ServerBuilder<?> onCreated(BeanCreatedEvent<ServerBuilder<?>> event) {
        return event.getBean()
                .addService(ServerInterceptors.intercept(productEndpoints.getProductEndpoint(), productEHInterceptor));
    }
}
