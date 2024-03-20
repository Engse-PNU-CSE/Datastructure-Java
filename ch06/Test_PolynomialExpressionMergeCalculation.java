package datastructure.ch06;

import java.util.DuplicateFormatFlagsException;

class Polynomial implements Comparable<Polynomial> {
	double coef; // 계수
	int exp; // 지수

	@Override
	public int compareTo(Polynomial o) {
//		if(this.exp == o.exp) return (int)(this.coef-o.coef);
		return this.exp - o.exp;
	}

	public Polynomial(double coef, int exp) {
		super();
		this.coef = coef;
		this.exp = exp;
	}

}

public class Test_PolynomialExpressionMergeCalculation {

	static void merge(Polynomial[] a, int lefta, int righta, int leftb, int rightb) {
		Polynomial temp[] = new Polynomial[a.length];
		int ix = 0;
		int p = lefta, q = leftb;
		while (p <= righta && q <= rightb) {
			if (a[p].compareTo(a[q]) > 0)
				temp[ix++] = a[p++];
			else if (a[p].compareTo(a[q]) < 0)
				temp[ix++] = a[q++];
			else {
				temp[ix++] = a[p++];
				temp[ix++] = a[q++];
			}
		}
		while (p > righta && q <= rightb)
			temp[ix++] = a[q++];
		while (q > rightb && p <= righta)
			temp[ix++] = a[p++];
		for (int i = 0; i < ix; i++) a[lefta + i] = temp[i];
	}

	// --- 퀵 정렬(비재귀 버전)---//
	static void MergeSort(Polynomial[] a, int left, int right) {
		int mid = (left + right) / 2;
		if (left == right)
			return;
		MergeSort(a, left, mid);
		MergeSort(a, mid + 1, right);
		merge(a, left, mid, mid + 1, right);
		return;
	}

	static double EvaluatePolynomial(Polynomial[] z, int i) {
		double sum = 0;
		for (Polynomial a : z) {
			if(a!=null) sum += a.coef * Math.pow(i, a.exp);
		}
		return sum;
	}

	static void MultiplyPolynomial(Polynomial[] x, Polynomial[] y, Polynomial[] z) {
		int pz = 0;
		int nx = x.length, ny = y.length;
		for(int i = 0; i < nx; i++) {
			if(x[i]==null) break;
			for(int j = 0; j < ny; j++ ) {
				if(x[j]==null) break;
				z[pz++] = new Polynomial(x[i].coef * y[j].coef, x[i].exp+y[j].exp);
			}
		}
		MergeSort(z, 0, pz - 1);
	}

	static void AddPolynomial(Polynomial[] x, Polynomial[] y, Polynomial[] z) {
		int px = 0, nx = x.length;
		int py = 0, ny = y.length;
		int pz = 0;

		while (px < nx && py < ny) {
			if (x[px].compareTo(y[py]) == 0) {
				z[pz++] = new Polynomial(x[px].coef + y[py++].coef, x[px++].exp);
			} else
				z[pz++] = (x[px].compareTo(y[py]) < 0) ? y[py++] : x[px++];
		}

		while (px < nx)
			z[pz++] = x[px++];

		while (py < ny)
			z[pz++] = y[py++];

	}

	static void ShowPolynomial(String pname, Polynomial[] x) {
		System.out.printf(":::::::::::::::+Polynimial %s :::::::::::::::\n", pname);
		for (Polynomial p : x) {
			if (p != null)
				System.out.printf("%.1fx^%d + ", p.coef, p.exp);
		}
		System.out.println("\n");
	}
	static void removeDuplicatedExp(Polynomial[] x) {
		int i, j, k;
		for(i = 0 ; i < x.length-1; i++) {
			if(x[i]==null) break;
			for(j = i+1; j < x.length; j++) {
				if(x[j]==null) break;
				else if (x[i].exp == x[j].exp) {
					x[i].coef += x[j].coef;
					for(k = j; k < x.length-1; k++) {
						if(x[k]==null) {
							x[k-1] = null;
							break;
						}
						x[k]=x[k+1];
					}
					x[k]=null;
					j--;
				}
			}
		}
	}
	public static void main(String[] args) {
		Polynomial[] x = {new Polynomial(1.5, 3), new Polynomial(1.5, 3),new Polynomial(1.5, 3), new Polynomial(2.5, 7), new Polynomial(3.3, 2),
				new Polynomial(4.0, 1), new Polynomial(2.2, 0), new Polynomial(3.1, 4), new Polynomial(3.8, 5), };
		Polynomial[] y = { new Polynomial(1.5, 1), new Polynomial(2.5, 2), new Polynomial(3.3, 3),
				new Polynomial(4.0, 0), new Polynomial(2.2, 4), new Polynomial(3.1, 5), new Polynomial(3.8, 6), };
		int nx = x.length, ny = y.length;
		ShowPolynomial("X", x);
		ShowPolynomial("Y", y);
		MergeSort(x, 0, x.length - 1); // 배열 x를 퀵정렬
		removeDuplicatedExp(x);
		MergeSort(y, 0, y.length - 1); // 배열 x를 퀵정렬
		removeDuplicatedExp(y);
		ShowPolynomial("Sorted X",x);
		ShowPolynomial("add same exp", x);
		ShowPolynomial("Sotred Y",y);
		Polynomial[] z1 = new Polynomial[nx + ny];
		AddPolynomial(x, y, z1);// 다항식 덧셈 z = x + y
		removeDuplicatedExp(z1);
		ShowPolynomial("Z = X + Y",z1);
		Polynomial[] z2 = new Polynomial[nx * ny];
		MultiplyPolynomial(x, y, z2);// 다항식 곱셈 z = x * y
		removeDuplicatedExp(z2);
		ShowPolynomial("Z = X * Y",z2);
		double result = EvaluatePolynomial(z2, 10);// 다항식 값 계산 함수 z(10) 값 계산한다
		System.out.println(" result = " + result);
	}
}
