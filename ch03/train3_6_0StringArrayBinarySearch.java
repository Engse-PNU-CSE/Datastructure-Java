package datastructure.ch03;

//comparator 구현 실습
/*
* 교재 121 실습 3-6 
* 함수(메소드)에 parameter 전달 방식을 표현하는 능력의 숙달 훈련
* 함수(메소드) 전체를 작성하는 훈련 
*/
import java.util.Arrays;
public class train3_6_0StringArrayBinarySearch {

	
	static void reverse(String[] a) {//교재 67페이지

	}
	static void showData(String msg, String[] data) {
		System.out.println(msg + " ::");
		for(String value :data) System.out.print(value + " ");
		System.out.println();
	}
	static void sortData(String[] data) {
		for(int i = 0 ; i < data.length; i++) {
			for(int j = i+1; j < data.length; j++) {
				if(data[i].compareTo(data[j]) > 0) {
					String temp = data[j];
					data[j] = data[i]; data[i] = temp;
				}
			}
		}
	}
	static int binarySearch(String[] data, String key) {
		int pl = 0;
		int pr = data.length-1;
		
		while(pr>=pl) {
			int pm = (pl + pr) / 2;
			if(key == data[pm]) return pm;
			else if(key.compareTo(data[pm]) > 0) pl = pm + 1;
			else pr = pm - 1;
		}
		return -1;
	}
	static int linearSearch(String[] data, String key) {
		for(int i = 0; i < data.length; i++) 
			if(data[i] == key) return i;
		
		return -1;
	}
	public static void main(String[] args) {
		String []data = {"사과","포도","복숭아", "감", "산딸기", "블루베리", "대추", "수박", "참외"};//홍봉희 재배 과수

		showData("정렬전", data);
		sortData(data);
		showData("정렬후", data);
		reverse(data);//역순으로 재배치
		showData("역순 재배치후", data);
		Arrays.sort(data);//Arrays.sort(Object[] a);
		showData("Arrays.sort()로 정렬후",data);
      
		String key = "포도";
		int resultIndex = linearSearch(data, key);
		System.out.println("\nlinearSearch(포도): result = " + resultIndex);

		key = "배";
		/*
		 * 교재 109~113
		 */
		resultIndex = binarySearch(data, key);
		System.out.println("\nbinarySearch(배): result = " + resultIndex);
		key = "산딸기";
		/*
		 * 교재 115 Arrays.binarySearch에 의한 검색
		 */
		resultIndex = Arrays.binarySearch(data, key);
		System.out.println("\nArrays.binarySearch(산딸기): result = " + resultIndex);

	}


}
