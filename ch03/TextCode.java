package datastructure.ch03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class TextCode {
	public static void main(String[] args) {
		String[] str = {"apple", "banana", "Char", "apple"};
		List<String> list = new ArrayList<>(Arrays.asList(str));
		List<String> list2 = new ArrayList<>();
		Iterator<String> iter = list.iterator();
		while(iter.hasNext()) {
			String s = iter.next();
			if(list2.contains(s)) continue;
			else list2.add(s);
		}
		System.out.println(list2);
	}
	
}
