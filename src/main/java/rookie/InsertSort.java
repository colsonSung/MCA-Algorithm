package rookie;

import java.util.Arrays;

import static rookie.SelectionSort.swap;

public class InsertSort {
    //从左边开始逐渐扩大局部，并通过讲新加入的元素插入至合适位置始终保持局部有序；
    public static void main(String[] arg){
        int[] arr = new int[]{83,456,72,2,4,724,6,7,2};

        insertSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    private static void insertSort(int[] arr) {
        if(arr == null || arr.length<2)
            return;

        for(int i=1; i< arr.length; i++){ //逐渐扩大数据边界
            for(int j= i-1; j>=0 ; j--){  //类似于局部冒泡
                if(arr[j+1] < arr[j]){
                    swap(arr, j, j+1);
                }
            }
        }
    }

}
