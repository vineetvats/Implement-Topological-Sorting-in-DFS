---------------------------- READ ME --------------------------

This is a readme file containing instructions for executing code for 

Short Project 8: Implementing DFS based Topological Ordering algorithm

Team No: 33

@Authors:
Vineet Vats: vxv180008
Yash Pradhan: ypp170130




Instructions to execute code:

The uploaded folder with name as my net id: ypp170130 contains java file "DFS.java", which also contains driver stub in main method


NOTE: while executing from command prompt, the pwd should be the directory containing the directory ypp170130

Steps for running code from the cmd prompt

1. Compile the "DFS.java" by executing the following command
> javac ypp170130/DFS.java

2. Run Driver stub by invoking main method
> java ypp170130/DFS


----------------------------- SAMPLE RUN --------------------------

Sample Input: 10 12  1 3 1  3 2 1  2 4 1  4 7 1  1 8 1  8 5 1  8 2 1  5 4 1  6 8 1  6 10 1  5 10 1  10 9 1 0

Sample Output:
______________________________________________
Graph: n: 10, m: 12, directed: true, Edge weights: false
1 :  (1,3) (1,8)
2 :  (2,4)
3 :  (3,2)
4 :  (4,7)
5 :  (5,4) (5,10)
6 :  (6,8) (6,10)
7 : 
8 :  (8,5) (8,2)
9 : 
10 :  (10,9)
______________________________________________

Topological Ordering: 6 1 8 5 10 9 3 2 4 7 