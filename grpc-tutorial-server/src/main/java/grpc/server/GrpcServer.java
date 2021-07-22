package grpc.server;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import io.grpc.Server;
import io.grpc.ServerBuilder;

/**
 * @author syoka
 * @version : GrpcServer.java, v 1.0 2021年07月21日 13时29分 syoka Exp$
 */
public class GrpcServer {

    public static void main(String[] args) throws IOException, InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        final Server server = ServerBuilder.forPort(9999)
                .addService(new SimpleCalculator(countDownLatch))
                .build();
        server.start();

        countDownLatch.await();

        Runtime.getRuntime().addShutdownHook(new Thread(server::shutdown));
    }
}
