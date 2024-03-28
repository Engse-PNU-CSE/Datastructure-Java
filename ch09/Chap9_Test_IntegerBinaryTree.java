package datastructure.ch09;

import java.util.ArrayList;

import java.util.List;
import java.util.Random;
import java.util.Scanner;


class TreeNode {
	TreeNode LeftChild;
	int data;
	TreeNode RightChild;

	public TreeNode() {
		LeftChild = RightChild = null;
	}
}

class ObjectStack {
	// --- 실행시 예외: 스택이 비어있음 ---//
	// generic class는 Throwable을 상속받을 수 없다 - 지원하지 않는다
	public class EmptyGenericStackException extends Exception {
		private static final long serialVersionUID = 1L;

		public EmptyGenericStackException() {
			super();
		}
	}

	// --- 실행시 예외: 스택이 가득 참 ---//
	public class OverflowGenericStackException extends RuntimeException {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public OverflowGenericStackException() {
		}
	}

	private List<TreeNode> data; // list를 사용: 배열은 크기를 2배로 늘리는 작업 필요
	// private List<T> data;
	private int capacity; // 스택의 크기
	private int top; // 스택 포인터

//--- 생성자(constructor) ---//
	public ObjectStack(int capacity) {
		top = 0;
		this.capacity = capacity;
		// this.data = new T[capacity]; // 스택 본체용 배열을 생성
		try {
			data = new ArrayList<>(capacity);
		} catch (OutOfMemoryError e) {
			capacity = 0;
		}
	}

//--- 스택에 x를 푸시 ---//
	public boolean push(TreeNode x) throws OverflowGenericStackException {
		System.out.println("top = " + top + "capacity = " + capacity);
		if (top >= capacity)
			throw new OverflowGenericStackException();
		top++;
		return data.add(x);

	}

//--- 스택에서 데이터를 팝(정상에 있는 데이터를 꺼냄) ---//
	public TreeNode pop() throws EmptyGenericStackException {
		if (top < 0)
			throw new EmptyGenericStackException();
		return data.remove(--top);
	}

//--- 스택에서 데이터를 피크(peek, 정상에 있는 데이터를 들여다봄) ---//
	public TreeNode peek() throws EmptyGenericStackException {
		if (top <= 0)
			throw new EmptyGenericStackException();
		return data.get(top - 1);
	}

//--- 스택을 비움 ---//
	public void clear() {
		top = 0;
	}

//--- 스택에서 x를 찾아 인덱스(없으면 –1)를 반환 ---//
	public int indexOf(TreeNode x) {
		for (int i = top - 1; i >= 0; i--) // 꼭대기 쪽부터 선형 검색
			if (data.get(i).equals(x))
				return i; // 검색 성공
		return -1; // 검색 실패
	}

//--- 스택의 크기를 반환 ---//
	public int getCapacity() {
		return capacity;
	}

//--- 스택에 쌓여있는 데이터 갯수를 반환 ---//
	public int size() {
		return top;
	}

//--- 스택이 비어있는가? ---//
	public boolean isEmpty() {
		return top <= 0;
	}

//--- 스택이 가득 찼는가? ---//
	public boolean isFull() {
		return top >= capacity;
	}

//--- 스택 안의 모든 데이터를 바닥 → 꼭대기 순서로 출력 ---//
	public void dump() {
		if (top <= 0)
			System.out.println("stack이 비어있습니다.");
		else {
			for (int i = 0; i < top; i++)
				System.out.print(data.get(i) + " ");
			System.out.println();
		}
	}
}

