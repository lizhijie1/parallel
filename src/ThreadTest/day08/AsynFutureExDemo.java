package day01;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Complefuture中的异常处理
 */
public class AsynFutureExDemo {
    public  static  Integer cals(Integer para){
        return para/0;
    }
    public static void main(String args[]){
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(()->cals(50))
                .exceptionally(ex->{
                    System.out.println(ex.toString());
                    return 0;
                }).thenApply((i)->Integer.toString(i))
                .thenApply((str)->"\""+str+"\"").thenAccept(System.out::println);
        try {
            future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
