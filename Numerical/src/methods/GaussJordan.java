package methods;

import matrices.matrixMaker;

public class GaussJordan {
	
	public double[] solve(double[][] a, double[] b) {
		//create augmented matrix
		matrixMaker matrix = new matrixMaker(a,b);
		return JordanElemenation(matrix);
	}
	
	private double[] JordanElemenation(matrixMaker matrix) {
		double[] solution = new double[matrix.matrix().length];
		for (int k=0;k<matrix.matrix().length;k++) {
			if (matrix.matrix()[k][k]==0)swap(matrix,k);
			for (int i = 0;i<matrix.matrix().length;i++) {
				if (i!=k) {
					double factor = matrix.matrix()[i][k] / matrix.matrix()[k][k];
					if (factor ==0)continue;
					for(int j=k; j<matrix.matrix()[0].length; j++) {
						matrix.matrix()[i][j] = matrix.matrix()[i][j] - factor*matrix.matrix()[k][j];
					}
				}
			}
		}
		for (int i=0;i<matrix.matrix().length;i++) {
			solution[i]=matrix.matrix()[i][matrix.matrix()[0].length-1]/matrix.matrix()[i][i];
		}
		System.out.print("\n\nGauss Jordan method Solution => ");
		matrix.print(solution);
		return solution;
	}
	
	private void swap(matrixMaker matrix,int k) {
		int i;
		for (i=k+1 ;i<matrix.matrix().length;i++) {
			if (matrix.matrix()[i][k]!=0) {
				matrix.swap(i, k);
				break;
			}
		}
		if (i==matrix.matrix().length) {
			System.out.println("Cannot Swap");
			//throw error
		}
	}

}