//정수를 저정하는 이진트리 만들기 실습
class ObjectQueue {
	private TreeNode[] que;// 큐는 배열로 구현
	// private List<Integer> que; // 수정본
	private int capacity; // 큐의 크기
	private int front; // 맨 처음 요소 커서
	private int rear; // 맨 끝 요소 커서
	private int num; // 현재 데이터 개수

//--- 실행시 예외: 큐가 비어있음 ---//
	public class EmptyQueueException extends RuntimeException {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public EmptyQueueException() {
		}
	}

//--- 실행시 예외: 큐가 가득 찼음 ---//
	public class OverflowQueueException extends RuntimeException {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public OverflowQueueException() {
		}
	}

//--- 생성자(constructor) ---//
	public ObjectQueue(int maxlen) {
		num = front = rear = 0;
		capacity = maxlen;
		try {
//       que = new int[capacity];          // 큐 본체용 배열을 생성
			que = new TreeNode[maxlen];
		} catch (OutOfMemoryError e) { // 생성할 수 없음
			capacity = 0;
		}
	}

//--- 큐에 데이터를 인큐 ---//
	public int enque(TreeNode x) throws OverflowQueueException {
		if (num >= capacity)
			throw new OverflowQueueException(); // 큐가 가득 찼음
		que[rear++] = x;
		num++;

		return 1;
	}

//--- 큐에서 데이터를 디큐 ---//
	public TreeNode deque() throws EmptyQueueException {
		if (num <= 0)
			throw new EmptyQueueException(); // 큐가 비어있음
		TreeNode x = que[front++];
		num--;

		return x;
	}

//--- 큐에서 데이터를 피크(프런트 데이터를 들여다봄) ---//
	public TreeNode peek() throws EmptyQueueException {
		if (num <= 0)
			throw new EmptyQueueException(); // 큐가 비어있음
		return que[front];
	}

//--- 큐를 비움 ---//
	public void clear() {
		num = front = rear = 0;
	}

//--- 큐에서 x를 검색하여 인덱스(찾지 못하면 –1)를 반환 ---//
	public int indexOf(TreeNode x) {
		for (int i = 0; i < num; i++) {
			int idx = (i + front) % capacity;
			if (que[idx].equals(x)) // 검색 성공
				return idx;
		}
		return -1; // 검색 실패
	}

//--- 큐의 크기를 반환 ---//
	public int getCapacity() {
		return capacity;
	}

//--- 큐에 쌓여 있는 데이터 개수를 반환 ---//
	public int size() {
		return num;
	}

//--- 큐가 비어있는가? ---//
	public boolean isEmpty() {
		return num <= 0;
	}

//--- 큐가 가득 찼는가? ---//
	public boolean isFull() {
		return num >= capacity;
	}

//--- 큐 안의 모든 데이터를 프런트 → 리어 순으로 출력 ---//
	public void dump() {
		if (num <= 0)
			System.out.println("큐가 비어있습니다.");
		else {
			for (int i = 0; i < num; i++)
				System.out.print(que[((i + front) % capacity)] + " ");
			System.out.println();
		}
	}
}

class Tree {
	TreeNode root;

	Tree() {
		root = null;
	}

	TreeNode inorderSucc(TreeNode current) {
		TreeNode temp = current.RightChild;
		if (current.RightChild != null)
			while (temp.LeftChild != null)	
				temp = temp.LeftChild;
		System.out.println("inordersucc:: temp.data = " + temp.data);
		return temp;
	}

	boolean isLeafNode(TreeNode current) {
		if (current.LeftChild == null && current.RightChild == null)
			return true;
		else
			return false;
	}

	void inorder() {
		inorder(root);
	}

	void preorder() {
		preorder(root);
	}

	void postorder() {
		postorder(root);
	}

	void inorder(TreeNode CurrentNode) {
		if (root == null) {
			System.out.println("Not inserted Data");
			return;
		}
		if (CurrentNode != null) {
			inorder(CurrentNode.LeftChild);
			System.out.print(" " + CurrentNode.data);
			inorder(CurrentNode.RightChild);
		}
	}

	void preorder(TreeNode CurrentNode) {
		if (root == null) {
			System.out.println("Not inserted Data");
			return;
		}
		if (CurrentNode != null) {
			System.out.print(CurrentNode.data + " ");
			preorder(CurrentNode.LeftChild);
			preorder(CurrentNode.RightChild);
		}
	}

	void postorder(TreeNode CurrentNode) {
		if (root == null) {
			System.out.println("Not inserted Data");
			return;
		}
		if (CurrentNode != null) {
			postorder(CurrentNode.LeftChild);
			postorder(CurrentNode.RightChild);
			System.out.print(CurrentNode.data + " ");
		}
	}

