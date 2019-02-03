package com.chingon;

public class ArrayMerging {
    private int pointer[];
    private MyMinHeap myMinHeap;
    private int[] result;


    public ArrayMerging(int[]... arrays) {
        this.pointer = new int[arrays.length];
        this.myMinHeap = new MyMinHeap(pointer.length);
        calculateLength(arrays);
    }

    private void calculateLength(int[]... arrays) throws ArrayIndexOutOfBoundsException {
        int amountOfArrays = arrays.length;
        int arrayLength = arrays[0].length;

        for(int i=1; i < arrays.length; i++) {
            if (arrays[i].length != arrayLength)
                throw new ArrayIndexOutOfBoundsException("Arrays have not the same length");
        }
        this.result = new int[arrayLength * amountOfArrays];
    }


    public void mergeArrays(int[]... arrays) {
        for(int i = 0; i < arrays.length; i++) {
            int element = arrays[i][pointer[i]];
            myMinHeap.addToHeap(element);

        }
        myMinHeap.printHeapArray();

    }





    public static void merge(int[]... arrays) {
        ArrayMerging arrayMerging = new ArrayMerging(arrays);
        arrayMerging.mergeArrays(arrays);
    }
    public static void main(String[] args) {

        int arr1[] = new int[]{34, 55, 56 ,59, 87};
        int arr2[] = new int[]{2, 25 ,46, 47, 60};
        int arr3[] = new int[]{4, 21, 33 ,40, 87};


        merge(arr1,arr2,arr3);
    }
}
