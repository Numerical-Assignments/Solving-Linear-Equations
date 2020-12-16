package testing;

import methods.*;
import matrices.*;

public class newMain {

	public static void main(String[] args) {
		//youssef();
		ashraf();
	}
	
	public static void abdallah() {
		
	}
	public static void ashraf() {
		JacobiIteration s = new JacobiIteration();
		double intial[] = {0,0,0};
		double bb[]= {5,7,7};
		double mat [][] = {{3,1,1},{2,4,1},{1,1,5}};
		s.jacobiwitherror(mat, bb, 0.0000001, intial, 4);
		System.out.print(s.steps);
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
		gaussSolver.solve(a.clone(), b.clone(),4);
		System.out.print(gaussSolver.steps());
		
		System.out.println("\n\n");
		
		GaussEliminationUsingPivoting geup = new GaussEliminationUsingPivoting();
		geup.solve(a.clone(), b.clone(),4);
		System.out.print(geup.steps());
		
	}

}
