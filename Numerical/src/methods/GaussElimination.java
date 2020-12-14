package methods;

import matrices.*;

public class GaussElimination {
	
	private HelpTools tool = new HelpTools();
	private boolean solutionExist = true;
	protected StringBuilder steps = new StringBuilder(); 
	
	public double[] solve(double[][] a, double[] b) {
		//create appendix matrix
		matrixMaker mat = new matrixMaker(a,b);
		//print
		steps.append("appendix matrix: \n");
		tool.AppendMatrixToString(steps ,mat.matrix());
		
		forwardElimination(mat);
		if(solutionExist)
			backSubstitution(mat);
		
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
				if(factor == 0) continue;
				//print
				steps.append("factor: "+factor+"\n");
				for(int j=k; j<mat.matrix()[0].length; j++) {
					mat.matrix()[i][j] = mat.matrix()[i][j] - factor*mat.matrix()[k][j];
				}
				//print
				tool.AppendMatrixToString(steps ,mat.matrix());
			}
		}
		if(mat.matrix()[mat.matrix().length-1][mat.matrix().length-1] == 0) {
			solutionExist = false;
			System.out.println("no solution, row is full of zeros!");
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
			for(int j=i+1; j<l; j++) 
				sum = sum + mat.matrix()[i][j] * x[j];
			x[i] = (mat.matrix()[i][l] - sum) / mat.matrix()[i][i];
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
		if(!solutionExist) System.out.println("no solution, column is full of zeroes!");
		else {
			//print
			System.out.println("swap rows: "+i+", "+k);
			mat.print(mat.matrix());
		}
	}
	
	protected void printToBuilder(double[][] m) {
		for(int i=0; i<m.length; i++) {
			for(int j=0; j<m[i].length-1; j++) {
				steps.append(m[i][j]+",   ");
			}
			steps.append(m[i][m[i].length-1]);
			steps.append("\n");
		}
	}
	
	protected void printToBuilder(double[] v) {
		for(int i=0; i<v.length-1; i++) {
			steps.append(v[i]+", ");
		}
		steps.append(v[v.length-1]+"\n");
	}
	
	public String steps() {
		return steps.toString();
	}
}
