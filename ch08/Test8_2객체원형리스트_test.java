package datastructure.ch08;

/*
 * 정수 리스트 > 객체 리스트> 객체 원형 리스트
 */
import java.util.Comparator;
import java.util.Scanner;

class SimpleObject3 {
	static final int NO = 1; // 번호를 읽어 들일까요?
	static final int NAME = 2; // 이름을 읽어 들일까요?

	private int no; // 회원번호
	private String name; // 이름
	

	static final Scanner sc = new Scanner(System.in);

	// --- 문자열 표현을 반환 ---//
	@Override
	public String toString() {
		return "(" + no + ") " + name;
	}

	public SimpleObject3(int no, String name) {
		this.no = no;
		this.name = name;
	}
	public SimpleObject3() {
		this.no = -1;
		this.name = null;
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
	public static final Comparator<SimpleObject3> NO_ORDER = new NoOrderComparator();

	private static class NoOrderComparator implements Comparator<SimpleObject3> {
		@Override
		public int compare(SimpleObject3 d1, SimpleObject3 d2) {
			return (d1.no > d2.no) ? 1 : (d1.no < d2.no) ? -1 : 0;
		}
	}

	// --- 이름으로 순서를 매기는 comparator ---//
	public static final Comparator<SimpleObject3> NAME_ORDER = new NameOrderComparator();

	private static class NameOrderComparator implements Comparator<SimpleObject3> {
		@Override
		public int compare(SimpleObject3 d1, SimpleObject3 d2) {
			return d1.name.compareTo(d2.name);
		}
	}
}

class Node3 {
	SimpleObject3 data;
	Node3 link;

	public Node3(SimpleObject3 element) {
		data = element;
		link = null;
	}
	public Node3() {
		link = null;
	}
}

class CircularList {
	Node3 first;

	public CircularList() { //head node
		first = new Node3();
		first.link = first;
	}

	public boolean Delete(SimpleObject3 element, Comparator<SimpleObject3> cc) // delete the element
	{
		Node3 current_node = first, next_node = first.link;
		while(next_node != first) {
			if(cc.compare(next_node.data, element) == 0) {
				System.out.println("Delete Data : " + next_node.data.toString());
				current_node.link = next_node.link;
				next_node = null;
				return true;
			}
			current_node = next_node;
			next_node = current_node.link;
		}
		return false;
	}

	public void Show() { // 전체 리스트를 순서대로 출력한다.
		Node3 current_node = null, next_node = first.link;
		if(first.link == first) {
			System.out.println("No Data exist");
			return;
		}
		System.out.print("Current Data : ");
		while(next_node != first) {
			current_node = next_node;
			next_node = current_node.link;
			if(next_node == first) {
				System.out.println(current_node.data.toString());
				return;
			}
			System.out.print(current_node.data.toString() + " -> ");
		}
	}

	public void Add(SimpleObject3 element, Comparator<SimpleObject3> cc) // 임의 값을 삽입할 때 리스트가 오름차순으로 정렬이 되도록 한다
	{
		Node3 tmp = new Node3(element);
		if(first.link == first) {
			first.link = tmp;
			tmp.link = first;
			System.out.println("Input data = " + element.toString());
		}
		else {
			Node3 next_node = first.link, qurrent_node = first;
			while(next_node != first) {
				if(cc.compare(element, next_node.data) < 0) {
					qurrent_node.link = tmp;
					tmp.link = next_node;
					return;
				}
				qurrent_node = next_node;
				next_node = next_node.link;
			}
		}
	}

	public boolean Search(SimpleObject3 element, Comparator<SimpleObject3> cc) {
		Node3 current_node = first, next_node = first.link;
		while(next_node != first) {
			if(cc.compare(next_node.data, element) == 0) {
				System.out.println(next_node.data.toString());
				return true;
			}
			current_node = next_node;
			System.out.print(current_node.data.toString() + " -> ");
			next_node = current_node.link;
		}
		return false; // 전체 리스트를 순서대로 출력한다.
	}
}

public class Test8_2객체원형리스트_test {
	static final Scanner sc = new Scanner(System.in);
	enum Menu {
		Add("삽입"), Delete("삭제"), Show("인쇄"), Search("검색"), Exit("종료");

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
		CircularList l = new CircularList();
		SimpleObject3 data;
		System.out.println("inserted");
		
		do {
			System.out.println();
			l.Show();
			switch (menu = SelectMenu()) {
			case Add: // 머리노드 삽입
				data = new SimpleObject3();
				data.scanData("입력", 3);
				l.Add(data, SimpleObject3.NO_ORDER);
				break;
			case Delete: // 머리 노드 삭제
				data = new SimpleObject3();
				data.scanData("삭제", SimpleObject3.NO);
				boolean num = l.Delete(data, SimpleObject3.NO_ORDER);
				System.out.println("데이터 성공 = " + num);
				break;
			case Show: // 꼬리 노드 삭제
				l.Show();
				break;
			case Search: // 회원 번호 검색
				data = new SimpleObject3();
				data.scanData("탐색", SimpleObject3.NO);
				boolean result = l.Search(data, SimpleObject3.NO_ORDER);
				if (result)
					System.out.println("검색 성공 = " + result);
				else
					System.out.println("검색 실패 = " + result);
				break;
			case Exit: // 꼬리 노드 삭제
				break;
			}
		} while (menu != Menu.Exit);
	}
}
