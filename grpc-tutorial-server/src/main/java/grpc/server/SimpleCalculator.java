package grpc.server;

import java.util.concurrent.CountDownLatch;

import com.syoka.grpc.tutorial.api.CalculatorGrpc;
import com.syoka.grpc.tutorial.api.Element;
import com.syoka.grpc.tutorial.api.Result;

import io.grpc.stub.StreamObserver;

/**
 * 阻塞式
 *
 * @author syoka
 * @version : SimpleCalculator.java, v 1.0 2021年07月21日 13时29分 syoka Exp$
 */
public class SimpleCalculator extends CalculatorGrpc.CalculatorImplBase {

    private final CountDownLatch countDownLatch;

    public SimpleCalculator(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void add(Element request, StreamObserver<Result> responseObserver) {
        int a = request.getA();
        int b = request.getB();
        Result result = Result.newBuilder().setResult(a + b).build();
        responseObserver.onNext(result);
        responseObserver.onCompleted();
        countDownLatch.countDown();
    }
}
