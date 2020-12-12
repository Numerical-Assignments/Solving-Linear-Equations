package testing;

import methods.*;

public class mainIsHere{

	public static void main(String[] args) {
		//AX=B
		double[][] a = new double[][] {
			{2,1,4},
			{1,2,3},
			{4,-1,2}
		};
		double[] b = new double[]{1,1.5,2};
		
		GaussElimination gaussSolver = new GaussElimination();
		gaussSolver.solve(a.clone(), b.clone());
		
		System.out.println("\n\n");
		
		GaussEliminationUsingPivoting geup = new GaussEliminationUsingPivoting();
		geup.solve(a.clone(), b.clone());

	}

}