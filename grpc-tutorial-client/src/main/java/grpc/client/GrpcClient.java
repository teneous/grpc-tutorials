package grpc.client;

import com.syoka.grpc.tutorial.api.CalculatorGrpc;
import com.syoka.grpc.tutorial.api.Element;
import com.syoka.grpc.tutorial.api.Result;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

/**
 * @author syoka
 * @version : GrpcClient.java, v 1.0 2021年07月21日 23时56分 syoka Exp$
 */
public class GrpcClient {

    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("127.0.0.1", 9999)
                .usePlaintext()
                .build();

        CalculatorGrpc.CalculatorBlockingStub stub = CalculatorGrpc.newBlockingStub(channel);

        Element element = Element.newBuilder().setA(25).setB(30).build();
        Result result = stub.add(element);
        System.out.println("result:" + result);
    }
}
