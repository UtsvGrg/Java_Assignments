package org.example;

import java.util.ArrayList;

public class ThreadSort{
    ArrayList<Double> arr;
    int numElements;
    int index;
    int maxThreads;
    ArrayList<Thread> threadArrayList;
    
    public ThreadSort(ArrayList<Double> arrayList, int n, int index){
        this.arr = arrayList ;
        this.numElements = n;
        this.index = index;
        this.maxThreads = (n+1)/2;
        this.threadArrayList = new ArrayList<Thread>();
    }

    public void threadSort() {
        int perThreadSwap = arr.size()/2; // numThreads = 2
        for(int i=0; i< arr.size();i++){
            if (i%2==0) {
                MultipleSwapper swapper1 = new MultipleSwapper(arr,0,perThreadSwap);
                MultipleSwapper swapper2 = new MultipleSwapper(arr,perThreadSwap,perThreadSwap*2);
                Thread thread1 = new Thread(swapper1);
                Thread thread2 = new Thread(swapper2);
                thread1.start();
                thread2.start();
                try { // Because very less chance but still race condition was prevalent.
                    thread1.join();
                    thread2.join();
                } 
                catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
            if (i%2!=0){
                MultipleSwapper swapper3 = new MultipleSwapper(arr,1,perThreadSwap);
                MultipleSwapper swapper4 = new MultipleSwapper(arr,perThreadSwap+1,perThreadSwap*2);
                Thread thread3 = new Thread(swapper3);
                Thread thread4 = new Thread(swapper4);
                thread3.start();
                thread4.start();
                try {
                    thread3.join();
                    thread4.join();
                } 
                catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }
}

class MultipleSwapper implements Runnable{
    ArrayList<Double> arr;
    int start;
    int end;

    public MultipleSwapper(ArrayList<Double> arr, int start, int end){
        this.arr = arr;
        this.start = start;
        this.end = end;
    }

    @Override
    public void run(){
        Double temp;
        for (int j = start; j < end; j += 2) {
            if (j + 1 < arr.size() && arr.get(j) > arr.get(j+1)) {
                temp = arr.get(j);
                arr.set(j, arr.get(j+1));
                arr.set(j+1,temp);
            }
        }
    }
}