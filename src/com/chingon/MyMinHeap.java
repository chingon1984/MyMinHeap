package com.chingon;

public class MyMinHeap {

    private int[] heap;
    private int maxSize;
    private int lastIndex;


    public MyMinHeap(int maxSize) {
        this.heap = new int[maxSize];
        this.maxSize = maxSize;
        lastIndex = 0;
    }

    public MyMinHeap(int maxSize, int inputArray[]) throws ArrayIndexOutOfBoundsException {
        this(maxSize);

        if (inputArray.length > maxSize)
            throw new ArrayIndexOutOfBoundsException("Given Array is too big!");

        for (int element : inputArray)
            addToHeap(element);
    }


    private int parent(int index) {
        return (index - 1) / 2;
    }

    private int leftChild(int index) {
        return 2 * index + 1;
    }

    private int rightChild(int index) {
        return 2 * index + 2;
    }


    private void addToHeap(int element) {
        /*- get size of heap
         * - put new element on last place
         * - heapify the element up*/

        resizeArray();

        int size = lastIndex++;
        heap[size] = element;
        heapifyUp(size);

    }

    private void resizeArray() {
        while (lastIndex >= maxSize) {
            int newMaxSize = 2 * maxSize;
            int tempArray[] = new int[maxSize];
            tempArray = heap;
            heap = new int[newMaxSize];

            for (int i = 0; i < maxSize; i++)
                heap[i] = tempArray[i];

            maxSize = newMaxSize;
        }
    }

    private void heapifyUp(int index) {
        /*-compare child with parent starting at inde = end , while (child < parent)
         *   -swap(child, parent)
         *   -new index = index(parent)
         * */
        while (heap[index] < heap[parent(index)]) {
            swap(index, parent(index));
            index = parent(index);
        }

    }

    private void swap(int child, int parent) {
        int temp = heap[parent];
        heap[parent] = heap[child];
        heap[child] = temp;
    }

    private void printHeapArray() {
        System.out.println("Ordered Heap:");
        for (int i = 0; i < lastIndex; i++)
            System.out.print(heap[i] + " ");
    }


    public static void main(String[] args) {
        MyMinHeap minheap = new MyMinHeap(6, new int[]{4, 6, 3, 7});
//        minheap.addToHeap(1);
//        minheap.addToHeap(3);
//        minheap.addToHeap(6);
//        minheap.addToHeap(5);
//        minheap.addToHeap(9);
//        minheap.addToHeap(8);
//        minheap.addToHeap(-2);


        minheap.printHeapArray();
    }
}
