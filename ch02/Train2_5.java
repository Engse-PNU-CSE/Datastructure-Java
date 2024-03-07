package datastructure.ch02;

import java.util.Arrays;
//교재 67 - 실습 2-5
//2번 실습
import java.util.Random;
public class Train2_5 {

	public static void main(String[] args) {
		int []data = new int[10];
		inputData(data);
		showData(data);
		
		sortData(data);
		showData(data);
	
		
		reverse(data);//역순으로 재배치 - 정렬 아님 
		showData(data);

		reverseSort(data);//역순으로 재배치 - 정렬 아님 
		showData(data);
		
		Arrays.sort(data);
	}
	static void showData(int[]data) {
		for(int value:data) System.out.print(value+ " ");
		System.out.println();
	}
	static void inputData(int []data) {//input random integer
		Random rand = new Random();
		for(int i = 0; i < data.length; i++) data[i]=rand.nextInt(10)+1;

	}
	static void swap(int[]arr, int ind1, int ind2) {//교재 67페이지
		int temp = arr[ind1];
		arr[ind1] = arr[ind2];
		arr[ind2] = temp;
	}
	static void sortData(int []arr) {
		for(int i = 0 ; i < arr.length; i++) {
			for(int j = i+1; j < arr.length; j++) {
				if(arr[i] > arr[j]) swap(arr, i, j);
			}
		}

	}
	static void reverse(int[] a) {//교재 67페이지
		for(int i = 0; i < a.length/2; i++) {
			swap(a, i, a.length - i - 1);
		}

	}
	static void reverseSort(int []arr) {
		for(int i = 0 ; i < arr.length; i++) {
			for(int j = i+1; j < arr.length; j++) {
				if(arr[i] < arr[j]) swap(arr, i, j);
			}
		}
	}

}
