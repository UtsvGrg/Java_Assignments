Open the project in Intellij, as I created the project in that
IMPORTANT --> that the output.txt file is generated in this folder and not the src folder (When running the main file)


Also, to build the file: Go to view > tool windows > maven > select your file name > lifecycle > test and it should build successfully
_____________________________________________________________________________________________________________________

Further README

Here firstly, I randomly generated a Double type array
and stored it in cgArr1 and its copy in cgArr2

This randomly generated arrays are then sorted using the function 
bricksort and threadsort respectively, both working on the same principal
odd-even sort. The main difference being usage of threads in the threadsort.

The basic role of using threads is to parallelize the work, allowing the code 
to finish quickly, although there is a small drawback when using threads 
that is context switch, that saving the data before switching between threads, which is 
quite prevalent, when we are working for small numbers, or using threads for a minute task

Here my code on running, prints the time taken to sort an array of lenght 1, 10,100,1000 and 10000
When using threads, I'm using the threads to swap between the odd layer and even layer.
When swapping the odd or even, I have divided the arr in two parts, and by using 2 threads
I am simultaneously swapping in the array

Although the time taken to run the code when using thread should be less, but
because swapping is such a minute and light task, that the process used for thread creating
and thread swapping takes more time than the bare number swapping.

I have used System.currentTimeMillis() to calculate the time elapsed between a function.

Multithreading - In class ThreadSort
File IO - In class Main, for using printWriter to write the output in the file
Generics - The integer and Double arrays in the classes, also main usage is in question2

Thank you
Utsav Garg
2021108