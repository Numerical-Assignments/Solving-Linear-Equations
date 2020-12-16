package testing;

import methods.*;
import matrices.*;

public class newMain {

	public static void main(String[] args) {
		youssef();

	}
	
	public static void abdallah() {
		
	}
	public static void ashraf() {
		
	}
	public static void tarek() {
		
	}
	public static void youssef() {
		double[][] a = new double[][] {
			{2,1,4},
			{1,2,3},
			{4,-1,2}
		};
		double[] b = new double[]{1,1.5,2};
		
		GaussElimination gaussSolver = new GaussElimination();
		gaussSolver.solve(a.clone(), b.clone());
		System.out.print(gaussSolver.steps());
		
		System.out.println("\n\n");
		
		GaussEliminationUsingPivoting geup = new GaussEliminationUsingPivoting();
		geup.solve(a.clone(), b.clone());
		System.out.print(geup.steps());
		
	}

}
