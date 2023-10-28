package rookie;

import java.util.Arrays;

import static rookie.SelectionSort.swap;

public class BubbleSort {
    public static void main(String[] arg){
        int[] arr = new int[]{83,456,72,2,4,724,6,7,2};

        bubbleSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    private static void bubbleSort(int[] arr) {
        if(arr == null || arr.length<2)
            return;

        //每次两两比较过后较大的值从前到后逐渐冒泡，不受数据状况的影响，算法复杂度一直是O(n^2)
        for(int i = arr.length-1; i>0 ; i--){ //最右侧保留交换过后最大的值
            for(int j = 0; j<i; j++){
                if(arr[j] > arr[j+1]){
                    swap(arr, j, j+1);
                }
            }
        }
    }
}
