package methods;

import matrices.*;

public class GaussElimination {
	
	private boolean solutionExist = true;
	protected int per = 4;
	protected HelpTools tool = new HelpTools();
	protected StringBuilder steps = new StringBuilder(); 
	
	public double[] solve(double[][] a, double[] b, int percision) {
		per = percision;
		//create appendix matrix
		matrixMaker mat = new matrixMaker(a,b);
		//print
		steps.append("appendix matrix: \n");
		tool.AppendMatrixToString(steps ,mat.matrix());
		
		forwardElimination(mat);
		if(solutionExist)
			return backSubstitution(mat);
		
		return null;
	}
	
	protected void forwardElimination(matrixMaker mat) {
		//print
		steps.append("Forward elimination: \n");
		
		for(int k=0; k<mat.matrix().length-1; k++) {
			for(int i=k+1; i<mat.matrix().length; i++) {
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
	
	protected double[] backSubstitution(matrixMaker mat) {
		//print
		steps.append("Solutions after back substitution :\n");
		
		int l = mat.matrix().length;
		double[] x = new double[l];
		
		x[l-1] = mat.matrix()[l-1][l]/mat.matrix()[l-1][l-1];
		for(int i=l-1; i>=0; i--) {
			double sum = 0;
			for(int j=i+1; j<l; j++) {
				sum = sum + mat.matrix()[i][j] * x[j];
				sum = tool.setpercision(sum, per);
			}
			
			x[i] = (mat.matrix()[i][l] - sum) / mat.matrix()[i][i];
			x[i] = tool.setpercision(x[i], per);
		}
		//print
		tool.AppendVectorToString(steps ,x);
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
		if(!solutionExist) throw new RuntimeException("no solution, column is full of zeroes!");
		else {
			//print
			steps.append("swap rows: "+i+", "+k+"\n");
			tool.AppendMatrixToString(steps ,mat.matrix());
		}
	}
	
	public String steps() {
		return steps.toString();
	}
}
