package day01;

import java.util.Arrays;
import java.util.Random;

public class RandomArr {
    public static void main(String args[]){
        int[] arr = new int[23];
        Random r = new Random();
        Arrays.parallelSetAll(arr,(x)->r.nextInt());
        System.out.println(Arrays.toString(arr));
    }
}
