
package datastructure.ch05;
//https://www.geeksforgeeks.org/n-queen-problem-backtracking-3/?ref=lbp
//N Queen problem / backtracking

//모든 해가 나오는 버젼 만들기 
/*
* 체스판은 8 x 8
* 체스의 기물: king/가로세로대각선 1칸만 이동, queen/가로세로 대각선/같은 편의 기물을 넘을 수 없다,
*  Rook/가로,세로 이동/다른 기물을 넘을 수없다, bishop/대각선, knight/1-2칸 이동/다른 기물을 넘을 수 있다,
*  pawn/처음 이동은 2칸까지 가능, 그 후 한칸만 가능, 잡을 때는 대각선 가능
*  체스판 최대 배치 문제 : king/16, Queen/8, rook/8, bishop/?, knight/?
*  rook 2개/a, h, knight 2개/b, g, bishop 2개/c, f, queen 1개/black queen은 black 칸에, 폰 8개
*/
class Point {
	private int ix;
	private int iy;

	public Point(int x, int y) {
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
		if ((this.ix == ((Point) p).ix) && (this.iy == ((Point) p).iy))
			return true;
		else
			return false;
	}
}
class Stack3 {
	private int[] data; // 스택용 배열
	private int capacity; // 스택의 크기
	private int ptr; // 스택 포인터

//--- 실행시 예외: 스택이 비어있음 ---//
	public class EmptyIntStackException extends RuntimeException {
		private static final long serialVersionUID = 1L;
	}

//--- 실행시 예외: 스택이 가득 참 ---//
	public class OverflowIntStackException extends RuntimeException {

		private static final long serialVersionUID = 1L;
	}

//--- 생성자(constructor) ---//
	public Stack3(int maxlen) {
		ptr = 0;
		capacity = maxlen;
		try {
			data = new int[capacity]; // 스택 본체용 배열을 생성
		} catch (OutOfMemoryError e) { // 생성할 수 없음
			capacity = 0;
		}
	}

	public void push(int x) throws OverflowIntStackException {
		if (isFull()) throw new OverflowIntStackException(); // 스택이 가득 참
		data[ptr++] = x;
	}

	public int pop() throws EmptyIntStackException {
		if (isEmpty()) throw new EmptyIntStackException();
		return data[--ptr];
	}

	public int peek() throws EmptyIntStackException {
		if (isEmpty()) throw new EmptyIntStackException();
		return data[ptr-1];
	}
	
	public void dump() throws EmptyIntStackException {
		if (isEmpty()) throw new EmptyIntStackException();
		for(int i = 0; i < ptr; i++) System.out.print(data[i] + " ");
		System.out.println();
	}

	public void clear() throws EmptyIntStackException {
		if (isEmpty()) throw new EmptyIntStackException();
		ptr = 0;

	}

	public int indexOf(int x) {
		for (int i = ptr - 1; i >= 0; i--)
			if (data[i] == x)
				return i; 
		return -1; 
	}

	public int getCapacity() {
		return capacity;
	}

	public int size() {
		return ptr;
	}

	public boolean isEmpty() {
		return ptr <= 0;
	}

	public boolean isFull() {
		return ptr >= capacity;
	}


}
public class Test_QueenEight {
	public static void solveQueen(int[][] d) {
		int count = 0;//퀸 배치 갯수
		int ix = 0, iy = 0;// 행 ix, 열 iy
		Stack3 st = new Stack3(100); //100개를 저장할 수 있는 스택을 만들고
		Point p = new Point(ix, iy);//현 위치를 객체로 만들고
		d[ix][iy] = 1;//현 위치에 queen을 넣었다는 표시를 하고
		count++;
		st.push(p);// 스택에 현 위치 객체를 push
		ix++;//ix는 행별로 퀸 배치되는 것을 말한다.
		while (true) {
			if (st.isEmpty() && ix == 8) //ix가 8이면 8개 배치 완료, stack이 empty가 아니면 다른 해를 구한다 
				break;
			if ((iy = nextMove(d, ix, iy))== -1) {//다음 이동할 열을 iy로 주는데 -1이면 더이상 이동할 열이 없음을 나타냄

			}

			if (count == 8) { //8개를 모두 배치하면

			}

		}

	}

	public static boolean checkRow(int[][] d, int crow) { //배열 d에서 행 crow에 퀸을 배치할 수 있는지 조사
		return false;
	}

	public static boolean checkCol(int[][] d, int ccol) {//배열 d에서 열 ccol에 퀸을 배치할 수 있는지 조사
		return false;
	}
	//배열 d에서 행 cx, 열 cy에 퀸을 남서, 북동 대각선으로 배치할 수 있는지 조사
	public static boolean checkDiagSW(int[][] d, int cx, int cy) { // x++, y-- or x--, y++ where 0<= x,y <= 7
		return false;
	}

	//배열 d에서 행 cx, 열 cy에 퀸을 남동, 북서 대각선으로 배치할 수 있는지 조사
	public static boolean checkDiagSE(int[][] d, int cx, int cy) {// x++, y++ or x--, y--
		return false;
	}
	//배열 d에서 (x,y)에 퀸을 배치할 수 있는지  조사
	public static boolean checkMove(int[][] d, int x, int y) {// (x,y)로 이동 가능한지를 check
		return false;

	}
	//배열 d에서 현재 위치(row,col)에 대하여 다음에 이동할 위치 nextCol을 반환, 이동이 가능하지 않으면 -1를 리턴
	public static int nextMove(int[][] d, int row, int col) {// 현재 row, col에 대하여 이동할 col을 return
		return -1;
	}
	
	static void showQueens(int[][] data) {//배열 출력

	}

	public static void main(String[] args) {
		int row = 8, col = 8;
		int[][] data = new int[8][8];
		for (int i = 0; i < data.length; i++)
			for (int j = 0; j < data[0].length; j++)
				data[i][j] = 0;

		solveQueen(data);

	}
}
