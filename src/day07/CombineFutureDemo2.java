package day01;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * 组合多个CompletableFuture的第二种实现方式
 */
public class CombineFutureDemo2 {
    public  static  Integer calc(Integer para){
        return  para/2;
    }
    public static void main(String args[]){
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(()->calc(50));
        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(()->calc(25));

        CompletableFuture<Void> fu = future1.thenCombine(future2,(i,j)->(i+j))
                .thenApply((str)->"\""+str+"\"")
                .thenAccept(System.out::println);
        try {
            fu.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
