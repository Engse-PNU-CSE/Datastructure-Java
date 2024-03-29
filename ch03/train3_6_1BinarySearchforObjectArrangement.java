package datastructure.ch03;

/*
 * Comparable 구현
 */
/*
 * 함수(메소드)에 parameter 전달 방식을 표현하는 능력의 숙달 훈련
 * 함수(메소드) 전체를 작성하는 훈련 
 */
import java.util.Arrays;

//5번 실습 - 2장 실습 2-14를 수정하여 객체 배열의 정렬 구현
class PhyscData2 implements Comparable<PhyscData2>{
	String name;
	int height;
	double vision;

	public PhyscData2(String name, int height, double vision) {
		super();
		this.name = name;
		this.height = height;
		this.vision = vision;
	}
	@Override
	public String toString() {
		return "PhyscData2 [name=" + name + ", height=" + height + ", vision=" + vision + "]";
	}
	@Override
	public int compareTo(PhyscData2 p) {
	    if (this == p) return 0;
	    
	    // 이름 비교
	    int nameCompare = this.name.compareTo(p.name);
	    if (nameCompare != 0) return nameCompare;

	    // 키 비교
	    if (this.height > p.height) return 1;
	    else if (this.height < p.height) return -1;

	    // 시력 비교
	    return Double.compare(this.vision, p.vision);
	}
	public int equals(PhyscData2 p) {
		return -1;
	}
}
public class train3_6_1BinarySearchforObjectArrangement {

	static void showData(String msg, PhyscData2[] p) {
		for(PhyscData2 person : p) {
			person.toString();
		}
	}
	static void sortData(PhyscData2[] data) {
		for(int i = 0; i < data.length; i++) {
			for(int j = i + 1; j < data.length; j++) {
				if(data[i].compareTo(data[j]) > 0) {
					swap(data, i, j);
				}
			}
		}
	}
	static void swap(PhyscData2[] arr, int ind1, int ind2) {
		PhyscData2 temp = arr[ind1];
		arr[ind1] = arr[ind2];
		arr[ind2] = temp;
	}
	static int linearSearch(PhyscData2[] data, PhyscData2 key) {
		return -1;
	}
	static int binarySearch(PhyscData2[] data, PhyscData2 key) {
		return -1;
	}
	static void reverse(PhyscData2[] data) {
		
	}
	public static void main(String[] args) {
		PhyscData2[] data = {
				new PhyscData2("홍길동", 162, 0.3),
				new PhyscData2("나동", 164, 1.3),
				new PhyscData2("최길", 152, 0.7),
				new PhyscData2("김홍길동", 172, 0.3),
				new PhyscData2("박동", 182, 0.6),
				new PhyscData2("이동", 167, 0.2),
				new PhyscData2("길동", 167, 0.5),
		};
		showData("정렬전", data);
		sortData(data);
		showData("정렬후", data);
		reverse(data);
		showData("역순 재배치후", data);
		Arrays.sort(data);//사용된다 그 이유는?
		showData("Arrays.sort() 정렬후", data);
		
		PhyscData2 key = new PhyscData2("길동", 167, 0.2);
		int resultIndex = linearSearch(data, key);
		System.out.println("\nlinearSearch(<길동,167,02>): result = " + resultIndex);
		
		key = new PhyscData2("박동", 182, 0.6);
		/*
		 * 교재 109~113
		 */
		resultIndex = binarySearch(data, key);//comparable를 사용
		System.out.println("\nbinarySearch(<박동,182,0.6>): result = " + resultIndex);
		key = new PhyscData2("이동", 167, 0.5);
		/*
		 * 교재 115 Arrays.binarySearch에 의한 검색	
		 * 
		 */
		resultIndex = Arrays.binarySearch(data, key);//comparable를 사용
		System.out.println("\nArrays.binarySearch(<이동,167,0.5>): result = " + resultIndex);
	}
	
	

}
