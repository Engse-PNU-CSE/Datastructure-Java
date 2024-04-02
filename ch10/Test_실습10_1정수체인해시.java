package datastructure.ch10;

import java.util.Scanner;
//체인법에 의한 해시
//--- 해시를 구성하는 노드 ---//

class Node {
    int key;                 // 키값
    Node next;        // 뒤쪽 포인터(뒤쪽 노드에 대한 참조)
    
    public Node(int data) {
    	key = data;
    	next = null;
    }
}
class SimpleChainHash {
 private int    size;              // 해시 테이블의 크기
 private Node[] table;        // 해시 테이블

 

 public SimpleChainHash(int i) {
	// TODO Auto-generated constructor stub
	 size = i;
	 setTable(new Node[size]);
}

//--- 키값이 key인 요소를 검색(데이터를 반환) ---//
 public boolean search(int key) {
	 Node current_data = table[key%11];
	 int count = 1;
	 while(current_data != null) {
		 if(current_data.key == key) {
			 System.out.println("Index : " + key%11 + ", Node : " + count);
			 return true;
		 }
		 count++;
		 current_data = current_data.next;
	 }
	return false;
 }

 //--- 키값이 key인 데이터를 data의 요소로 추가 ---//
 public boolean add(int key) {
	 Node inserted_data = new Node(key);
	 if(table[key%11] == null) {
		 table[key%11] = inserted_data;
		 return true;
	 }
	 Node current_node = table[key%11], past_node = null;
	 while(current_node != null) {
		 if(current_node.key == key) return false;
		 past_node = current_node;
		 current_node = current_node.next;
	 }
	 past_node.next = new Node(key);
	return true;
 }

 //--- 키값이 key인 요소를 삭제 ---//
 public boolean delete(int key) {
	 if(table[key%11] == null) return false;
	 Node current_node = table[key%11], past_node = null;
	 if(current_node.key == key) {
		 table[key%11] = current_node.next;
		 current_node = null;
		 return true;
	 }
	 while(current_node != null) {
		 if(current_node.key == key) {
			 past_node.next = current_node.next;
			 current_node = null;
			 return true;
		 }
		 past_node = current_node;
		 current_node = current_node.next;
	 }
	return false;
 }

 //--- 해시 테이블을 덤프(dump) ---//
 public void dump() {
	 System.out.println("---current data---");
	 for(int i = 0; i < size; i ++) {
		 Node bucket = table[i];
		 System.out.print(i + ": ");
		 if(bucket == null) {
			 System.out.println();
			 continue;
		 }
		 while(bucket.next != null) {
			System.out.print("(" + bucket.key + ")" + " -> ");
			bucket = bucket.next;
		 }
		 System.out.println("(" + bucket.key + ")");
	 }
	 System.out.println("------------------");
 }

public Node[] getTable() {
	return table;
}

public void setTable(Node[] table) {
	this.table = table;
}
}

public class Test_실습10_1정수체인해시 {

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


//체인법에 의한 해시 사용 예
 public static void main(String[] args) {
	 	Menu menu;
		SimpleChainHash hash = new SimpleChainHash(11);
		Scanner stdIn = new Scanner(System.in);
		int val = 0;
		boolean  result;
		final int count = 15;
		do {
			switch (menu = SelectMenu()) {
			case Add:
				int[] input = new int[count];
				for (int ix = 0; ix < count; ix++) {
					double d = Math.random();
					input[ix] = (int) (d * 20);
					System.out.print(" " + input[ix]);
				}
				System.out.println();
				for (int i = 0; i < count; i++) {
					if (!hash.add(input[i]))
						System.out.println(input[i] + " : Insert Duplicated data");
				}
				break;
			case Delete:
				// Delete
				System.out.println("Search Value:: ");
				val = stdIn.nextInt();
				result = hash.delete(val);
				if (result)
					System.out.println(" 검색 데이터가 존재한다");
				else
					System.out.println(" 검색 데이터가 없음");
				System.out.println();
				break;
			case Search:
				System.out.println("Search Value:: ");
				val = stdIn.nextInt();
				result = hash.search(val);
				if (result)
					System.out.println(" 검색 데이터가 존재한다");
				else
					System.out.println(" 검색 데이터가 없음");
				System.out.println();
				break;

			case Show:
				hash.dump();
			break;
			default:
				break;
		}
	} while (menu != Menu.Exit);

	}
}
