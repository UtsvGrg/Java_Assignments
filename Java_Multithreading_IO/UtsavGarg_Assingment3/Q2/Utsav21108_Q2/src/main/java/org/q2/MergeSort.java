package org.q2;

public class MergeSort {
    static void merge(int[] lf, int first, int mid, int last){
        int i, len1 = mid-first+1, len2 = last-mid;
        int[] l1 = new int[len1];
        int[] l2 = new int[len2];

        for(i=0;i<len1;i++){
            l1[i]=lf[first+i];
        }
        for(i=0;i<len2;i++){
            l2[i]=lf[mid+1+i];
        }
        i=0;
        int j=0,k=first;
        while(i<len1 && j<len2){
            if (l1[i]>=l2[j]){
                lf[k]=l2[j];
                j++;
            }
            else{
                lf[k]=l1[i];
                i++;
            }
            k++;
        }
        while(i<len1){
            lf[k]=l1[i];
            i++;
            k++;
        }
        while(j<len2){
            lf[k]=l2[j];
            j++;
            k++;
        }
    }
    static void mergeSort(int[] lf, int first, int last){
        if (first<last){
            int mid = (first+last)/2;
            mergeSort(lf,first,mid);
            mergeSort(lf,mid+1,last);
            merge(lf,first,mid,last);
        }
    }
}

class ThreadSort implements Runnable{
    int[] arr;
    int first;
    int last;

    public ThreadSort(int[] arr, int first, int last){
        this.arr = arr;
        this.first = first;
        this.last = last;
    }

    @Override
    public void run(){
        MergeSort.mergeSort(arr,first,last);
    }
}



