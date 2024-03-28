
package datastructure.ch08;
/*
 * 정수 리스트 > 객체 리스트 >
 */
import java.util.Comparator;
import java.util.Scanner;

class SimpleObject2 {
	static final Scanner sc = new Scanner(System.in);
	static final int NO = 1; // 번호를 읽어 들일까요?
	static final int NAME = 2; // 이름을 읽어 들일까요?
	int no; // 회원번호
	String name; // 이름

	public SimpleObject2(int sno, String sname) {
		this.no = sno;
		this.name = sname;
	}
	public SimpleObject2() {
		this.no = -1;
		this.name = null;
	}
	// --- 문자열 표현을 반환 ---//
	@Override
	public String toString() {
		return "(" + no + ") " + name;
	}
	// --- 데이터를 읽어 들임 ---//
	void scanData(String guide, int sw) {
		System.out.println(guide + "할 데이터를 입력하세요."+ sw);

		if ((sw & NO) == NO) { //& 는 bit 연산자임
			System.out.print("번호: ");
			no = sc.nextInt();
		}
		if ((sw & NAME) == NAME) {
			System.out.print("이름: ");
			name = sc.next();
		}
	}
	// --- 회원번호로 순서를 매기는 comparator ---//
	public static final Comparator<SimpleObject2> NO_ORDER = new NoOrderComparator();

	private static class NoOrderComparator implements Comparator<SimpleObject2> {
		@Override
		public int compare(SimpleObject2 d1, SimpleObject2 d2) {
			return (d1.no > d2.no) ? 1 : (d1.no < d2.no) ? -1 : 0;
		}
	}

	// --- 이름으로 순서를 매기는 comparator ---//
	public static final Comparator<SimpleObject2> NAME_ORDER = new NameOrderComparator();

	private static class NameOrderComparator implements Comparator<SimpleObject2> {
		@Override
		public int compare(SimpleObject2 d1, SimpleObject2 d2) {
			return (d1.name.compareTo(d2.name) > 0) ? 1 : ((d1.name.compareTo(d2.name) < 0)) ? -1 : 0;
		}
	}
}

class Node4 {
	SimpleObject2 data; // 데이터
	Node4 pre_link; // 좌측포인터(앞쪽 노드에 대한 참조)
	Node4 nxt_link; // 우측포인터(뒤쪽 노드에 대한 참조)

	// --- 생성자(constructor) ---//
	Node4(SimpleObject2 so) {
		this.data = so;
		pre_link = nxt_link = this;
	}
	Node4() { //head node로 사용
		this.data = null;
		pre_link = nxt_link = this;
	}
	Node4(int sno, String sname) {
		data = new SimpleObject2(sno, sname);
		pre_link = nxt_link = this;
	}
	public int compareNode(Node4 n2) {
		if (SimpleObject2.NO_ORDER.compare(this.data, n2.data) < 0) return -1;
		else if (SimpleObject2.NO_ORDER.compare(this.data, n2.data) > 0)return 1;
		else return 0;
	}
}

