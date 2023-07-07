package org.example;

import java.util.ArrayList;

public class BrickSort{
    ArrayList<Double> arr;
    int numElements;
    int index;
    int maxThreads;
    ArrayList<Thread> threadArrayList;
    
    public BrickSort(ArrayList<Double> arrayList, int n, int index){
        this.arr = arrayList ;
        this.numElements = n;
        this.index = index;
        this.threadArrayList = new ArrayList<Thread>();
    }

    public void brickSort() {
        Double temp;
        for(int i=0; i< arr.size();i++){
            if (i%2==0) {
                for (int j = 0; j < arr.size(); j += 2) {
                    if (j + 1 < arr.size() && arr.get(j) > arr.get(j+1)) {
                        temp = arr.get(j);
                        arr.set(j, arr.get(j+1));
                        arr.set(j+1,temp);
                    }
                }
            }
            if (i%2!=0){
                for (int j = 1; j < arr.size(); j += 2) {
                    if (j + 1 < arr.size() && arr.get(j) > arr.get(j+1)) {
                        temp = arr.get(j);
                        arr.set(j, arr.get(j+1));
                        arr.set(j+1,temp);
                    }
                }
            }
        }
    }
}
