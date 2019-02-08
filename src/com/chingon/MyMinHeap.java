package com.chingon;

public class MyMinHeap {

    private int[] heap;
    private int maxSize;
    private int nextFreeIndex;


    public MyMinHeap(int maxSize) {
        this.heap = new int[maxSize];
        this.maxSize = maxSize;
        nextFreeIndex = 0;
    }

    public MyMinHeap(int inputArray[]) throws ArrayIndexOutOfBoundsException {
        this(2 * inputArray.length);

        if (inputArray.length > maxSize)
            throw new ArrayIndexOutOfBoundsException("Given Array is too big!");

        for (int element : inputArray)
            addToHeap(element);
    }

    public int getRoot() {
        return heap[0];
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

    private boolean leftChildAvailable(int index) {
        return leftChild(index) < nextFreeIndex;
    }

    private boolean rightChildAvailable(int index) {
        return rightChild(index) < nextFreeIndex;
    }


    public void addToHeap(int element) {
        resizeArray();

        int size = nextFreeIndex++;
        heap[size] = element;
        heapifyUp(size);
    }

    private void resizeArray() {
        while (nextFreeIndex >= maxSize) {
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
        while (heap[index] < heap[parent(index)]) {
            swap(index, parent(index));
            index = parent(index);
        }

    }

    private void swap(int elt1, int elt2) {
        int temp = heap[elt2];
        heap[elt2] = heap[elt1];
        heap[elt1] = temp;
    }

    public void printHeapArray() {
        System.out.println("Ordered Heap:");
        for (int i = 0; i < nextFreeIndex; i++)
            System.out.print(heap[i] + " ");
    }

    public void delete(int index) {
        if (index >= nextFreeIndex) {
            System.out.println("\nDelete: Index " + index + " is too large. Maximum " + (nextFreeIndex-1) + " !!!");
            return;
        }

        System.out.println("\nDelete <Index: " + index + "> : " + heap[index]);
        deleteIndexElement(index);
        heapifyDown(index);
        printHeapArray();
    }

    private void deleteIndexElement(int index) {
        swap(index, --nextFreeIndex);
        heap[nextFreeIndex] = 0;
    }

    private void heapifyDown(int index) {
        while (true) {
            if(!leftChildAvailable(index)) {
                return;
            }

            if(!rightChildAvailable(index)) {
                if(heap[index] > heap[leftChild(index)]) {
                    swap(index,leftChild(index));
                }
                return;
            }

            if(heap[rightChild(index)] < heap[leftChild(index)]) {
                if(heap[index] > heap[rightChild(index)]) {
                    swap(index, rightChild(index));
                    index = rightChild(index);
                    continue;
                }
                return;
            }

            if(heap[index] < heap[leftChild(index)]) {
                return;
            }

            swap(index,leftChild(index));
            index = leftChild(index);
        }
    }


    public static void main(String[] args) {
        MyMinHeap minheap = new MyMinHeap(new int[]{1, 5, 6, 9, 11, 8, 15, 17, 21});
//        minheap.addToHeap(1);
//        minheap.addToHeap(3);
//        minheap.addToHeap(6);
//        minheap.addToHeap(5);
//        minheap.addToHeap(9);
//        minheap.addToHeap(8);
//        minheap.addToHeap(-2);
        minheap.printHeapArray();
        minheap.delete(1);
        minheap.delete(0);
        minheap.delete(0);
        minheap.delete(0);
        minheap.delete(0);
        minheap.delete(0);
        minheap.delete(0);
    }
}
