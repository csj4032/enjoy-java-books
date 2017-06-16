package ch01.Q1_07_Rotate_Matrix;

import CtCILibrary.AssortedMethods;

import java.util.Arrays;

public class Question {

	public static boolean retate(int[][] matrix) {
		if (matrix.length == 0 || matrix.length != matrix[0].length) return false;
		int n = matrix.length;

		for (int layer = 0; layer < n / 2; layer++) {
			int first = layer;
			int last = n - 1 - layer;
			for (int i = first; i < last; i++) {
				int offset = i - first;
				matrix[first][i] = matrix[last - offset][first];
			}
		}

		return true;
	}

	public static void main(String[] args) {
		int[][] matrix = AssortedMethods.randomMatrix(3, 3, 0, 9);
		System.out.println(Arrays.deepToString(matrix));
	}
}