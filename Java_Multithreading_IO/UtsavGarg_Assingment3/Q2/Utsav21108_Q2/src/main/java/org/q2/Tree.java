package org.q2;

import java.util.TreeSet;
import java.lang.Math;
public class Tree {
    int[] arr;
    TreeSet treeSet;

    public Tree(int[] arr){
        this.arr = arr;
        this.treeSet = new TreeSet<Integer>();
    }

    public void createTree(){
        for(int i=0; i<arr.length;i++){
            treeSet.add(arr[i]);
        }
    }

    public void heightTree(){
        Math.ceil((Math.log(treeSet.size()+1)));
    }

    public void searchTree(int num){
        treeSet.contains(num);
    }
}
