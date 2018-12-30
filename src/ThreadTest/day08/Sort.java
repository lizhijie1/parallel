package day01;

import java.util.Arrays;

public class Sort {
    public static void main(String args[]){
        int arr[] = {5,53,6,3,4};
        //Arrays.sort(arr);
        //并行排序
        Arrays.parallelSort(arr);
        System.out.println(Arrays.toString(arr));

    }
}
