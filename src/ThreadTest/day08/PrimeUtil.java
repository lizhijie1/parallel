package day01;

import java.util.stream.IntStream;

public class PrimeUtil {
    public static boolean isPrime(int number){
        int temp = number;
        if(temp < 2){
            return false;
        }
        for (int i = 2; Math.sqrt(temp)>=i;i++){
            if (temp % i == 0){
                return  false;
            }
        }
        return true;
    }

    public static void main(String args[]){
        //parallel得到并行流
       //System.out.println(IntStream.range(1,10000).parallel().filter(PrimeUtil::isPrime).count());
       //非并行
        System.out.println(IntStream.range(1,10000).filter(PrimeUtil::isPrime).count());
    }
}
