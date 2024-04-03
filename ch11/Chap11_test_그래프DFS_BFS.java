package datastructure.ch11;

/*
 Graph Representation
 Adjacency Lists + BFS + DFS
*/

import java.util.Scanner;

class ListNode {
	int data;
	ListNode link;

	public ListNode(int data) {
		this.data = data;
		link = null;
	}
}

class List {

	ListNode first;

	public List() {
		first = null;
	}

	void Insert(int k) {// 같은 값을 체크하지 않아 중복 입력됨
		// 구현할 부분
		if (first == null) {
			first = new ListNode(k);
			return;
		}
		ListNode currentNode = first;
		while (currentNode.link != null) {
			currentNode = currentNode.link;
		}
		currentNode.link = new ListNode(k);
	}

	void Delete(int k) {
		// 구현할 부분
		if (first == null) {
			System.out.println("List is cleared");
			return;
		}
		ListNode currentNode = first;
		if (first.data == k) {
			first = currentNode.link;
			currentNode.link = null;
		}
		ListNode pastNode = null;
		while (currentNode != null) {
			if (currentNode.data == k)
				break;
			pastNode = currentNode;
			currentNode = currentNode.link;
		}
		pastNode.link = currentNode.link;
		currentNode.link = null;
	}
}

class ListIterator {

	private List list;
	private ListNode current;

	public ListIterator(List l) {
		list = l;
		current = list.first;
	}

	int First() {
		if (current != null)
			return current.data;
		else
			return 0;
	}

	int Next() {
		int data = current.data;
		current = current.link;
		return data;
	}

	boolean NotNull() {
		if (current != null)
			return true;
		else
			return false;
	}

	boolean NextNotNull() {
		if (current.link != null)
			return true;
		else
			return false;
	}

}

class QueueNode {
	int data;
	QueueNode link;

	QueueNode(int data, QueueNode link) {
		this.data = data;
		this.link = link;
	}
}

class Queue {
	private QueueNode front, rear;

	void QueueEmpty() {
		front = rear = null;
	}

	public Queue() {
		QueueEmpty();
	}

	boolean IsEmpty() {
		if (front == null)
			return true;
		else
			return false;
	}

	void Insert(int y) {
		// 구현할 부분
		QueueNode insertedData = new QueueNode(y, null);

		if (IsEmpty()) {
			front = rear = insertedData;
			return;
		}
		rear.link = insertedData;
		rear = insertedData;
	}

	int Delete() {
		if (IsEmpty()) {
	        System.out.println("Queue is empty");
	        return -1;
	    }
	    int deleteData = front.data;
	    if (front == rear) {
	        front = rear = null;
	    } else {
	        front = front.link;
	    }
	    return deleteData;
		// 구현할 부분
	}
}

class StackNode {
	ListNode data;
	StackNode link;

	StackNode(ListNode data) {
		this.data = data;
		link = null;
	}
}

class Stack {
	private StackNode top;

	void StackEmpty() {
		top = null;
	}

	public Stack() {
		StackEmpty();
	}

	boolean IsEmpty() {
		if (top == null)
			return true;
		else
			return false;
	}

	void Insert(ListNode y) {
		// 구현할 부분
		StackNode insertData = new StackNode(y);
		if (IsEmpty()) {
			top = insertData;
			return;
		}
		insertData.link = top;
		top = insertData;
	}

	ListNode Delete()
	// delete the top node in stack and return its data
	
	{
		ListNode deleteNode = top.data;
		top = top.link;
		return deleteNode;
		// 구현할 부분
	}
	ListNode Peek() {
		return top.data;
	}
}

class Graph {
	private List[] HeadNodes;
	int n;
	boolean[] visited;

	public Graph(int vertices) {
		n = vertices;
		HeadNodes = new List[n];
		visited = new boolean[n];
		for (int i = 0; i < n; i++) {
			HeadNodes[i] = new List();
			visited[i] = false;
		}

	}

	void displayAdjacencyLists() {
		for (int i = 0; i < n; i++) {
			// 구현할 부분
			System.out.printf("%d linked to : ", i);
			ListNode li = HeadNodes[i].first;
			if (li == null) {
				System.out.println();
				continue;
			}
			while (li.link != null) {
				System.out.print(li.data + " -> ");
				li = li.link;
			}
			System.out.println(li.data);
		}
	}

	void InsertVertex(int start, int end) {
		if (start < 0 || start >= n || end < 0 || end >= n) {
			System.out.println("the start node number is out of bound.");
			return;
		}

		// 구현할 부분
		HeadNodes[start].Insert(end);
		HeadNodes[end].Insert(start);
//		while(CurrentList != null) {
//		}
	}

