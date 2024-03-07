package datastructure.ch02;

import java.util.Arrays;
import java.util.Comparator;

//5번 실습 - 2장 실습 2-14를 수정하여 객체 배열의 정렬 구현
class PhyscData implements Comparable<PhyscData>{
	String name;
	int height;
	double vision;
	public PhyscData(String name, int height, double vision) {
		super();
		this.name = name;
		this.height = height;
		this.vision = vision;
	}
	@Override
	public String toString() { //toString() override
		return "[name = " + name + ", height = " + height + ", vision = " + vision + "]";
	}
	@Override
	public int compareTo(PhyscData p) {
		return name.compareTo(p.name);
	}
	public int equals(PhyscData p) {
		if(name == p.name && height == p.height && vision == p.vision) return 1;
		return 0;
	}
}
public class Homework2_2 {
	static void swap(PhyscData[]arr, int ind1, int ind2) {
		PhyscData temp = arr[ind1];
		arr[ind1] = arr[ind2];
		arr[ind2] = temp;
	}
	static void sortData(PhyscData []arr) {
		for(int i = 0 ; i < arr.length; i++) {
			for(int j = i+1; j < arr.length; j++) {
				if(arr[i].compareTo(arr[j]) > 0) {
					swap(arr, i, j);
				}
			}
		}
	}
	static void searchData(PhyscData []arr, PhyscData name) {
		for(int i = 0 ; i < arr.length; i++) {
			if(arr[i].equals(name) == 1) {
				name.toString();
			}
		}
	}
	public static void main(String[] args) {
		PhyscData[] data = {
				new PhyscData("홍길동", 162, 0.3),
				new PhyscData("홍동", 164, 1.3),
				new PhyscData("홍길", 152, 0.7),
				new PhyscData("김홍길동", 172, 0.3),
				new PhyscData("이길동", 182, 0.6),
				new PhyscData("박길동", 167, 0.2),
				new PhyscData("최길동", 169, 0.5),
		};
		System.out.println("default data");
		showData(data);
		System.out.println("sorted[comparator : name] data");
		sortData(data);
		showData(data);
		System.out.println("Arrays.sort comparator = [height] : data");
		Arrays.sort(data , new Comparator <PhyscData>() {
			public int compare (PhyscData p1, PhyscData p2) {
				int p1height = p1.height;
				int p2height = p2.height;
				return Integer.compare(p1height, p2height);
			}
		});//comparator가 필요하다 
		showData(data);
		System.out.println("Arrays.sort comparator = [vision] : data");
		Arrays.sort(data , new Comparator <PhyscData>() {
			public int compare (PhyscData p1, PhyscData p2) {
				double p1vision = p1.vision;
				double p2vision = p2.vision;
				return Double.compare(p1vision, p2vision);
			}
		});//comparator가 필요하다 
		showData(data);
	}
	static void showData(PhyscData[]arr) {
		for(int i = 0; i < arr.length; i++) System.out.println(arr[i].toString());
		System.out.println();
	}

}
