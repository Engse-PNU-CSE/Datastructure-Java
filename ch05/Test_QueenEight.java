
package Chap5_Recursive;
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
	public class EmptyGenericStackException extends Exception {
		private static final long serialVersionUID = 1L;

		public EmptyGenericStackException() {
		}
	}

	public class OverflowGenericStackException extends RuntimeException {

		private static final long serialVersionUID = 1L;

		public OverflowGenericStackException() {
		}
	}

	private List<Point> data;
	private int capacity;
	private int top;

	public Stack3(int capacity) {
		data = new ArrayList<>();
		this.capacity = capacity;
		top = 0;
	}

	public boolean push(Point x) throws OverflowGenericStackException {
		if (isFull())
			throw new OverflowGenericStackException();
		data.add(x);
		top++;
		return true;
	}

	public Point pop() throws EmptyGenericStackException {
		if (isEmpty())
			throw new EmptyGenericStackException();
		Point p = data.get(top - 1);
		data.remove(--top);
		return p;
	}

	public Point peek() throws EmptyGenericStackException {
		if (isEmpty())
			throw new EmptyGenericStackException();
		return data.get(top - 1);
	}

	public void clear() throws EmptyGenericStackException {
		if (isEmpty())
			throw new EmptyGenericStackException();
		while (!data.isEmpty())
			data.remove(--top);
	}

	public void dump() throws EmptyGenericStackException {
		if (isEmpty())
			throw new EmptyGenericStackException();
		System.out.print("현재 데이터 : ");
		for (Point2 p : data)
			System.out.print(p + " ");
		System.out.println();
	}

	public int indexOf(Point x) {
		for (int i = top - 1; i >= 0; i--)
			if (data.get(i).equals(x))
				return i;
		return -1;
	}

	public int getCapacity() {
		return capacity;
	}

	public int size() {
		return top;
	}

	public boolean isEmpty() {
		return top <= 0;
	}

	public boolean isFull() {
		return top >= capacity;
	}

}

public class Backtracking_Queen2023 {
	public static void solveQueen(int[][] d) {
		int count = 0;// 퀸 배치 갯수
		int ix = 0, iy = 0;// 행 ix, 열 iy
		Stack3 st = new Stack3(100); // 100개를 저장할 수 있는 스택을 만들고
		Point p = new Point(ix, iy);// 현 위치를 객체로 만들고
		d[ix][iy] = 1;// 현 위치에 queen을 넣었다는 표시를 하고
		count++;
		st.push(p);// 스택에 현 위치 객체를 push
		ix++;// ix는 행별로 퀸 배치되는 것을 말한다.
		while (true) {
			if (st.isEmpty() && ix == 8) // ix가 8이면 8개 배치 완료, stack이 empty가 아니면 다른 해를 구한다
				break;
			else {
				ix = 7;
				try {
					Point s = st.pop();
					ix = p.getX();
					iy = p.getY() + 1;
					d[ix][iy] = 0;
					count--;
				} catch (EmptyGenericStackException e) {
				}
			}
			if ((iy = nextMove(d, ix, iy)) == -1) {// 다음 이동할 열을 iy로 주는데 -1이면 더이상 이동할 열이 없음을 나타냄
				try {
					Point s = st.pop();
					ix = p.getX();
					iy = p.getY() + 1;
					d[ix][iy] = 0;
					count--;
				} catch (EmptyGenericStackException e) {
				}
			} else {
				d[ix][iy] = 1; // 퀸 배치
				Point s = new Point(ix, iy);
				try {
					st.push(p);
				} catch (Exception e) {
				}

				ix++; // 다음 행으로
				iy = 0; // 열 초기화
				count++; // 배치된 퀸의 개수 증가
			}

			if (count == 8) { // 8개를 모두 배치하면
				showQueens(d);
				d[ix][iy] = 0;
				try {
					st.pop();
				} catch (EmptyGenericStackException e) {
				}
				ix--;
			}

		}

	}

	public static boolean checkRow(int[][] d, int crow) { // 배열 d에서 행 crow에 퀸을 배치할 수 있는지 조사
		for (int i = 0; i < 8; i++)
			if (d[crow][i] == 1)
				return false;
		return true;
	}

	public static boolean checkCol(int[][] d, int ccol) {// 배열 d에서 열 ccol에 퀸을 배치할 수 있는지 조사
		for (int i = 0; i < 8; i++)
			if (d[i][ccol] == 1)
				return false;
		return true;
	}

	// 배열 d에서 행 cx, 열 cy에 퀸을 남서, 북동 대각선으로 배치할 수 있는지 조사
	public static boolean checkDiagSW(int[][] d, int cx, int cy) { // x++, y-- or x--, y++ where 0<= x,y <= 7
		int x = cx, y = cy;
		while (x >= 0 && y < 8)
			if (d[--x][++y] == 1)
				return false;
		x = cx;
		y = cy;
		while (x < 8 && y >= 0)
			if (d[++x][--y] == 1)
				return false;
		return true;
	}

	// 배열 d에서 행 cx, 열 cy에 퀸을 남동, 북서 대각선으로 배치할 수 있는지 조사
	public static boolean checkDiagSE(int[][] d, int cx, int cy) {// x++, y++ or x--, y--
		int x = cx, y = cy;
		while (x < 8 && y < 8)
			if (d[++x][++y] == 1)
				return false;
		x = cx;
		y = cy;
		while (x >= 0 && y >= 0)
			if (d[--x][--y] == 1)
				return false;
		return true;
	}

	// 배열 d에서 (x,y)에 퀸을 배치할 수 있는지 조사
	public static boolean checkMove(int[][] d, int x, int y) {// (x,y)로 이동 가능한지를 check
		return checkCol(d, x) && checkRow(d, y) && checkDiagSE(d, x, y) && checkDiagSW(d, x, y);
	}

	// 배열 d에서 현재 위치(row,col)에 대하여 다음에 이동할 위치 nextCol을 반환, 이동이 가능하지 않으면 -1를 리턴
	public static int nextMove(int[][] d, int row, int col) {// 현재 row, col에 대하여 이동할 col을 return
		for (int i = col + 1; i < 8; i++)
			if (checkMove(d, row, i))
				return i;
		return -1;
	}

	static void showQueens(int[][] data) {// 배열 출력
		for (int[] i : data) {
			for (int j : i)
				System.out.print(j + " ");
			System.out.println();
		}
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
