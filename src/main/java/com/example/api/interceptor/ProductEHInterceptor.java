package com.example.api.interceptor;

import io.grpc.*;
import jakarta.inject.Singleton;

@Singleton
public class ProductEHInterceptor implements ServerInterceptor {

    @Override
    public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(ServerCall<ReqT, RespT> call,
                                                                 Metadata headers,
                                                                 ServerCallHandler<ReqT, RespT> next) {
        ServerCall.Listener<ReqT> delegate = next.startCall(call, headers);
        return new ForwardingServerCallListener.SimpleForwardingServerCallListener<>(delegate) {
            @Override
            public void onHalfClose() {
                try {
                    super.onHalfClose();
                } catch (IllegalArgumentException illegalArgumentException){
                    call.close(Status.INTERNAL.withCause(illegalArgumentException).withDescription(illegalArgumentException.getMessage()), headers);
                }catch (Exception e) {
                    call.close(Status.INTERNAL.withCause(e).withDescription("Error occurred"), headers);
                }
            }
        };
    }
}
