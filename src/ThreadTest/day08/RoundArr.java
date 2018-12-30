package day01;

import java.util.Arrays;
import java.util.function.IntConsumer;

public class RoundArr {
    public static void main(String args[]){
        int[] arr = {1,2,3,5,6,8};
        Arrays.stream(arr).forEach(new IntConsumer() {
            @Override
            public void accept(int value) {
                System.out.println(value);
            }
        });
    }
}
