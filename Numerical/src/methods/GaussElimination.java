package methods;

import matrices.matrixMaker;

public class GaussElimination {
	
	private boolean solutionExist = true;
	
	public double[] solve(double[][] a, double[] b) {
		//create appendix matrix
		matrixMaker mat = new matrixMaker(a,b);
		
		forwardElimination(mat);
		if(solutionExist)
			backSubstitution(mat);
		
		return null;
	}
	
	protected void forwardElimination(matrixMaker mat) {
		//print
		mat.print(mat.matrix());
		
		for(int k=0; k<mat.matrix().length-1; k++) {
			//print
			System.out.println("k: "+k);
			for(int i=k+1; i<mat.matrix().length; i++) {
				// factor handling
				if(mat.matrix()[k][k] == 0) swap(mat, k);
				if(!solutionExist) return;
				double factor = mat.matrix()[i][k] / mat.matrix()[k][k];
				if(factor == 0) continue;
				//print
				System.out.println("factor: "+factor);
				for(int j=k; j<mat.matrix()[0].length; j++) {
					mat.matrix()[i][j] = mat.matrix()[i][j] - factor*mat.matrix()[k][j];
				}
				//print
				mat.print(mat.matrix());
			}
		}
		if(mat.matrix()[mat.matrix().length-1][mat.matrix().length-1] == 0) {
			solutionExist = false;
			System.out.println("no solution, row is full of zeros!");
		}
	}
	
	protected double[] backSubstitution(matrixMaker mat) {
		int l = mat.matrix().length;
		double[] x = new double[l];
		
		x[l-1] = mat.matrix()[l-1][l]/mat.matrix()[l-1][l-1];
		for(int i=l-1; i>=0; i--) {
			double sum = 0;
			for(int j=i+1; j<l; j++) 
				sum = sum + mat.matrix()[i][j] * x[j];
			x[i] = (mat.matrix()[i][l] - sum) / mat.matrix()[i][i];
		}
		//print
		System.out.println("solution: ");
		mat.print(x);
		return x;
	}
	
	protected void swap(matrixMaker mat, int k) {
		solutionExist = false;
		int i;
		for(i = k+1; i<mat.matrix().length; i++) {
			if(mat.matrix()[i][k] != 0) {
				solutionExist = true;
				mat.swap(i, k);
				break;
			}
		}
		if(!solutionExist) System.out.println("no solution, column is full of zeroes!");
		else {
			//print
			System.out.println("swap rows: "+i+", "+k);
			mat.print(mat.matrix());
		}
	}
}
