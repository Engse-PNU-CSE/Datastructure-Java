package datastructure.ch02;

public class Homework2_1 {
	public static void main(String[] args) {
		String []data = {"apple","grape","persimmon", "pear","blueberry", "strawberry", "melon", "oriental melon"};
		System.out.println(data[0].compareTo(data[8]));
		showData(data);
		sortData(data);
		showData(data);
	}
	static void showData(String[]arr) {
		for(String i: arr) System.out.print(i + " ");
		System.out.println();
	}

	static void swap(String[]arr, int ind1, int ind2) {
		String temp = arr[ind1];
		arr[ind1] = arr[ind2];
		arr[ind2] = temp;
	}
	static void sortData(String []arr) {
		for(int i = 0 ; i < arr.length; i++) {
			for(int j = i+1; j < arr.length; j++) {
				if(arr[i].compareTo(arr[j]) > 0) {
					swap(arr, i, j);
				}
			}
		}
	}
}
