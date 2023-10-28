package rookie;

import java.util.Arrays;

public class SelectionSort {
    public static void main(String[] arg){
        int[] arr = new int[]{9, 2, 4, 567, 8, 36, 7, 8, 3, 3, 2, 1, 5};

        selectionSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    private static void selectionSort(int[] arr) {
        if(arr == null || arr.length < 2) return;

        //从整体中选择最小的值放到最前面:首先需要找到最小值
        for(int i=0; i<arr.length; i++){
            int minIndex = i;
            for(int j=i+1; j<arr.length; j++){
                minIndex = arr[j] < arr[minIndex]? j:minIndex;
            }
            swap(arr, i, minIndex);
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
