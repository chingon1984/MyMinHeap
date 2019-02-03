package com.chingon;

public class ArrayMerging {
    private int pointer[];
    private MyMinHeap myMinHeap;
    private int[] result;
    private int arrayLength = 0;


    public ArrayMerging(int[]... arrays) {
        this.pointer = new int[arrays.length];
        this.myMinHeap = new MyMinHeap(pointer.length);
        calculateLength(arrays);
    }

    private void calculateLength(int[]... arrays) throws ArrayIndexOutOfBoundsException {
        int amountOfArrays = arrays.length;
        this.arrayLength = arrays[0].length;

        for(int i=1; i < arrays.length; i++) {
            if (arrays[i].length != arrayLength)
                throw new ArrayIndexOutOfBoundsException("Arrays have not the same length");
        }
        this.result = new int[arrayLength * amountOfArrays];
    }


    public void mergeArrays(int[]... arrays) {
        int element = 0;
        for(int i=0; i<result.length; i++) {
            this.myMinHeap = new MyMinHeap(pointer.length);
            int point = 0;

            for (int j = 0; j < arrays.length; j++) {
                // test if array still has values inside
                if(pointer[j] < arrayLength)
                    element = arrays[j][pointer[j]];
                else
                    //if not, add largest possible int to end of heap, to guarantee it won't get to root
                    element = Integer.MAX_VALUE;

                    myMinHeap.addToHeap(element);
                    if (myMinHeap.getRoot() == element)
                        point = j;
            }
            // take next element of array at next iteration
            pointer[point]++;
            result[i] = myMinHeap.getRoot();
        }
    }

    private void printResult() {
        System.out.println("Result: ");
        for (int i = 0; i < result.length; i++)
            System.out.print(result[i] + " ");
    }


    public static void merge(int[]... arrays) {
        ArrayMerging arrayMerging = new ArrayMerging(arrays);
        arrayMerging.mergeArrays(arrays);
        arrayMerging.printResult();
    }





    public static void main(String[] args) {
        int arr1[] = new int[]{6, 10, 24, 34, 55, 56 ,59, 87};
        int arr2[] = new int[]{2, 3,  8, 25 , 26, 46, 47, 60};
        int arr3[] = new int[]{1, 4,  7, 21, 33 , 40, 87, 99};


        merge(arr1,arr2,arr3);
    }
}
