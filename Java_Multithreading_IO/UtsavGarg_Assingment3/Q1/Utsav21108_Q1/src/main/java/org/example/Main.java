package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.Math;
import java.util.ArrayList;

public class Main {
    static long timeStart;
    static long timeFinish;
    public static void main(String[] args) throws IOException {
        PrintWriter out = null;
        out = new PrintWriter(new FileWriter("output.txt"));
        int[] num = new int[5];
        num[0] = 1; num[1] = 10; num[2] = 100; num[3] = 1000; num[4] = 10000;

        for(int j = 0; j<5; j++){
            int numElements = num[j];
            out.println("____________________________________\n");
            System.out.println("____________________________________\n");
            out.println("For number of students: "+numElements+"\n");
            System.out.println("For number of students: "+numElements+"\n");


            ArrayList<Double> cgArr1 = new ArrayList<Double>();
            ArrayList<Double> cgArr2 = new ArrayList<Double>();

            for(int i=0; i<numElements; i++){
                double temp = Math.random()*10;
                cgArr1.add(temp);
                cgArr2.add(temp);
            }

            timeStart = System.currentTimeMillis();
            BrickSort brickArr = new BrickSort(cgArr1,numElements,0);
            brickArr.brickSort();
            timeFinish = System.currentTimeMillis();
            out.println("0 Threads sorting time: "+(timeFinish-timeStart)+" ms");
            System.out.println("0 Threads sorting time: "+(timeFinish-timeStart)+" ms");

            timeStart = System.currentTimeMillis();
            ThreadSort threadArr = new ThreadSort(cgArr2,numElements,0);
            threadArr.threadSort();
            timeFinish = System.currentTimeMillis();
            out.println("2 Threads sorting time: "+(timeFinish-timeStart)+" ms");
            System.out.println("2 Threads sorting time: "+(timeFinish-timeStart)+" ms");
            out.flush();
        }
        out.close();
        System.out.println("\nCheck out the output.txt in the README folder");
    }
}