package com.example.api.endpoints;

import com.example.domain.model.CreateProductRequest;
import com.example.service.ProductService;
import com.grpc.ProductOuterClass;
import com.grpc.ProductServiceGrpc;
import io.grpc.stub.StreamObserver;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class ProductEndpoint extends ProductServiceGrpc.ProductServiceImplBase {

    private final ProductService productService;

    @Inject
    public ProductEndpoint(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void createProduct(ProductOuterClass.Product request, StreamObserver<ProductOuterClass.Product> responseObserver) {
        var createProductReq = buildCreateProductReq(request);
        var createdproduct = productService.save(createProductReq);
        var rpcResponse = ProductOuterClass.Product.newBuilder()
                .setId(createdproduct.getId())
                .setName(createdproduct.getName())
                .build();
        responseObserver.onNext(rpcResponse);
        responseObserver.onCompleted();
    }

    private CreateProductRequest buildCreateProductReq(ProductOuterClass.Product request) {
        return CreateProductRequest.with(request.getName(), request.getPrice(), "desc");
    }

    @Override
    public void getProduct(ProductOuterClass.GetProductRequest request, StreamObserver<ProductOuterClass.Product> responseObserver) {
        super.getProduct(request, responseObserver);
    }

    @Override
    public void updateProduct(ProductOuterClass.UpdateProductRequest request, StreamObserver<ProductOuterClass.Product> responseObserver) {
        super.updateProduct(request, responseObserver);
    }

    @Override
    public void deleteProduct(ProductOuterClass.DeleteProductRequest request, StreamObserver<ProductOuterClass.Empty> responseObserver) {
        super.deleteProduct(request, responseObserver);
    }
}
