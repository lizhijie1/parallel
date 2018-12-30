package day01;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * 组合多个CompletableFuture
 */
public class SomeFutureDemo {
    public static  Integer calc(Integer para){
        return para/2;
    }
    public static void main(String args[]){
        //将处理后的结果传递给thenCompose(),并进一步传递给后续生成的CompletableFuture
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(()->calc(50))
                .thenCompose((i)->CompletableFuture.supplyAsync(()->calc(i))).thenApply((str->"\""+str+"\""))
                .thenAccept(System.out::println);
        try {
            future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
