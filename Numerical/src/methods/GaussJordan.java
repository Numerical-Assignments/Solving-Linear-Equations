package methods;

import matrices.HelpTools;
import matrices.matrixMaker;

public class GaussJordan {
	private StringBuilder steps;
	HelpTools help = new HelpTools();

	public double[] solve(double[][] a, double[] b, int percision) {
		steps = new StringBuilder();
		//create augmented matrix
		matrixMaker matrix = new matrixMaker(a,b);
		steps.append("Augmented Matrix: \n");
		steps=help.AppendMatrixToString(steps, matrix.matrix());
		return JordanElemenation(matrix,percision);
	}
	
	private double[] JordanElemenation(matrixMaker matrix,int percision) {
		double[] solution = new double[matrix.matrix().length];
		for (int k=0;k<matrix.matrix().length;k++) {
			if (matrix.matrix()[k][k]==0)swap(matrix,k);
			for (int i = 0;i<matrix.matrix().length;i++) {
				if (i!=k) {
					double factor = matrix.matrix()[i][k] / matrix.matrix()[k][k];
					if (factor ==0)continue;
					steps.append("Factor: "+factor+"\t==> Base Row: "+(k+1)+", Secondary Row: "+(i+1)+" .\n");
					for(int j=k; j<matrix.matrix()[0].length; j++) {
						matrix.matrix()[i][j] = matrix.matrix()[i][j] - factor*matrix.matrix()[k][j];
						matrix.matrix()[i][j]=help.setpercision(matrix.matrix()[i][j], percision);
					}
					steps = help.AppendMatrixToString(steps, matrix.matrix());
				}
			}
		}
		for (int i=0;i<matrix.matrix().length;i++) {
			solution[i]=matrix.matrix()[i][matrix.matrix()[0].length-1]/matrix.matrix()[i][i];
		}
		steps.append("\nGauss Jordan method Solution => ");
		steps=help.AppendVectorToString(steps, solution);
		System.out.println(steps);
		return solution;
	}
	
	private void swap(matrixMaker matrix,int k) {
		int i;
		for (i=k+1 ;i<matrix.matrix().length;i++) {
			if (matrix.matrix()[i][k]!=0) {
				matrix.swap(i, k);
				steps.append("Swap rows: "+i+","+k+".\n");
				steps=help.AppendMatrixToString(steps, matrix.matrix());
				break;
			}
		}
		if (i==matrix.matrix().length) {
			System.out.println("Cannot Swap");
			//throw error
		}
	}

	public StringBuilder getSteps() {
		return steps;
	}
	


}
