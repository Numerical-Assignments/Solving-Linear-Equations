package testing;

import methods.*;

public class newMain {

	public static void main(String[] args) {
		ashraf();
	}
	
	public static void abdallah() {
		
	}
	public static void ashraf() {
		JacobiIteration s = new JacobiIteration();
		double intial[] = {0,0,0};
		double bb[]= {5,7,5};
		double mat [][] = {{3,1,1},{2,4,1},{1,1,3}};
		s.jacobiwitherror(mat, bb, 1E-7, intial, 4);
		System.out.print(s.steps);
	}
	public static void tarek() {
		int n = 3;
		int precision = 4;
		
		double mat[][] = { { 4, 12, -16 }, { 12, 37, -43 }, { -16, -43, 98 } };
	    double b[] = {6, 7, 15};

		
		System.out.println(LUDecomposition.LUDoolittle(mat, b, n, precision)[0]);
	    System.out.println(LUDecomposition.LUDoolittle(mat, b, n, precision)[1]);
	    System.out.println(LUDecomposition.LUCrout(mat,b, n, precision)[0]);
	    System.out.println(LUDecomposition.LUCrout(mat,b, n, precision)[1]);
	    System.out.println(LUDecomposition.LUCholesky(mat, b, n, precision)[0]);
	    System.out.println(LUDecomposition.LUCholesky(mat, b, n, precision)[1]);
	}
	public static void youssef() {
		double[][] a = new double[][] {
			{2,1,4},
			{1,2,3},
			{4,-1,2}
		};
		double[] b = new double[]{1,1.5,2};
		
		GaussElimination gaussSolver = new GaussElimination();
		gaussSolver.solve(a.clone(), b.clone(), 4);
		System.out.print(gaussSolver.steps());
		
		System.out.println("\n\n");
		
		GaussEliminationUsingPivoting geup = new GaussEliminationUsingPivoting();
		geup.solve(a.clone(), b.clone(),4);
		System.out.print(geup.steps());
		
	}

}
