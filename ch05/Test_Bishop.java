package datastructure.ch05;

//해가 256개 확인 필요 23.12.12
import java.util.ArrayList;
import java.util.List;


//https://www.geeksforgeeks.org/n-Bishop-problem-backtracking-3/?ref=lbp
//N Bishop problem / backtracking
//모든 해가 나오는 버젼 만들기 
/*
 * 체스판은 8 x 8 체스의 기물: king/가로세로대각선 1칸만 이동, Bishop/가로세로 대각선/같은 편의 기물을 넘을 수 없다,
 * Rook/가로,세로 이동/다른 기물을 넘을 수없다, bishop/대각선, knight/1-2칸 이동/다른 기물을 넘을 수 있다,
 * pawn/처음 이동은 2칸까지 가능, 그 후 한칸만 가능, 잡을 때는 대각선 가능 체스판 최대 배치 문제 : king/16,
 * Bishop/8, rook/8, bishop/?, knight/? rook 2개/a, h, knight 2개/b, g, bishop
 * 2개/c, f, Bishop 1개/black Bishop은 black 칸에, 폰 8개
 */
class Point2 {
	private int ix;
	private int iy;

	public Point2(int x, int y) {
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
		if ((this.ix == ((Point2) p).ix) && (this.iy == ((Point2) p).iy))
			return true;
		else
			return false;
	}
}

class Stack12 {
	// --- 실행시 예외: 스택이 비어있음 ---//
	// generic class는 Throwable을 상속받을 수 없다 - 지원하지 않는다
	public class EmptyGenericStackException extends Exception {
		private static final long serialVersionUID = 1L;

		public EmptyGenericStackException(String message) {
			super(message);
		}
	}

	// --- 실행시 예외: 스택이 가득 참 ---//
	public class OverflowGenericStackException extends RuntimeException {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public OverflowGenericStackException(String message) {
			super(message);
		}
	}

	private List<Point2> data; // 스택용 배열
	// private List<T> data;
	private int capacity; // 스택의 크기
	private int top; // 스택 포인터

	// --- 생성자(constructor) ---//
	public Stack12(int capacity) {
		data = new ArrayList<>();
		this.capacity = capacity;
		top = 0;
	}

	// --- 스택에 x를 푸시 ---//
	public boolean push(Point2 x) throws OverflowGenericStackException {
		if (isFull())
			throw new OverflowGenericStackException("Stack is Full!");
		data.add(x);
		top++;
		return true;
	}

	// --- 스택에서 데이터를 팝(정상에 있는 데이터를 꺼냄) ---//
	public Point2 pop() throws EmptyGenericStackException {
		if (isEmpty())
			throw new EmptyGenericStackException("Stack is Empty!");
		Point2 p = data.get(top - 1);
		data.remove(--top);
		return p;
	}

	// --- 스택에서 데이터를 피크(peek, 정상에 있는 데이터를 들여다봄) ---//
	public Point2 peek() throws EmptyGenericStackException {
		if (isEmpty())
			throw new EmptyGenericStackException("Stack is Empty!");
		return data.get(top - 1);
	}

	// --- 스택을 비움 ---//
	public void clear() {
		top = 0;
	}

	// --- 스택에서 x를 찾아 인덱스(없으면 –1)를 반환 ---//
	public int indexOf(Point2 x) {
		for (int i = top - 1; i >= 0; i--) // 꼭대기 쪽부터 선형 검색
			if (data.get(i).equals(x))
				return i; // 검색 성공
		return -1; // 검색 실패
	}

	// --- 스택의 크기를 반환 ---//
	public int getCapacity() {
		return capacity;
	}

	// --- 스택에 쌓여있는 데이터 갯수를 반환 ---//
	public int size() {
		return top;
	}

	// --- 스택이 비어있는가? ---//
	public boolean isEmpty() {
		return top <= 0;
	}

	// --- 스택이 가득 찼는가? ---//
	public boolean isFull() {
		return top >= capacity;
	}

