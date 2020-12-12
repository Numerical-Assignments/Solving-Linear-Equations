package methods;

import java.util.Arrays;


import matrices.matrixMaker;

public class GaussSeidil {

	 matrixMaker matrix = new matrixMaker();
	 int n ;//= matrix.getMatrix().length;
	 double[] b;// = new double [n];
	 
	 public void transformToDominant (int[] rows) {
		 double[][] D = new double[n][n]; 
		 double [] bb = new double[n];
			for (int i = 0; i < rows.length; i++) { 
				for (int j = 0; j < n ; j++) {
						D[i][j] = matrix.getMatrix()[rows[i]][j];}
				bb[i] = b[rows[i]];
			}
		matrix.setMatrix(D);
		b = bb ;
	 }
	
	 
	 public boolean check (boolean[] visited,  int[] rows) {
		 double[][] M =  matrix.getMatrix();
		 int row = 0;
		 boolean big = false;
		 for (int i = 0; i < n ; i++) { 
			 	if ( row == n ) {
			 		transformToDominant(rows);
			 		break;
			 	}
				if (visited[i]) { continue; }
				double sum = 0;
				for (int j = 0; j < n; j++) {
	                sum += Math.abs(M[i][j]);} 
	            if (2 * Math.abs(M[i][row]) >= sum) {
	            	if (2 * Math.abs(M[i][row]) > sum) {big = true;}
	            	 visited[i] = true; 
		             rows[row] = i;
		             i = -1;
		             row++;
	            }
		 }
		 if (big && (row==n)) {return true;}
		 
		 return false;
	 }
	 
	 public boolean makeDominant(){ 
	        boolean[] visited = new boolean[n]; 
	        int[] rows = new int[n]; 
	        Arrays.fill(visited, false);
	        Arrays.fill(rows, 0);
	        return check(visited, rows); 
	    } 
	 
	 public double[] gaussSeidil (double[][] mat , double[] bb, double error, int itreations) {
		 matrix.setMatrix(mat);
		 n=matrix.getMatrix().length;
		 b = bb;
		 
		 if(makeDominant()) {
			 double [][] M = matrix.getMatrix();
			 double[] X = new double[n]; // Approximations 
		     double[] P = new double[n]; // Prev 
		     Arrays.fill(X, 0);
		    for(int itreation = 0 ;itreation < itreations ; itreation++){ 
		            for (int i = 0; i < n; i++) { 
		                double sum = b[i]; // b_n 
		                for (int j = 0; j < n; j++) 
		                    if (j != i) 
		                        sum -= M[i][j] * X[j]; 
		                // Update xi to use in the next 
		                // row calculation 
		                X[i] = sum / M[i][i] ; 
		            } 
		            
		            if (itreation == 0) 
		                continue; 
		            boolean stop = true; 
		            for (int i = 0; i < n && stop; i++) 
		                if (Math.abs(X[i] - P[i]) > error) 
		                    stop = false; 
		            if (stop) 
		                break; 
		            P = (double[])X.clone(); 
		        }
		    return X ;
		 }
		return null;
		 
	 }
	
	}
