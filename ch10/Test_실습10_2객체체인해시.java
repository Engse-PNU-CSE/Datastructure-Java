package datastructure.ch10;


import java.util.Comparator;
//hash node가 student 객체일 때를 구현하는 과제
//체인법에 의한 해시
import java.util.Scanner;


//체인법에 의한 해시

class SimpleObject5 {
	static final int NO = 1;
	static final int NAME = 2;
	int no; // 회원번호
	String name; // 이름
	static final Scanner sc = new Scanner(System.in);

	// --- 문자열 표현을 반환 ---//
	@Override
	public String toString() {
		return "(" + no + ") " + name;
	}

	public SimpleObject5() {
		no = -1;
		name = null;
	}

	// --- 데이터를 읽어 들임 ---//
	void scanData(String guide, int sw) {
		System.out.println(guide + "할 데이터를 입력하세요." + sw);

		if ((sw & NO) == NO) { // & 는 bit 연산자임
			System.out.print("번호: ");
			no = sc.nextInt();
		}
		if ((sw & NAME) == NAME) {
			System.out.print("이름: ");
			name = sc.next();
		}
	}

	// --- 회원번호로 순서를 매기는 comparator ---//
	public static final Comparator<SimpleObject5> NO_ORDER = new NoOrderComparator();

	private static class NoOrderComparator implements Comparator<SimpleObject5> {
		@Override
		public int compare(SimpleObject5 d1, SimpleObject5 d2) {
			return (d1.no > d2.no) ? 1 : (d1.no < d2.no) ? -1 : 0;
		}
	}

	// --- 이름으로 순서를 매기는 comparator ---//
	public static final Comparator<SimpleObject5> NAME_ORDER = new NameOrderComparator();

	private static class NameOrderComparator implements Comparator<SimpleObject5> {
		@Override
		public int compare(SimpleObject5 d1, SimpleObject5 d2) {
			return d1.name.compareTo(d2.name);
		}
	}


	
}

class ChainHash5 {
//--- 해시를 구성하는 노드 ---//
	class Node2 {
		private SimpleObject5 data; // 키값
		private Node2 next; // 뒤쪽 포인터(뒤쪽 노드에 대한 참조)
		// --- 생성자(constructor) ---//
		public Node2(SimpleObject5 data) {
			this.data = data;
			next = null;
		}
	}

	private int size; // 해시 테이블의 크기
	private Node2[] table; // 해시 테이블

//--- 생성자(constructor) ---//
	public ChainHash5(int capacity) {
		if(capacity < 0) {
			capacity = 0;
		}
		size = capacity;
		table = new Node2[size];
	}
	
