package datastructure.ch03;

import java.util.Arrays;
import java.util.Comparator;

//5번 실습 - 2장 실습 2-14를 수정하여 객체 배열의 정렬 구현
class PhyscData3 {
	String name;
	int height;
	double vision;
	public PhyscData3(String name, int height, double vision) {
		this.name = name;
		this.height = height;
		this.vision = vision;
	}
	@Override
	public String toString() {
		return "[name=" + name + ", height=" + height + ", vision=" + vision + "]";
	}

}
class NameOrderComparator2 implements Comparator<PhyscData3>{

	@Override
	public int compare(PhyscData3 p1, PhyscData3 p2) {
		//p1과 p2를 비교함
		if(p1.name.compareTo(p2.name) > 0) return 1;
		else if(p1.name.compareTo(p2.name) < 0) return -1;
		return 0;
	}

}
class VisionOrderComparator2 implements Comparator<PhyscData3>{

	@Override
	public int compare(PhyscData3 p1, PhyscData3 p2) {
		//p1과 p2를 비교함
		if(p1.vision > p2.vision) return 1;
		else if(p1.vision< p2.vision) return -1;
		return 0;
	}

}
class HeightOrderComparator2 implements Comparator<PhyscData3>{

	@Override
	public int compare(PhyscData3 p1, PhyscData3 p2) {
		//p1과 p2를 비교함
		if(p1.height > p2.height) return 1;
		else if(p1.height < p2.height) return -1;
		return 0;
	}

}
public class Train3_8ObjectComparisonOperator {	
	static final Comparator<PhyscData3> HEIGHT_ORDER = new HeightOrderComparator2();
	static final Comparator<PhyscData3> VISION_ORDER = new HeightOrderComparator2();
	static final Comparator<PhyscData3> NAME_ORDER = new HeightOrderComparator2();
	static void showData(String msg, PhyscData3[] p) {
		System.out.println(msg);
		for(PhyscData3 person : p) {
			System.out.println(person.toString());
		}
	}
	public static void main(String[] args) {
		PhyscData3[] data = {
				new PhyscData3("홍길동", 162, 0.3),
				new PhyscData3("홍동", 164, 1.3),
				new PhyscData3("홍길", 152, 0.7),
				new PhyscData3("김홍길동", 172, 0.3),
				new PhyscData3("길동", 182, 0.6),
				new PhyscData3("길동", 167, 0.2),
				new PhyscData3("길동", 167, 0.5),
		};
		showData("정렬전 객체 배열", data);
		Arrays.sort(data, HEIGHT_ORDER);
		
		showData("정렬후 객체 배열 :: HEIGHT_ORDER", data);
		Arrays.sort(data, VISION_ORDER);
		
		showData("정렬후 객체 배열 :: VISION_ORDER", data);
		Arrays.sort(data, NAME_ORDER);
		
		showData("정렬후 객체 배열 :: NAME_ORDER", data);
		PhyscData3 key = new PhyscData3("길동", 167, 0.2);
		
		int idx = Arrays.binarySearch(data, key, HEIGHT_ORDER);
		System.out.println("\nArrays.binarySearch(): result = " + idx);
	}

}
