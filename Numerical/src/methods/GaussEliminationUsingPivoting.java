package methods;

import matrices.matrixMaker;

public class GaussEliminationUsingPivoting extends GaussElimination {
	
	private boolean solutionExist = true;
	
	@Override
	protected void forwardElimination(matrixMaker mat) {
		//print
		steps.append("Forward elimination: \n");
		
		for(int k=0; k<mat.matrix().length-1; k++) {
			for(int i=k+1; i<mat.matrix().length; i++) {
				//partial pivoting
				partialPivoting(mat, k);
				// factor handling
				if(mat.matrix()[k][k] == 0) swap(mat, k);
				if(!solutionExist) return;
				double factor = mat.matrix()[i][k] / mat.matrix()[k][k];
				factor = tool.setpercision(factor, per);
				if(factor == 0) continue;
				//print
				steps.append("factor: "+factor+"\n");
				mat.matrix()[i][k] = 0;
				for(int j=k+1; j<mat.matrix()[0].length; j++) {
					mat.matrix()[i][j] = mat.matrix()[i][j] - factor*mat.matrix()[k][j];
					mat.matrix()[i][j] = tool.setpercision(mat.matrix()[i][j], per);
				}
				//print
				tool.AppendMatrixToString(steps ,mat.matrix());
			}
		}
		if(mat.matrix()[mat.matrix().length-1][mat.matrix().length-1] == 0) {
			solutionExist = false;
			throw new RuntimeException("no solution, row is full of zeros!");
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
		// print
		steps.append("swap rows: "+bigRaw+", "+k+"\n");
		tool.AppendMatrixToString(steps ,mat.matrix());	
	}
}