	public int getIndex(SimpleObject5 st) {
		return st.no * st.no % size;
	}

//--- 키값이 key인 요소를 검색(데이터를 반환) ---//
	public boolean search(SimpleObject5 st, Comparator<? super SimpleObject5> c) {
		int search_index = getIndex(st);
		Node2 current_node = table[search_index];
		while(current_node != null) {
			if(c.compare(st, current_node.data) == 0) {
				System.out.println("Find Data : " + current_node.data.toString());
				return true;
			}
			current_node = current_node.next;
		}
		return false;
	}

//--- 키값이 key인 데이터를 data의 요소로 추가 ---//
	public boolean add(SimpleObject5 st, Comparator<? super SimpleObject5> c) {
		int search_index = getIndex(st);
		if(table[search_index] == null) {
			table[search_index] = new Node2(st);
			return true;
		}
		else if(c.compare(st, table[search_index].data) < 0) {
			Node2 tmp = new Node2(st);
			tmp.next = table[search_index];
			table[search_index] = tmp;
			return true;
		}
		Node2 current_node = table[search_index], past_node = null;
		while(current_node != null) {
			if(c.compare(current_node.data, st) == 0) {
				System.out.println("Duplicated Data : " + st.toString());
				return false;
			}
			else if(c.compare(current_node.data, st) > 0) {
				Node2 tmp = new Node2(st);
				past_node.next = tmp;
				tmp.next = current_node;
				return true;
			}
			past_node = current_node;
			current_node = current_node.next;
		}
		past_node.next = new Node2(st);
		return true;
	}

//--- 키값이 key인 요소를 삭제 ---//
	public boolean delete(SimpleObject5 st, Comparator<? super SimpleObject5> c) {
		int search_index = getIndex(st);
		if(table[search_index] == null) {
			return false;
		}
		else if(c.compare(st, table[search_index].data) == 0) {
			System.out.println("Deleted data : " + table[search_index].data.toString());
			table[search_index] = table[search_index].next;
			return true;
		}
		Node2 current_node = table[search_index], past_node = null;
		while(current_node != null) {
			if(c.compare(current_node.data, st) == 0) {
				System.out.println("Deleted data : " + st.toString());
				past_node.next = current_node.next;
				current_node.next = null;
				return true;
			}
			past_node = current_node;
			current_node = current_node.next;
		}
		return false;
	}

//--- 해시 테이블을 덤프(dump) ---//
	public void dump() {
		for(int i = 0; i < size; i++) {
			System.out.printf("Index %d : ", i);
			Node2 bucket = table[i];
			if(bucket == null) {
				 System.out.println();
				 continue;
			 }
			 while(bucket.next != null) {
				System.out.print(bucket.data.toString() + " -> ");
				bucket = bucket.next;
			 }
			 System.out.println(bucket.data.toString());
		}
	}
}

public class Test_실습10_2객체체인해시 {
	enum Menu {
		Add("삽입"), Delete("삭제"), Search("검색"), Show("출력"), Exit("종료");

		private final String message; // 표시할 문자열

		static Menu MenuAt(int idx) { // 순서가 idx번째인 열거를 반환
			for (Menu m : Menu.values())
				if (m.ordinal() == idx)
					return m;
			return null;
		}

		Menu(String string) { // 생성자(constructor)
			message = string;
		}

		String getMessage() { // 표시할 문자열을 반환
			return message;
		}
	}
		// --- 메뉴 선택 ---//
		static Menu SelectMenu() {
			Scanner sc = new Scanner(System.in);
			int key;
			do {
				for (Menu m : Menu.values()) {
					System.out.printf("(%d) %s  ", m.ordinal(), m.getMessage());
					if ((m.ordinal() % 3) == 2 && m.ordinal() != Menu.Exit.ordinal())
						System.out.println();
				}
				System.out.print(" : ");
				key = sc.nextInt();
			} while (key < Menu.Add.ordinal() || key > Menu.Exit.ordinal());
			return Menu.MenuAt(key);
		}


	public static void main(String[] args) {
		Menu menu;
		Scanner stdIn = new Scanner(System.in);
		ChainHash5 hash = new ChainHash5(15);
		SimpleObject5 data;
		int select = 0;
		boolean result;
		do {
			switch (menu = SelectMenu()) {
			case Add:
				data = new SimpleObject5();
				data.scanData("삽입", SimpleObject5.NO | SimpleObject5.NAME);
				result = hash.add(data, SimpleObject5.NO_ORDER);
				if (!result)
					System.out.println(" 중복 데이터가 존재한다");
				else
					System.out.println(" 입력 처리됨");
				break;
			case Delete:
				// Delete
				data = new SimpleObject5();
				data.scanData("삭제", SimpleObject5.NO);
				result = hash.delete(data, SimpleObject5.NO_ORDER);
				if (result)
					System.out.println(" 삭제 처리");
				else
					System.out.println(" 삭제 데이터가 없음");
				break;
			case Search:
				data = new SimpleObject5();
				data.scanData("검색", SimpleObject5.NO);
				result = hash.search(data, SimpleObject5.NO_ORDER);
				if (result)
					System.out.println(" 검색 데이터가 존재한다");
				else
					System.out.println(" 검색 데이터가 없음");
				break;
			case Show:
				hash.dump();
				break;
			}
		} while (menu != Menu.Exit);
	}
}
