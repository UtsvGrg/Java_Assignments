Open the project in Intellij, as I created the project in that
IMPORTANT --> that the output.txt file is generated in this folder and not the src folder


Also, to build the file: Go to view > tool windows > maven > select your file name > lifecycle > test and it should build successfully
________________________________________________________________________________________________________________________

Further README

Similar to the readme in q1, you must know how does thread work.
The main difference is the usage of System.nanoTime() instead of 
System.currentMillisTime() because few of the functions required very less
time and therefore for comparison it was necessary to use nanoseconds, otherwise it was showing 0 ms.
 
I have made three case, one with 0 threads, one with 2 threads and one with 4
threads. Basically I have used the java collection framework to create the tree,
which requires a sorted arr, or else it does that internally. Here using the 4 threads
I have mergeSorted the array, by diving it into 4 different paths, simultaneously sorting
them and finally merging them for the tree creation.

I have made sure to take precautions and joined all the threads in the try catch block
with InterruptedException, allowing fully reproducible code.

As we expect, the time taken in tree creation with more threads is less, as the work gets
distributed in 4 different areas, and as sorting is a heavy task, the context switch in 
comparison can be neglected, therefore providing appropriate result.

Multithreading - In class Main, used by implementing Runner in ThreadSort
File IO - In class Main, for using printWriter and writing in the file output.txt
Generics - The treeset java collection framework used is generics type, also the integer and Double arrays in the classes

Thank you
Utsav Garg
2021108