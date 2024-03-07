package datastructure.ch03;

import java.util.ArrayList;
//3장 - 1번 실습 과제 > 2번 실습: 스트링 객체의 정렬과 이진 탐색 > 3번 실습: 객체 정렬과 이진 탐색
//comparator 구현 실습
/*
* 함수(메소드)에 parameter 전달 방식을 표현하는 능력의 숙달 훈련
* 함수(메소드) 전체를 작성하는 훈련 
*/
import java.util.Arrays;
import java.util.List;
import java.util.Random;
public class train3_4IntegerArrayBinarySearch {

	public static void main(String[] args) {
		int []data = new int[10];
		inputData(data);// 구현 반복 숙달 훈련

		showList("정렬 전 배열[]:: ", data);
		sortData(data);// 구현 반복 숙달 훈련
		//Arrays.sort(data);
		showList("정렬 후 배열[]:: ", data);// 구현 반복 숙달 훈련

		int key = 13;
		int resultIndex = linearSearch(data, key);//교재 99-100:실습 3-1 참조, 교재 102: 실습 3-2
		//Arrays 클래스에 linear search는 없다
		System.out.println("\nlinearSearch(13): result = " + resultIndex);

		key = 19;
		/*
		 * 교재 109~113
		 */
		resultIndex = binarySearch(data, key);
		System.out.println("\nbinarySearch(19): result = " + resultIndex);
		
		key = 10;
		/*
		 * 교재 115 Arrays.binarySearch에 의한 검색
		 */
		resultIndex = Arrays.binarySearch(data, key);
		System.out.println("\nArrays.binarySearch(10): result = " + resultIndex);

	}
	static void showList(String msg, int[] arr) {
		System.out.println(msg);
		for(int i: arr) System.out.print(i + " ");
		System.out.println();
	}
	
	static void inputData(int[] arr) {
		Random rand = new Random();
		for(int i = 0 ; i < arr.length; i++) arr[i]=rand.nextInt(30)+1;
	}
	static void sortData(int[] arr) {
		for(int i = 0; i < arr.length; i++) {
			for(int j = i+1; j < arr.length; j++) {
				if(arr[i] > arr[j]) {
					int temp = arr[j];
					arr[j] = arr[i];
					arr[i] = temp;
				}
			}
		}
	}
	static int linearSearch(int[]item, int key) {
		for(int i = 0; i < item.length; i++) {
			if(item[i] == key) return i;
		}
		return -1;
	}

	static int binarySearch(int[]item, int key) {
		int pl = 0;
		int pr = item.length-1;
		
		while(pr>=pl) {
			int pm = (pl + pr) / 2;
			if(key == item[pm]) return pm;
			if(key > item[pm]) pl = pm + 1;
			else if(key < item[pm]) pr = pm - 1;
		}
		return -1;
	}
}