	// --- 스택 안의 모든 데이터를 바닥 → 꼭대기 순서로 출력 ---//
	public void dump() throws EmptyGenericStackException {
		if (top <= 0)
			throw new EmptyGenericStackException("stack:: dump - empty");
		else {
			for (int i = 0; i < top; i++)
				System.out.print(data.get(i) + " ");
			System.out.println();
		}
	}
}

public class Test_Bishop {
	public static void EightBishop(int[][] d) {
		int count = 0;// 퀸 배치 갯수
		int numberSolutions = 0;
		int ix = 0, iy = 0;// 행 ix, 열 iy
		Stack12 st = new Stack12(100); // 100개를 저장할 수 있는 스택을 만들고
		Point2 p = new Point2(ix, iy);// 현 위치를 객체로 만들고
//		st.push(p);// 스택에 현 위치 객체를 push
		while (true) {
			if(iy == 8) {
				ix++;
				iy=0;
			}
			if (count == 8) {
				showBishops(d);
				try {
					p = st.pop();
				} catch (Stack12.EmptyGenericStackException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ix = p.getX();
				iy = p.getY();
				d[ix][iy] = 0;
				iy++;
				count--;
				numberSolutions++;
			}
			iy = nextMove(d, ix, iy);
			if (iy != -1) {
				p = new Point2(ix, iy);
				st.push(p);
				d[ix][iy] = 1;
				iy++;
				count++;
			} else {
				if(st.isEmpty()) {
					break;
				}
				else {
					try {
						p = st.pop();
					} catch (Stack12.EmptyGenericStackException e) {
					}
					ix = p.getX();
					iy = p.getY();
					d[ix][iy] = 0;
					iy++;
					count--;
				}
			}
		}
		System.out.printf("총 해의 갯수 :  %d개\n", numberSolutions);
	}

	// 배열 d에서 행 cx, 열 cy에 퀸을 남서, 북동 대각선으로 배치할 수 있는지 조사
	public static boolean checkDiagSW(int[][] d, int cx, int cy) { // x++, y-- or x--, y++ where 0<= x,y <= 7
		int x = cx, y = cy;
		while (x < 8 && y < 8) {
			if (d[x][y] == 1)
				return false;
			x++;
			y++;
		}
		x = cx;
		y = cy;
		while (x >= 0 && y >= 0) {
			if (d[x][y] == 1)
				return false;
			x--;
			y--;
		}

		return true;
	}

	// 배열 d에서 행 cx, 열 cy에 퀸을 남동, 북서 대각선으로 배치할 수 있는지 조사
	public static boolean checkDiagSE(int[][] d, int cx, int cy) {// x++, y++ or x--, y--
		int x = cx, y = cy;
		while (x >= 0 && y < 14) {
			if (d[x][y] == 1)
				return false;
			x--;
			y++;
		}
		x = cx;
		y = cy;
		while (x < 14 && y >= 0) {
			if (d[x][y] == 1)
				return false;
			x++;
			y--;
		}
		return true;
	}

	// 배열 d에서 (x,y)에 퀸을 배치할 수 있는지 조사
	public static boolean checkMove(int[][] d, int x, int y) {// (x,y)로 이동 가능한지를 check
		return checkDiagSE(d, x, y) && checkDiagSW(d, x, y);
	}

	// 배열 d에서 현재 위치(row,col)에 대하여 다음에 이동할 위치 nextCol을 반환, 이동이 가능하지 않으면 -1를 리턴
	public static int nextMove(int[][] d, int row, int col) {// 현재 row, col에 대하여 이동할 col을 return
		for (int i = col; i < 8; i++)
			if (checkMove(d, row, i))
				return i;
		return -1;
	}

	static void showBishops(int[][] data) {// 배열 출력
		System.out.println("::::::::::비숍 배치:::::::::::::");
		for (int[] i : data) {
			for (int j : i)
				System.out.print(j + " ");
			System.out.println();
		}
		System.out.println(":::::::::::::::::::::::::::::");
	}

	public static void main(String[] args) {
		int row = 8, col = 8;
		int[][] data = new int[row][col];
		for (int i = 0; i < row; i++)
			for (int j = 0; j < col; j++)
				data[i][j] = 0;

		EightBishop(data);

	}
}