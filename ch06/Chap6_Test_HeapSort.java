package datastructure.ch06;

import java.util.Random;
import java.util.Scanner;

interface MaxHeap {
	public void Insert(int x);
	public int DeleteMax();
}

class Heap implements MaxHeap {
	final int heapSize = 100;
	private int[] heap;
	private int n; // current size of MaxHeap
	private int MaxSize; // Maximum allowable size of MaxHeap
	
	public int MaxHeapSize( ) {
		return MaxSize;
	}
	
	public Heap(int sz) {
		MaxSize = sz;
		heap = new int[MaxSize+1];
		n = 0;
	}

	public void display() {
		System.out.println("MaxHeap:: (i, heap[i]): ");
		for (int i = 1; i <= n; i++) System.out.print("(" + i + ", " + heap[i] + ") ");
		System.out.println();
	}
	@Override
	public void Insert(int x) {
//		if (n == MaxSize) { 
//			HeapFull();
//			return;
//		}
//		heap[++n] = x;
//		makeHeap();
		int i;
		if (n == MaxSize) { 
			HeapFull();
			return; 
		}
		n++;
		for (i = n; i >= 1; i/=2) {
			if (i == 1) break; // at root
			if (x <= heap[i / 2]) break;
			// move from parent to i
			heap[i] = heap[i / 2];
		}
		heap[i] = x;
	}
	private void downHeap(int parent) {
	    int tmp = heap[parent];
	    int child;
	    for (; parent * 2 <= n; parent = child) {
	        int cl = parent * 2;
	        int cr = cl + 1;
	        child = (cr <= n && heap[cr] > heap[cl]) ? cr : cl;
	        if(tmp >= heap[child]) break;
	        heap[parent] = heap[child];
	    }
	    heap[parent] = tmp;
	}

	private void makeHeap() {
		for(int i = n/2; i >= 1; i--) downHeap(i);
	}
	@Override
	public int DeleteMax() {
		if(n==0) {
			HeapEmpty();
			return -1;
		}
		int x = heap[1];
		heap[1] = heap[n--];
		makeHeap();
		return x;
	}

	private void HeapEmpty() {
		System.out.println("Heap Empty");
	}

	private void HeapFull() {
		System.out.println("Heap Full");
	}
	public int currentSize() {
		return n;
	}
	public void sortHeap(int[] a) {
	    for (int i = 0; i < a.length; i++) {
	    	a[a.length-i-1] = DeleteMax();
	    }
	}

}
public class Chap6_Test_HeapSort {
	 static void showData(int[] d) {
	     for (int i = 0; i < d.length; i++)
	         System.out.print(d[i] + " ");
	     System.out.println();
	 }
	public static void main(String[] args) {
		Random rnd = new Random();
		int select = 0;
		Scanner stdIn = new Scanner(System.in);
		Heap heap = new Heap(20);

		do {
			System.out.println("Max Tree. Select: 1 insert, 2 display, 3 deleteMax, 4 sort, 5 exit => ");
			System.out.println("Current Heap Size : " + heap.currentSize() + "   Maximum Heap Size : " + heap.MaxHeapSize());
			select = stdIn.nextInt();
			switch (select) {
			case 1:
				int n = rnd.nextInt(1, 10);
				System.out.println("Input Value : " + n);
				heap.Insert(n);
				heap.display();
				break;
			case 2:
				heap.display();
				break;
			case 3:
				System.out.println("Delete Max : " + heap.DeleteMax());
				heap.display();
				break;
			case 4:
			    int []sorted = new int[heap.currentSize()];
				System.out.println("Sorted data : ");
				heap.sortHeap(sorted);
				showData(sorted);
				break;

			case 5:
				System.out.println("Exit Program. Good Bye!");
				return;

			}
		} while (select < 5);
		stdIn.close();
		return;
	}
}
