package testing;

import methods.GaussSeidil;
import methods.JacobiIteration;

public class mainIsHere{

	public static void main(String[] args) {
		//System.out.println("Hello World!");
		double [][]matrix = {{1,2},{6,5}};
		double []b = {3,4};
		GaussSeidil gs = new GaussSeidil();
		double[] s = gs.gaussSeidil(matrix, b,1e-15, 7);
		for (int i =0 ; i< s.length;i++) {
			System.out.println(s[i]);
		}
		JacobiIteration j = new JacobiIteration();
		double[] ss = j.jacobi(matrix, b,1e-15, 7);
		for (int i =0 ; i< ss.length;i++) {
			System.out.println(ss[i]);
		}

	}

}