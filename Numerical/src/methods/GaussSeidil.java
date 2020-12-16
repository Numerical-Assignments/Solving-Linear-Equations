package methods;

import java.util.Arrays;

import matrices.HelpTools;
import matrices.matrixMaker;

public class GaussSeidil {
	int max_itreation = 100;
	HelpTools help = new HelpTools();
	 matrixMaker matrix = new matrixMaker();
	 int n ;//= matrix.getMatrix().length;
	 double[] b;// = new double [n];
	 public StringBuilder steps = new StringBuilder();
	 public void transformToDominant (int[] rows) {
		 double[][] D = new double[n][n]; 
		 double [] bb = new double[n];
			for (int i = 0; i < rows.length; i++) { 
				for (int j = 0; j < n ; j++) {
						D[i][j] = matrix.matrix()[rows[i]][j];}
				bb[i] = b[rows[i]];
			}
		matrix.setMatrix(D);
		b = bb ;
	 }
	
	 
	 public boolean check (boolean[] visited,  int[] rows) {
		 double[][] M =  matrix.matrix();
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
	 
	 public double[] gaussSeidilwitherror (double[][] mat , double[] bb, double error,double[] intial,int percision) {
		 matrix.setMatrix(mat);
		 n=matrix.matrix().length;
		/* for(int i = 0 ; i < n ; i++) {
			 for (int j = 0 ; j < n ; j++) {
				 System.out.print(mat[i][j]+" ");
			 }
			 System.out.println();
		 }
		 for(int i = 0 ; i < n ; i++) {
			 System.out.print(bb[i]+" "); 
		 }
		 System.out.println();
		 for(int i = 0 ; i < n ; i++) {
			 System.out.print(intial[i]+" "); 
		 }*/
		 b = bb;
		 for(int i = 0 ; i < n ; i++) {
			 for (int j = 0 ; j < n ; j++) {
				 matrix.matrix()[i][j]=help.setpercision(matrix.matrix()[i][j], percision);
				 steps.append("("+matrix.matrix()[i][j]+")");
				 steps.append("X"+ (j+1) +" ");
				 if(j != n-1)  steps.append("+ ");
	            	//else steps.append("\n");
			 }
			 b[i] = help.setpercision(b[i], percision);
			 steps.append("= "+ b [i] +"\n");
		 }
		 steps.append("-----------------------------------"+"\n");
		 if(makeDominant()) {
			 steps.append("After making equations diagonally dominant :"+"\n");
			 double [][] M = matrix.matrix();
			 for(int i = 0 ; i < n ; i++) {
				 for (int j = 0 ; j < n ; j++) {
					 steps.append("("+matrix.matrix()[i][j]+")");
					 steps.append("X"+ (j+1) +" ");
					 if(j != n-1)  steps.append("+ ");
				 }
				 steps.append("= "+ b [i] +"\n");
			 }
			 steps.append("-----------------------------------"+"\n");
			 double[] X = new double[n]; // Approximations 
		     double[] P = new double[n]; // Prev 
		     X = intial;
		     int itreation = 0;
		    while(true) {
		            for (int i = 0; i < n; i++) { 
		                double sum = b[i]; // b_n 
		                for (int j = 0; j < n; j++) {
		                    if (j != i) 
		                        sum -= M[i][j] * X[j];
		               sum = help.setpercision(sum,percision);}
		                // Update xi to use in the next 
		                // row calculation 
		                X[i] = sum / M[i][i] ;
		                X[i] = help.setpercision(X[i], percision);
		            } 
		            steps.append("Itreation"+(itreation+1)+":  ");
		            itreation++;
		            for (int k = 0 ; k < n ; k++) {
		            	steps.append("X"+(k+1)+" = "+X[k]+" ");
		            	if(k != n-1)  steps.append(", ");
		            	else steps.append("\n");
		            }
		            if (itreation == 0) 
		                continue; 
		            boolean stop = true; 
		            for (int i = 0; i < n && stop; i++) 
		                if ((Math.abs(X[i] - P[i]) > error) && itreation < max_itreation) { 
		                    stop = false; 
		                    }
		            if (stop) 
		                break; 
		            P = (double[])X.clone(); 
		        }
		    steps.append("-----------------------------------"+"\n");
		    steps.append("The solution : "  );
            for (int k = 0 ; k < n ; k++) {
            	steps.append("X"+(k+1)+" = "+X[k]+" ");
            	if(k != n-1)  steps.append(", ");
            	else steps.append("\n");
            }
		    return X ;
		 }else {
			 throw new RuntimeException("The system isn't diagonally dominant .\n The method cannot guarantee convergence.");
		 }
		 
	 }
	 
	 public double[] gaussSeidilwithItrations (double[][] mat , double[] bb, int itreations,double[] intial,int percision )  {
		 matrix.setMatrix(mat);
		 n=matrix.matrix().length;
		 b = bb;
		 for(int i = 0 ; i < n ; i++) {
			 for (int j = 0 ; j < n ; j++) {
				 matrix.matrix()[i][j]=help.setpercision(matrix.matrix()[i][j], percision);
				 steps.append("("+matrix.matrix()[i][j]+")");
				 steps.append("X"+ (j+1) +" ");
				 if(j != n-1)  steps.append("+ ");
	            	//else steps.append("\n");
			 }
			 b[i] = help.setpercision(b[i], percision);
			 steps.append("= "+ b [i] +"\n");
		 }
		 steps.append("-----------------------------------"+"\n");
		 if(makeDominant()) {
			 steps.append("After making equations diagonally dominant :"+"\n");
			 double [][] M = matrix.matrix();
			 for(int i = 0 ; i < n ; i++) {
				 for (int j = 0 ; j < n ; j++) {
					 steps.append("("+matrix.matrix()[i][j]+")");
					 steps.append("X"+ (j+1) +" ");
					 if(j != n-1)  steps.append("+ ");
				 }
				 steps.append("= "+ b [i] +"\n");
			 }
			 steps.append("-----------------------------------"+"\n");
			 double[] X = new double[n]; // Approximations 
		     X = intial;
		    for(int itreation = 0 ;itreation < itreations ; itreation++){ 
		            for (int i = 0; i < n; i++) { 
		                double sum = b[i]; // b_n 
		                for (int j = 0; j < n; j++) {
		                    if (j != i) 
		                        sum -= M[i][j] * X[j];
		                sum = matrix.setpercision(sum,percision);}
		                // Update xi to use in the next 
		                // row calculation 
		                X[i] = sum / M[i][i] ;
		                X[i] = matrix.setpercision(X[i], percision);
		            } 
		            steps.append("Itreation"+(itreation+1)+":  ");
		            for (int k = 0 ; k < n ; k++) {
		            	steps.append("X"+(k+1)+" = "+X[k]+" ");
		            	if(k != n-1)  steps.append(", ");
		            	else steps.append("\n");
		            }
		        }
		    steps.append("-----------------------------------"+"\n");
		    steps.append("The solution : "  );
            for (int k = 0 ; k < n ; k++) {
            	steps.append("X"+(k+1)+" = "+X[k]+" ");
            	if(k != n-1)  steps.append(", ");
            	else steps.append("\n");
            }
		    return X ;
		 }else {
			 throw new RuntimeException("The system isn't diagonally dominant .\n The method cannot guarantee convergence.");
		 }
		 
	 }
	
	}
