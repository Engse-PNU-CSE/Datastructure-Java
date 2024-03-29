package datastructure.ch03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class trainSortStringList {

	    public static String[] removeElement1(String[] arr, String item) {
	    	List<String> str1 = new ArrayList<>(Arrays.asList(arr));
	    	int count = Collections.frequency(str1, item);
	    	for(int i = 1; i < count; i++) str1.remove(item);
	    	return str1.toArray(new String[0]);
	    }
	    static int binarySearch(String[]item, String key) {
			int pl = 0;
			int pr = item.length-1;
			
			while(pr>=pl) {
				int pm = (pl + pr) / 2;
				if(key == item[pm]) return pm;
				else if(key.compareTo(item[pm]) > 0) pl = pm + 1;
				else pr = pm - 1;
			}
			return -1;
		}
	    static void swap(String[] arr, int ind1, int ind2) {
			String temp = arr[ind1];
			arr[ind1] = arr[ind2];
			arr[ind2] = temp;
		}
	    
	    static void getList(List<String> list) {
			list.add("서울");	list.add("북경");
			list.add("상해");	list.add("서울");
			list.add("도쿄");	list.add("뉴욕");


			list.add("런던");	list.add("로마");
			list.add("방콕");	list.add("북경");
			list.add("도쿄");	list.add("서울");

			list.add(1, "LA");
	    }
	    static void showList(String topic, List<String> list) {
	    	System.out.println(topic + " ::");
	    	for(String item: list) System.out.print(item + ", ");
	    	System.out.println();
	    }
	    
	    static void sortList(List<String> list) {
	    	Collections.sort(list);
	    }
	    
	    static String[] removeDuplicateList(List<String> list) {
//		    Set<String> set = new HashSet<String>(list);
//		    List<String> list1 = new ArrayList<String>(set);
//		    String[] cities = list1.toArray(new String[0]);
//		    list to set, set to list method
		    String[] cities1 = list.toArray(new String[0]);
		    int count = 0;
		    while(count < cities1.length) {
		    	cities1 = removeElement1(cities1, cities1[count++]);
		    	
		    }
		    return cities1;
	    }
		public static void main(String[] args) {
			ArrayList<String> list = new ArrayList<>();
			getList(list);
			showList("입력후", list);
			//sort - 오름차순으로 정렬, 내림차순으로 정렬, 중복 제거하는 코딩

//		    Collections.sort(list);

//배열의 정렬
			sortList(list);
		    System.out.println();
		    showList("정렬후", list);
// 배열에서 중복제거
		    System.out.println();
		    System.out.println("중복제거::");
		    
		    String[] cities = removeDuplicateList(list);
	        ArrayList<String> lst = new ArrayList<>(Arrays.asList(cities));
		    showList("중복제거후", lst);
		}
	}

