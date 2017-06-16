package ch01.Q1_07_Rotate_Matrix;

import CtCILibrary.AssortedMethods;

import java.util.Arrays;

/**
 * 이미지를 표현하는 N*N 행렬이 있다. 이미지의 각 픽셀은 4바이트로
 * 표현된다. 이때, 이미지를 90도 회전시키는 메서드를 작성하라. 부가적
 * 인 행렬을 사용하지 않고서도 할 수 있겠는가?
 */
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