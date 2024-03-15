package datastructure.ch04;
/*
 * 원형 큐로서 큐에 Point 객체를 저장 - 교재 소스코드를 원형 큐가 되도록 수정하는 연습
 */

import java.util.Random;
import java.util.Scanner;

class Point5 {
	private int ix;
	private int iy;

	public Point5(int x, int y) {
		ix = x;
		iy = y;
	}

	@Override
	public String toString() {
		return "<" + ix + ", " + iy + ">";
	}

	public int getX() {
		return ix;
	}

	public int getY() {
		return iy;
	}

	public void setX(int x) {
		ix = x;
	}

	public void setY(int y) {
		iy = y;
	}

	@Override
	public boolean equals(Object p) {
		if ((this.ix == ((Point5) p).ix) && (this.iy == ((Point5) p).iy))
			return true;
		else
			return false;
	}
}

class CircularQueue {
	static int QUEUE_SIZE = 0;
	Point5[] que;// 배열로 객체원형 큐 구현
	int front, rear;
	static boolean isEmptyTag;

	// --- 실행시 예외: 큐가 비어있음 ---//
	public class EmptyQueueException extends RuntimeException {
		private static final long serialVersionUID = 1L;
		public EmptyQueueException() {
			super();
		}
	}

	// --- 실행시 예외: 큐가 가득 찼음 ---//
	public class OverflowQueueException extends RuntimeException {
		private static final long serialVersionUID = 1L;
		public OverflowQueueException() {
		}
	}

	public CircularQueue(int count) {
		que = new Point5[count];
		QUEUE_SIZE = count;
		front = rear = 0;
		isEmptyTag = true;
	}

	void push(Point5 it) throws OverflowQueueException {
		if (isFull()) throw new OverflowQueueException();
		que[rear++] = it;
		if (rear == QUEUE_SIZE) rear = 0;
		isEmptyTag = false;
	}

	Point5 pop() throws EmptyQueueException {
		if (isEmpty())
			throw new EmptyQueueException();
		Point5 p = que[front++];
		if (front == QUEUE_SIZE) {
			front = 0;
		}
		if (size() == 0)
			isEmptyTag = true;

		return p;
	}

	void clear() throws EmptyQueueException {
		if (isEmpty()) throw new EmptyQueueException();
//		for (int i = 0; i < que.length; i++) {
//			que[i] = null;
//			
//		}
		front = rear = 0;
		isEmptyTag = true;
	}

	// --- 큐의 크기를 반환 ---//
	public int getCapacity() {
		return QUEUE_SIZE;
	}

	// --- 큐에 쌓여 있는 데이터 개수를 반환 ---//
	public int size() {
		if(!isEmptyTag) return (front < rear) ? rear-front : rear-front+QUEUE_SIZE;
		return 0;
	}

	// --- 원형 큐가 비어있는가? --- 수정 필요//
	public boolean isEmpty() {
		return size() <= 0;
	}

	// --- 원형 큐가 가득 찼는가? --- 수정 필요//
	public boolean isFull() {
		return size() >= QUEUE_SIZE;
	}

	public void dump() throws EmptyQueueException {
		if (isEmpty())throw new EmptyQueueException();
		System.out.print("현재 데이터 : ");
		for (int i = 0; i < size(); i++)
			System.out.print(que[(i + front) % QUEUE_SIZE] + " ");
		System.out.println();
	}

	public Point5 peek() throws EmptyQueueException {
		if (isEmpty())
			throw new EmptyQueueException();
		return que[front];
	}
}

public class HW4_2ObjectCircularQueue2 {
	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);
		CircularQueue oq = new CircularQueue(4); // 최대 64개를 인큐할 수 있는 큐
		Random random = new Random();
		int rndx = 0, rndy = 0;
		Point5 p = null;
		while (true) {
			System.out.println(" "); // 메뉴 구분을 위한 빈 행 추가
			System.out.printf("현재 데이터 개수: %d / %d\n", oq.size(), oq.getCapacity());
			System.out.print("(1)인큐　(2)디큐　(3)피크　(4)덤프　(5)clear  (0)종료: ");
			int menu = stdIn.nextInt();
			if(menu == 0) break;
			switch (menu) {
			case 1: // 인큐
				try {
					rndx = random.nextInt(20);
					rndy = random.nextInt(20);
					p = new Point5(rndx, rndy);
					oq.push(p);
					System.out.print("입력데이터: (" + rndx + ", " + rndy + ")");
				} catch (CircularQueue.OverflowQueueException e) {
					System.out.println("stack이 가득찼있습니다.");
					e.printStackTrace();
				}
				break;

			case 2: // 디큐
				try {
					p = oq.pop();
					System.out.println("디큐한 데이터는 " + p + "입니다.");
				} catch (CircularQueue.EmptyQueueException e) {
					System.out.println("큐가 비어 있습니다.");
					e.printStackTrace();
				}
				break;

			case 3: // 피크
				try {
					p = oq.peek();
					System.out.println("피크한 데이터는 " + p + "입니다.");
				} catch (CircularQueue.EmptyQueueException e) {
					System.out.println("큐가 비어 있습니다.");
					e.printStackTrace();
				}
				break;

			case 4: // 덤프
				try {
					oq.dump();
				} catch (CircularQueue.EmptyQueueException e) {
					System.out.println("큐가 비어 있습니다.");
					e.printStackTrace();
				}
				break;
			case 5: // 클리어
				try {
					oq.clear();
					System.out.println("원형 큐가 비었습니다.");
				} catch (CircularQueue.EmptyQueueException e) {
					System.out.println("큐가 비어 있습니다.");
					e.printStackTrace();
				}
				break;
			default:
				break;
			}
		}
		stdIn.close();
		System.out.println("프로그램을 종료합니다.");
	}
}
