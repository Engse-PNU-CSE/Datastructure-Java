package datastructure.ch02;

import java.util.Random;
public class Train2_4 {

	public static void main(String[] args) {
		int []data = new int[10];
		inputData(data);
		showData(data);
		int max = findMax(data);
		System.out.println("\nmax = " + max);
		boolean existValue = findValue(data, 3);
		System.out.println("찾는 값 = " + 3 + ", 존재여부 = " + existValue);
	}
	static void showData(int[]data) {
		for (int num: data) {
			System.out.print(num+" ");
		}
		System.out.println();
	}
	public static void inputData(int []data) {//교재 63 - 난수의 생성
		Random rand = new Random();
		for(int i = 0 ; i < data.length; i++) data[i] = rand.nextInt(10)+1;
	}
	static int findMax(int []items) {
		int max = -1;
		for(int i : items) if(max < i) max = i;
		return max;
	}
	static boolean findValue(int []items, int value) {
		//items[]에 value 값이 있는지를 찾는 알고리즘 구현
		for(int i : items) if(i == value) {
			return true;
		}
		return false;
	}

}