	void BFS(int v) {
		boolean[] visited = new boolean[n]; // visited is declared as a Boolean
		for (int i = 0; i < n; i++)
			visited[i] = false; // initially, no vertices have been visited
		// 구현할 부분
		Queue route = new Queue();
		route.Insert(v);
		visited[v] = true;
		while(!route.IsEmpty()) {
			int n = route.Delete();
			System.out.printf("%d -> ", n);
			List leaf = HeadNodes[n];
			ListNode currentleaf_node = leaf.first;
			while(currentleaf_node != null) {
				if(!visited[currentleaf_node.data]) {
					visited[currentleaf_node.data] = true;
					route.Insert(currentleaf_node.data);
				}
				currentleaf_node = currentleaf_node.link;
			}
		}
		
	}

	void ShowList(List l) {
		ListIterator li = new ListIterator(l);
		// 구현할 부분
	}

	// Driver
	void DFS(int v) {
		for (int i = 0; i < n; i++) visited[i] = false; // initially, no vertices have been visited
		 //_DFS(v); // start search at vertex 0, its not used stack
		_NonRecursiveDFS(v);

	}

	// Workhorse
	void _DFS(int v)
	// visit all previously unvisited vertices that are reachable from vertex v
	{
		visited[v] = true;
		System.out.println(v + ", ");
		ListIterator li = new ListIterator(HeadNodes[v]);
		if (!li.NotNull())
			return;
		int w = li.First();
		while (true) {
			if (!visited[w])
				_DFS(w);
			if (li.NotNull())
				w = li.Next();
			else
				return;
		}
	}

	// Workhorse 2
	void _NonRecursiveDFS(int v)
	// visit all previously unvisited vertices that are reachable from vertex v
	{
		// 구현할 부분
		Stack route = new Stack();
		route.Insert(new ListNode(v));
		visited[v] = true;
		
		while(!route.IsEmpty()) {
			System.out.printf("%d -> ", route.Peek().data);
			List leaf = HeadNodes[route.Peek().data];
			ListNode currentleaf_node = leaf.first;
			while(currentleaf_node != null) {
				if(!visited[currentleaf_node.data]) {
					visited[currentleaf_node.data] = true;
					route.Insert(currentleaf_node);
					break;
				}
				currentleaf_node = currentleaf_node.link;
			}
			if(currentleaf_node == null) {
				route.Delete();
			}
		}
		System.out.println();
	}

	public void InsertVertex(int startEdge, int endEdge, int weight) {
		// TODO Auto-generated method stub

	}
}

public class Chap11_test_그래프DFS_BFS {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int select = 10, n, startEdge = -1, endEdge = -1;
		int startBFSNode = 0;// the start node to BFS
		int inputdata[][] = {{0, 1}, {0, 2}, {0, 3}, {1, 4}, {2, 4}, {3, 4}};

		System.out.println("vertex 숫자 입력: ");

		n = sc.nextInt();

		Graph g = new Graph(n);

		while (select != '0') {
			System.out.println("\n명령 선택 1: edge 추가, 2: Adjacency Lists 출력, 3: BFS, 4: DFS, 5: 종료 => ");
			select = sc.nextInt();
			switch (select) {
			case 1:
//				System.out.println("edge 추가: from vertext: ");
//				startEdge = sc.nextInt();
//				System.out.println("to vertex: ");
//				endEdge = sc.nextInt();
//				if (startEdge < 0 || startEdge >= n || endEdge < 0 || endEdge >= n) {
//					System.out.println("the input node is out of bound.");
//					break;
//				}
//				// get smallest start node.
//				if (startEdge < startBFSNode)
//					startBFSNode = startEdge;
//				if (endEdge < startBFSNode)
//					startBFSNode = endEdge;
//				g.InsertVertex(startEdge, endEdge);
				for(int i = 0; i < inputdata.length; i++) {
					g.InsertVertex(inputdata[i][0], inputdata[i][1]);
				}
				break;
			case 2:
				// display
				g.displayAdjacencyLists();
				break;

			case 3:
				System.out.println("Start BFS from node: " + startBFSNode);
				g.BFS(startBFSNode);
				break;
			case 4:
				System.out.println("Start DFS from node: " + startBFSNode);
				g.DFS(startBFSNode);
				break;
			case 5:
				System.exit(0);
				break;
			default:
				System.out.println("WRONG INPUT  " + "\n" + "Re-Enter");
				break;
			}
		}

		return;
	}
}
