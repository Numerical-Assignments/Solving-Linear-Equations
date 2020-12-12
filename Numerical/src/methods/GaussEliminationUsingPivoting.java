package methods;

import matrices.matrixMaker;

public class GaussEliminationUsingPivoting extends GaussElimination {
	
	private boolean solutionExist = true;
	
	@Override
	protected void forwardElimination(matrixMaker mat) {
		//print
		mat.print(mat.matrix());
		
		for(int k=0; k<mat.matrix().length-1; k++) {
			//print
			System.out.println("k: "+k);
			for(int i=k+1; i<mat.matrix().length; i++) {
				//partial pivoting
				partialPivoting(mat, k);
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
	
	private void partialPivoting(matrixMaker mat, int k) {
		double bigNum = mat.matrix()[k][k];
		int bigRaw = k;
		for(int i=k+1; i<mat.matrix().length; i++) {
			if(mat.matrix()[i][k] > bigNum) {
				bigNum = mat.matrix()[i][k];
				bigRaw = i;
			}
		}
		mat.swap(bigRaw, k);
		System.out.println("swap rows: "+bigRaw+", "+k);
		mat.print(mat.matrix());	
	}
}
