package com.example;
import com.grpc.ProductServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import com.grpc.ProductOuterClass.Product;
import com.grpc.ProductOuterClass.GetProductRequest;
import com.grpc.ProductOuterClass.DeleteProductRequest;
import com.grpc.ProductOuterClass.UpdateProductRequest;

public class GrpcClient {
    private final ProductServiceGrpc.ProductServiceBlockingStub blockingStub;

    public GrpcClient(String host, int port) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext() // Use plaintext communication, remove for secure connection
                .build();

        blockingStub = ProductServiceGrpc.newBlockingStub(channel);
    }

    public Product createProduct(Product product){
        return blockingStub.createProduct(product);
    }

    public Product getProduct(String id) {
        GetProductRequest request = GetProductRequest.newBuilder()
                .setId(id)
                .build();

        return blockingStub.getProduct(request);
    }

    public Product updateProduct(Product product) {
        UpdateProductRequest request = UpdateProductRequest.newBuilder()
                .setId(product.getId())
                .setName(product.getName())
                .setPrice(product.getPrice())
                .build();

        return blockingStub.updateProduct(request);
    }

//    public void deleteProduct(String id) {
//        DeleteProductRequest request = DeleteProductRequest.newBuilder()
//                .setId(id)
//                .build();
//
//        blockingStub.deleteProduct(request);
//    }

    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:50051")
                .usePlaintext() // Use plaintext communication, remove for secure connection
                .build();

        var blockingStub = ProductServiceGrpc.newBlockingStub(channel);
        var request = Product.newBuilder()
                .setName("Test Product")
                .setPrice(100)
                .build();
        var response = blockingStub.createProduct(request);
        System.out.println(response);
    }
}
