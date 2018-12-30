package day01;

import java.util.Arrays;

public class FunctionProgramming {
    public static void main(String args[]){
        int[] arr = {1,3,4,5,6,7,8,9,10};
        Arrays.stream(arr).map(x->(x%2==0?x:x+1)).forEach(System.out::print);
        System.out.println("hello world");
    }
}
