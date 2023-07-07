package org.q2;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class Main {
    static PrintWriter out = null;
    static long startTime;
    static long finishTime;
    static long num = 1;
    static void case1(int[] a){
        out.println("\n#"+num+" | For 0 Threads");
        System.out.println("\n#"+num+" | For 0 Threads");
        num++;
        startTime = System.nanoTime();
        MergeSort.mergeSort(a,0,a.length-1);
        Tree tree = new Tree(a);
        tree.createTree();
        finishTime = System.nanoTime();
        out.println("Tree creation: "+ (finishTime - startTime)+" ns");
        System.out.println("Tree creation: "+ (finishTime - startTime)+" ns");
        startTime = System.nanoTime();
        System.out.println("Height of the tree: "+tree.heightTree());
        out.println("Height of the tree: "+tree.heightTree());
        finishTime = System.nanoTime();
        out.println("Height calculation: "+(finishTime - startTime)+" ns");
        System.out.println("Height calculation: "+(finishTime - startTime)+" ns");
        startTime = System.nanoTime();
        tree.searchTree(8);
        finishTime = System.nanoTime();
        out.println("Search calculation: "+(finishTime - startTime)+" ns");
        System.out.println("Search calculation: "+(finishTime - startTime)+" ns");
        out.flush();
    }

    static void case2(int[] a){
        out.println("\n#"+num+" | For 2 Threads");
        System.out.println("\n#"+num+" | For 2 Threads");
        num++;
        startTime = System.nanoTime();
        ThreadSort sort1 = new ThreadSort(a,0,(a.length-1)/2);
        ThreadSort sort2 = new ThreadSort(a,(a.length-1)/2+1,a.length-1);
        Thread thread1 = new Thread(sort1);
        Thread thread2 = new Thread(sort2);
        thread1.start();
        thread2.start();
        try{
            thread1.join();
            thread2.join();
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }

        MergeSort.merge(a,0,(a.length-1)/2,a.length-1);
        Tree tree = new Tree(a);
        tree.createTree();
        finishTime = System.nanoTime();
        out.println("Tree creation: "+ (finishTime - startTime)+" ns");
        System.out.println("Tree creation: "+ (finishTime - startTime)+" ns");
        startTime = System.nanoTime();
        System.out.println("Height of the tree: "+tree.heightTree());
        out.println("Height of the tree: "+tree.heightTree());
        finishTime = System.nanoTime();
        out.println("Height calculation: "+(finishTime - startTime)+" ns");
        System.out.println("Height calculation: "+(finishTime - startTime)+" ns");
        startTime = System.nanoTime();
        tree.searchTree(8);
        finishTime = System.nanoTime();
        out.println("Search calculation: "+(finishTime - startTime)+" ns");
        System.out.println("Search calculation: "+(finishTime - startTime)+" ns");
        out.flush();
    }

    static void case3(int[] a){
        out.println("\n#"+num+" | For 4 Threads");
        System.out.println("\n#"+num+" | For 4 Threads");
        num++;
        startTime = System.nanoTime();

        ThreadSort sort1 = new ThreadSort(a,0,(a.length-1)/4);
        ThreadSort sort2 = new ThreadSort(a,(a.length-1)/4+1,(a.length-1)/2);
        ThreadSort sort3 = new ThreadSort(a,(a.length-1)/2+1,(a.length-1)*3/4);
        ThreadSort sort4 = new ThreadSort(a,(a.length-1)*3/4+1,(a.length-1));

        Thread thread1 = new Thread(sort1);
        Thread thread2 = new Thread(sort2);
        Thread thread3 = new Thread(sort3);
        Thread thread4 = new Thread(sort4);

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

        try{
            thread1.join();
            thread2.join();
            thread3.join();
            thread4.join();
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }

        MergeSort.merge(a,0,(a.length-1)/4,(a.length-1)/2);
        MergeSort.merge(a,(a.length-1)/2+1,(a.length-1)*3/4,a.length-1);
        MergeSort.merge(a,0,(a.length-1)/2,a.length-1);

        Tree tree = new Tree(a);
        tree.createTree();
        finishTime = System.nanoTime();
        out.println("Tree creation: "+ (finishTime - startTime)+" ns");
        System.out.println("Tree creation: "+ (finishTime - startTime)+" ns");
        startTime = System.nanoTime();
        System.out.println("Height of the tree: "+tree.heightTree());
        out.println("Height of the tree: "+tree.heightTree());
        finishTime = System.nanoTime();
        out.println("Height calculation: "+(finishTime - startTime)+" ns");
        System.out.println("Height calculation: "+(finishTime - startTime)+" ns");
        startTime = System.nanoTime();
        tree.searchTree(8);
        finishTime = System.nanoTime();
        out.println("Search calculation: "+(finishTime - startTime)+" ns");
        System.out.println("Search calculation: "+(finishTime - startTime)+" ns");
        out.flush();
    }

    public static void main(String[] args) throws IOException {
        out = new PrintWriter(new FileWriter("output.txt"));

        int[] num = new int[3];
        num[0] = 10;
        num[1] = 1000;
        num[2] = 1000000;

        for(int j=0; j<3;j++) {
            int numElements = num[j];
            out.println("______________________________\n");
            System.out.println("______________________________\n");
            out.println("For Total Nodes: "+numElements);
            System.out.println("For Total Nodes: "+numElements);
            int[] a = new int[numElements];
            int[] b = new int[numElements];
            int[] c = new int[numElements];

            for (int i = 0; i < numElements; i++) {
                a[i] = (int) (Math.random() * 1e9);
                b[i] = a[i];
                c[i] = a[i];
            }

            case1(a);
            case2(b);
            case3(c);
        }
        System.out.println("\nCheck out the output.txt in the first README Folder");
    }
}