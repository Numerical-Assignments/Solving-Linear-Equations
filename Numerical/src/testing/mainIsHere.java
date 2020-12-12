package testing;

import methods.GaussElimination;
import methods.GaussEliminationUsingPivoting;
import methods.GaussJordan;
import methods.GaussSeidil;
import methods.JacobiIteration;

public class mainIsHere{

	public static void main(String[] args) {
		//System.out.println("Hello World!");
		double [][]matrix = {{1,2},{6,5}};
		double []bc = {3,4};
		GaussSeidil gs = new GaussSeidil();
		double[] s = gs.gaussSeidil(matrix, bc,1e-15, 7);
		for (int i =0 ; i< s.length;i++) {
			System.out.println(s[i]);
		}
		JacobiIteration j = new JacobiIteration();
		double[] ss = j.jacobi(matrix, bc,1e-15, 7);
		for (int i =0 ; i< ss.length;i++) {
			System.out.println(ss[i]);
		}
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
		
		GaussJordan jordan = new GaussJordan();
		jordan.solve(a.clone(), b.clone());

		


	}

}