	void NonrecInorder()// void Tree::inorder(TreeNode *CurrentNode)와 비교
	{
		ObjectStack s = new ObjectStack(20);
		TreeNode CurrentNode = root;
		if (root == null) {
			System.out.println("Not inserted Data");
			return;
		}
		while (true) {
			while (CurrentNode != null) {
				s.push(CurrentNode);
				CurrentNode = CurrentNode.LeftChild;
			}
			if (!s.isEmpty()) {
				try {
					CurrentNode = s.pop();
				} catch (ObjectStack.EmptyGenericStackException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(" " + CurrentNode.data);
				CurrentNode = CurrentNode.RightChild;
			}
			else break;  
		}
	}

	void levelOrder() {
		ObjectQueue q = new ObjectQueue(100);
		TreeNode CurrentNode = root;
		if (root == null) {
			System.out.println("Not inserted Data");
			return;
		}
		q.enque(CurrentNode);
		while (!q.isEmpty()) {
			int count = q.size();
			while(count > 0) {
	        CurrentNode = q.deque();
	        System.out.print("(" + ((CurrentNode.data < 10) ? " " : "")  + CurrentNode.data+ ") ");

	        if (CurrentNode.LeftChild != null) q.enque(CurrentNode.LeftChild);
	        if (CurrentNode.RightChild != null) q.enque(CurrentNode.RightChild);
	        count--;
			}
			System.out.println();
		}
	}

	boolean insert(int x) {// binary search tree를 만드는 입력 => A + B * C을 tree로 만드는 방법: 입력 해결하는 알고리즘 작성 방법을
							// 설계하여 구현
		TreeNode current_node = root;
		TreeNode parent_node = null;
		
		TreeNode tmp = new TreeNode();
		tmp.data = x;
		
		if(root == null) {
			root = tmp;
			return true;
		}
		
		while(current_node!=null) {
			if(current_node.data == x) return false;
			parent_node = current_node;
			if(current_node.data < x) current_node = current_node.RightChild;
			else current_node = current_node.LeftChild;
		}
		
		if(parent_node.data < x) parent_node.RightChild = tmp;
	
		else parent_node.LeftChild = tmp;
		
		return true;
	}
	void delete_cal(TreeNode current_node, TreeNode parent_node, int branchMode) {
		if(isLeafNode(current_node)) {
			if(current_node == root) {
				root = null;
				return;
			}
			if(branchMode == 1) parent_node.LeftChild = null;
			else parent_node.RightChild = null;
		}
		else {
			if(current_node.LeftChild == null) {
				if(branchMode == 1) parent_node.LeftChild = current_node.RightChild;
				else parent_node.RightChild = current_node.RightChild;
			}
			else if(current_node.RightChild == null) {
				if(branchMode == 1) parent_node.LeftChild = current_node.LeftChild;
				else parent_node.RightChild = current_node.LeftChild;
			}
			else {
				TreeNode tmp = new TreeNode();
				tmp = inorderSucc(current_node);
				delete(tmp.data);
				current_node.data = tmp.data;
			}
		}
	}
	boolean delete(int num) {// 난이도 최상급
		TreeNode current_node = root, q = null, parent_node = null;
		int branchMode = 0; // 1은 left, 2는 right
		while(current_node!=null) {
			if(current_node.data == num) {
				delete_cal(current_node, parent_node, branchMode);
				return true;
			}
			parent_node = current_node;
			if(current_node.data < num) {
				current_node = current_node.RightChild;
				branchMode = 2;
			}
			else {
				current_node = current_node.LeftChild;
				branchMode = 1;
			}
		}
		return false;
	}

	boolean search(int num) {
		TreeNode current_node = root;
		
		while(current_node!=null) {
			if(current_node.data == num) return true;
			if(current_node.data < num) current_node = current_node.RightChild;
			else current_node = current_node.LeftChild;
		}
		return false;
	}
}

public class Chap9_Test_IntegerBinaryTree {
	static final Scanner stdIn = new Scanner(System.in);

	enum Menu {
		Add("삽입"), Delete("삭제"), Search("검색"), InorderPrint("정렬출력"), LevelorderPrint("레벨별출력"),
		StackInorderPrint("스택정렬출력"), PreorderPrint("prefix출력"), PostorderPrint("postfix출력"), Exit("종료");

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
				if(m.ordinal() % 3 == 0 && m.ordinal() != 0) System.out.println();
				}
			System.out.print(" : ");
			key = stdIn.nextInt();
		} while (key < Menu.Add.ordinal() || key > Menu.Exit.ordinal());

		return Menu.MenuAt(key);
	}

	public static void main(String[] args) {
		Random rand = new Random();
		Tree t = new Tree();
		Menu menu; // 메뉴
		int count = 6;
		int num;
		boolean result;
		do {
			System.out.println(":::::::::::::::::::::::::::::");
			System.out.print("Current Node : ");
			t.inorder();
			System.out.println();
			switch (menu = SelectMenu()) {
			case Add: //
				int[] input = new int[count];
				for (int ix = 0; ix < count; ix++) {
					input[ix] = rand.nextInt(1, 20);
				}
				for (int i = 0; i < count; i++) {
					if (!t.insert(input[i]))
						System.out.println("Insert Duplicated data");
					else System.out.println("Insert Success ::: " + input[i]);
				}
//				System.out.print("Input data(Integer) : ");
//				count = stdIn.nextInt();
//				if (!t.insert(count))
//					System.out.println("Insert Duplicated data");
//				else System.out.println("Insert Success ::: " + count);
				System.out.println();
				break;

			case Delete: // 임의 정수 삭제
				System.out.println("삭제할 데이터:: ");
				num = stdIn.nextInt();
				if (t.delete(num))
					System.out.println("삭제 데이터 = " + num + " 성공");
				else
					System.out.println("삭제 실패");
				;
				break;

			case Search: // 노드 검색
				System.out.println("검색할 데이터:: ");

				num = stdIn.nextInt();
				result = t.search(num);
				if (result)
					System.out.println(" 데이터 = " + num + "존재합니다.");
				else
					System.out.println("해당 데이터가 없습니다.");
				break;

			case InorderPrint: // 전체 노드를 키값의 오름차순으로 표시
				t.inorder();
				System.out.println();
				// t.NonrecInorder();
				break;
			case LevelorderPrint: // 전체 노드를 키값의 오름차순으로 표시
				t.levelOrder();
				System.out.println();
				// t.NonrecInorder();
				break;
			case StackInorderPrint: // 전체 노드를 키값의 오름차순으로 표시
				t.NonrecInorder();
				break;
			case PreorderPrint:// prefix 출력
				t.preorder();
				System.out.println();
				break;
			case PostorderPrint:// postfix로 출력
				t.postorder();
				System.out.println();
				break;
			default:
				break;
			}
		} while (menu != Menu.Exit);
	}
}
