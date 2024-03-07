package datastructure.ch02;

import java.util.Random;

class Matrix {
    private int[][] matrix;
    
    public Matrix(int rows, int cols) {
        int[][] matrix2 = new int[rows][cols];
        
        this.matrix = matrix2;
    }

    public void printMatrix() {
        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public Matrix addMatrix(Matrix B) {
    	int rows = matrix.length, cols = matrix[0].length;
    	Matrix C = new Matrix(rows, cols);
        int sum[][] = new int[rows][cols];
        for(int i = 0; i < rows; i++) for(int j=0; j < cols; j++) sum[i][j] = matrix[i][j] + B.matrix[i][j];
        C.matrix = sum;
        return C;
    }

    public void inputMatrix() {
    	Random rand = new Random();
        for(int i = 0 ; i < matrix.length; i ++) for(int j = 0 ; j < matrix[0].length; j++) matrix[i][j]=rand.nextInt(10);
    }
}
public class Homework2_3 {
	public static void main(String[] args) {
		Matrix A = new Matrix(2,3);
		Matrix B = new Matrix(2,3);
		A.inputMatrix();
		B.inputMatrix();
		System.out.println("Matrix A[2][3]");
		A.printMatrix();
		System.out.println("Matrix B[2][3]");
		B.printMatrix();
		Matrix C = A.addMatrix(B);
		System.out.println("Matrix C[2][3] = A + B");
		C.printMatrix();
		
	}
}