class DoubledLinkedList2 {
	private Node4 first; // 머리 포인터(참조하는 곳은 더미노드)

// --- 생성자(constructor) ---//
	public DoubledLinkedList2() {
		first = new Node4(); // dummy(first) 노드를 생성

	}

// --- 리스트가 비어있는가? ---//
	public boolean isEmpty() {
		return first.nxt_link == first;
	}

// --- 노드를 검색 ---//
	public boolean search(SimpleObject2 obj, Comparator<? super SimpleObject2> c) {
		Node4 current_node = first;
		while(current_node.nxt_link != first) {
			if(c.compare(current_node.nxt_link.data, obj) == 0) {
				return true;
			}
			current_node = current_node.nxt_link;
		}
		return false;
	}

// --- 전체 노드 표시 ---//
	public void show() {
		Node4 current_node = first;
		if(isEmpty()) {
			System.out.println("Data is not exits");
			return;
		}
		System.out.print("Current data : ");
		while(current_node.nxt_link != first) {
			current_node = current_node.nxt_link;
			if(current_node.nxt_link == first) {
				System.out.println(current_node.data.toString());
				return;
			}
			System.out.print(current_node.data.toString() + " -> ");
		}
	}

// --- 올림차순으로 정렬이 되도록 insert ---//
	public void add(SimpleObject2 obj, Comparator<? super SimpleObject2> c) {
		Node4 tmp = new Node4(obj);
		Node4 current_node = first;
		if(current_node.nxt_link == first) {
			first.nxt_link = first.pre_link = tmp;
			tmp.nxt_link = tmp.pre_link = first;
			return;
		}
		while(current_node.nxt_link != first) {
				if(c.compare(obj, current_node.nxt_link.data) < 0) {
					tmp.pre_link = current_node;
					tmp.nxt_link = current_node.nxt_link;
					current_node.nxt_link = tmp;
					current_node.nxt_link.pre_link = tmp;
					return;
				}
				current_node = current_node.nxt_link;
		}
		
		if(current_node.nxt_link == first) { 
		    tmp.pre_link = current_node;
		    tmp.nxt_link = first; 
		    current_node.nxt_link = tmp; 
		    first.pre_link = tmp; 
		}
		
	}

// --- list에 삭제할 데이터가 있으면 해당 노드를 삭제 ---//
	public boolean delete(SimpleObject2 obj, Comparator<? super SimpleObject2> c) {
		Node4 current_node = first;
		while(current_node.nxt_link != first) {
			if(c.compare(current_node.nxt_link.data, obj) == 0) {
				System.out.println("Delete Data : " + current_node.nxt_link.data);
				current_node.nxt_link = current_node.nxt_link.nxt_link;
				current_node.nxt_link.nxt_link.pre_link = current_node;
				return true;
			}
			current_node = current_node.nxt_link;
		}
		return false;
	}
	public DoubledLinkedList2 merge(DoubledLinkedList2 lst2) {
		Node4 current_lst1 = first.nxt_link, current_lst2 = lst2.first.nxt_link;
		while(current_lst1 != first) {
			Node4 tmp_lst1_nxtlink = current_lst1.nxt_link;
			while(current_lst2 != lst2.first) {
				if(current_lst1.compareNode(current_lst2) < 0) break;
				current_lst2=current_lst2.nxt_link;
			}
			current_lst1.pre_link.nxt_link = tmp_lst1_nxtlink;
			tmp_lst1_nxtlink.pre_link = current_lst1.pre_link;
			
			current_lst1.pre_link = current_lst2.pre_link;
			current_lst2.pre_link.nxt_link = current_lst1;
			
			current_lst2.pre_link = current_lst1;
			current_lst1.nxt_link = current_lst2;
			
			current_lst1 = tmp_lst1_nxtlink;
		}
		return lst2;
	}
	
}

public class Test8_3객체이중리스트_test {
	static final Scanner sc = new Scanner(System.in);
	enum Menu {
		Add("삽입"), Delete("삭제"), Show("인쇄"), Search("검색"), Merge("병합"), Exit("종료");

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
	static Menu SelectMenu() {;
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
		Menu menu; // 메뉴
		System.out.println("Linked List");
		DoubledLinkedList2 lst1 = new DoubledLinkedList2(), lst2 = new DoubledLinkedList2(),lst3 = new DoubledLinkedList2();
		SimpleObject2 so;
		boolean result = false;
		do {
			System.out.println();
			lst1.show();
			switch (menu = SelectMenu()) {
			case Add: // 머리노드 삽입
				so =  new SimpleObject2();
				so.scanData("입력", 3);
				lst1.add(so, SimpleObject2.NO_ORDER);
				break;
			case Delete: // 머리 노드 삭제
				so =  new SimpleObject2();
				so.scanData("삭제", SimpleObject2.NO);
				result = lst1.delete(so, SimpleObject2.NO_ORDER);
				if(!result) System.out.println("데이터가 존재하지 않습니다.");
				break;
			case Show: // 꼬리 노드 삭제
				lst1.show();
				break;
			case Search: // 회원 번호 검색
				so =  new SimpleObject2();
				so.scanData("탐색", SimpleObject2.NO);
				result = lst1.search(so, SimpleObject2.NO_ORDER);
				if (!result)
					System.out.println("검색 값 = " + so.toString() + " : 데이터가 없습니다.");
				else
					System.out.println("검색 값 = " + so.toString() + " : 데이터가 존재합니다.");
				break;
			case Merge:
				for (int i = 0; i < 3; i++) {
					so =  new SimpleObject2();
					so.scanData("입력", 3);
					lst2.add(so, SimpleObject2.NO_ORDER);
				}
				 System.out.println("list1: ");
				 lst1.show();
				 System.out.println("list2: ");
				 lst2.show();
				 System.out.println("list3 = list1 + list2");
				 lst3 = lst1.merge(lst2);
				 lst3.show();
				break;
			case Exit: // 꼬리 노드 삭제
				break;
			}
		} while (menu != Menu.Exit);
	}
}
