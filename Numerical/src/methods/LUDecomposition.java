package methods;
import java.math.BigDecimal;
import java.math.RoundingMode; 
public class LUDecomposition {
	   public static String[] LUDoolittle(double[][] mat, double [] b, int n, int p) {
	    	
	        double[][] lower = new double[n][n];
	        double[][] upper = new double[n][n];
	        
	   	 	StringBuilder steps = new StringBuilder();
	   	 	StringBuilder output = new StringBuilder();
	   	 	String [] Solution = new String [2];
	   	 	
	        // Decomposing matrix into Upper and Lower
	        // triangular matrix
	        for (int i = 0; i < n; i++)
	        {
	            // Upper Triangular
	            for (int k = i; k < n; k++) 
	            {
	                // Summation of L(i, j) * U(j, k)
	               double sum = 0;
	                for (int j = 0; j < i; j++)
	                    sum += setpercision((lower[i][j] * upper[j][k]),p);
	                	sum = setpercision(sum, p);
	 
	                // Evaluating U(i, k)
	                upper[i][k] = setpercision(mat[i][k],p) - sum;
	                upper[i][k] = setpercision(upper[i][k], p);
	                
	            }
	 
	            // Lower Triangular
	            for (int k = i; k < n; k++) 
	            {
	                if (i == k)
	                    lower[i][i] = 1; // Diagonal as 1
	                else
	                {
	                    // Summation of L(k, j) * U(j, i)
	                    double sum = 0;
	                    for (int j = 0; j < i; j++)
	                        sum += setpercision((lower[k][j] * upper[j][i]), p);
	                		sum = setpercision(sum, p);

	                    // Evaluating L(k, i)
	                    lower[k][i] = (mat[k][i] - sum) / upper[i][i];
	                    lower[k][i] = setpercision(lower[k][i], p);
	                }
	            }
	        }
	        steps.append("Lower Triangular" +"\n");
	        //System.out.println("Lower Triangular");
	       
	        // Displaying the result :
	        // Lower
	        
	        for (int i = 0; i < n; i++) {
	            for (int j = 0; j < n; j++) {
	    	        steps.append(lower[i][j] + "\t");
	                //System.out.print(lower[i][j] + "\t");
	            }
		        steps.append("\n");
	            //System.out.print("\n");
	        }
	        steps.append("Upper Triangular" + "\n");
	        //System.out.println("Upper Triangular");	        
	        
	        // Displaying the result :
            // Upper

	        for (int i = 0; i < n; i++) {
	            for (int j = 0; j < n; j++) {
	    	        steps.append(upper[i][j] + "\t");
	                //System.out.print(upper[i][j] + "\t");
	            }
		        steps.append("\n");
	            //System.out.print("\n");
	        }
	        
	        //finding the solution 
	        
		    //LUX = B
	        //LY = B
	        //UX = Y
	        double ff[] = forward (lower, b, p);
		    double bb[]= backward (upper, ff, p);
	        
		    output.append("Solution" + "\n");
		    //System.out.println("Solution");	 
		       
		    for (double element: bb) {
			    output.append(element + "\n");
		    	//System.out.println(element);
		    }
	        steps.append("\n");
		    //System.out.println();
	        Solution[0] = steps.toString();
	        Solution[1] = output.toString();
		    return Solution;
		    }
	// Method  (Crout LU Method
	
	public static String[] LUCrout(double mat [][], double [] b, int n, int p) {
		int i, j, k;
		double sum = 0;
        double[][] lower = new double[n][n];
        double[][] upper = new double[n][n];
   	 	StringBuilder steps = new StringBuilder();
   	 	StringBuilder output = new StringBuilder();
   	 	String [] Solution = new String [2];

		for (i = 0; i < n; i++) {
			upper[i][i] = 1;
		}

		for (j = 0; j < n; j++) {
			for (i = j; i < n; i++) {
				sum = 0;
				for (k = 0; k < j; k++) {
					sum = sum + setpercision((lower[i][k] * upper[k][j]), p);
					sum = setpercision(sum,p);
				}
				lower[i][j] = setpercision(mat[i][j],p) - sum;
				lower[i][j] = setpercision(lower[i][j],p);
			}

			for (i = j; i < n; i++) {
				sum = 0;
				for(k = 0; k < j; k++) {
					sum = sum + setpercision((lower[j][k] * upper[k][i]),p);
					sum = setpercision(sum,p);

				}
				if (lower[j][j] == 0) {
					//System.out.println("det(L) close to 0!\n Can't divide by 0...\n");
					
					steps.append("det(L) close to 0!\n Can't divide by 0...\n");
			        Solution[1] = output.toString();
				    return Solution;
				}
				upper[j][i] = (setpercision(mat[j][i],p) - sum) / setpercision(lower[j][j],p);
				upper[j][i] = setpercision(upper[j][i], p);
			}
		}
		
        steps.append("Lower Triangular" +"\n");
        //System.out.println("Lower Triangular");
       
        // Displaying the result :
        // Lower
        
        for ( i = 0; i < n; i++) {
            for ( j = 0; j < n; j++) {
    	        steps.append(lower[i][j] + "\t");
                //System.out.print(lower[i][j] + "\t");
            }
	        steps.append("\n");
            //System.out.print("\n");
        }
        steps.append("Upper Triangular" + "\n");
        //System.out.println("Upper Triangular");	        
        
        // Displaying the result :
        // Upper

        for ( i = 0; i < n; i++) {
            for ( j = 0; j < n; j++) {
    	        steps.append(upper[i][j] + "\t");
                //System.out.print(upper[i][j] + "\t");
            }
	        steps.append("\n");
            //System.out.print("\n");
        }        
        double ff[] = forward (lower, b, p);
	    double bb[]= backward (upper, ff, p);
	    output.append("Solution" + "\n");
	    //System.out.println("Solution");	 
	       
	    for (double element: bb) {
		    output.append(element + "\n");
	    	//System.out.println(element);
	    }
        output.append("\n");
	    //System.out.println();
        Solution[0] = steps.toString();
        Solution[1] = output.toString();
	    return Solution;
        
	}	
  // Method 3 ( Cholesky LU Decomposition )
  
	public static String[] LUCholesky(double[][] matrix, double [] b, int n, int p) {
    	
   	 	StringBuilder steps = new StringBuilder();
   	 	StringBuilder output = new StringBuilder();
   	 	String [] Solution = new String [2];

		if (isSymmetric(matrix)) {
    		double[][] lower = new double[n][n];
        	double[][] lowerTranspose = new double[n][n];
        	// Decomposing a matrix
        	// into Lower Triangular
        	for (int i = 0; i < n; i++) {
        		for (int j = 0; j <= i; j++) {
        			double sum = 0;
        			// summation for diagonals
        			if (j == i) {
        				for (int k = 0; k < j; k++)
        					sum += (int)Math.pow(lower[j][k], 2);
        					sum = setpercision(sum, p);
        					lower[j][j] = (int)Math.sqrt(matrix[j][j] - sum);
        					lower[j][j] = setpercision(lower[j][j],p);
        			}
        			else {
        				// Evaluating L(i, j)
        				// using L(j, j)
        				for (int k = 0; k < j; k++)
        					sum += (lower[i][k] * lower[j][k]);
    						sum = setpercision(sum, p);
        					lower[i][j] = (matrix[i][j] - sum) / lower[j][j];
        					lower[i][j] = setpercision(lower[i][j],p);

        			}
        		}
        	}
        	transpose(lower,lowerTranspose);
        	
            steps.append("Lower Triangular" +"\n");
	        //System.out.println("Lower Triangular");
	       
	        // Displaying the result :
	        // Lower
	        
	        for (int i = 0; i < n; i++) {
	            for (int j = 0; j < n; j++) {
	    	        steps.append(lower[i][j] + "\t");
	                //System.out.print(lower[i][j] + "\t");
	            }
		        steps.append("\n");
	            //System.out.print("\n");
	        }
	        steps.append("Lower Triangular Transpose" + "\n");
	        //System.out.println("Lower Triangular Transpose");	        
	        
    	    // Transpose of
    	    // Lower Triangular

	        for (int i = 0; i < n; i++) {
	            for (int j = 0; j < n; j++) {
	    	        steps.append(lowerTranspose[i][j] + "\t");
	                //System.out.print(lowerTranspose[i][j] + "\t");
	            }
		        steps.append("\n");
	            //System.out.print("\n");
	        }
	        //finding the solution 
    	    //LUX = B
            //LY = B
            //UX = Y
            
            double ff[] = forward (lower, b, p);
    	    double bb[]= backward (lowerTranspose, ff, p);
		    output.append("Solution" + "\n");
		    //System.out.println("Solution");	 
		       
		    for (double element: bb) {
			    output.append(element + "\n");
		    	//System.out.println(element);
		    }
	        output.append("\n");
		    //System.out.println();
	        Solution[0] = steps.toString();
	        Solution[1] = output.toString();
		    return Solution;
    	}
    	
    	else {
    	    //System.out.println("Not Symmetric Matrix :)");
    	    steps.append("Not Symmetric Matrix :)"+"\n");
    	    output.append("Not Symmetric Matrix :)"+"\n");
	        Solution[1] = output.toString();
		    return Solution;
    	    
    	}
		
    }
	// To Check matrix is symmetric or not for cholesky decomposition
  
  	static boolean isSymmetric(double mat[][]) 
	{ 
	    for (int i = 0; i < mat.length; i++) 
	        for (int j = 0; j < mat[0].length; j++) 
	            if (mat[i][j] != mat[j][i]) 
	                return false; 
	    return true; 
	}
	
	// precision
  
	public static double setpercision(double Number, int percision) {
		Double truncatedDouble = BigDecimal.valueOf(Number).setScale(percision, RoundingMode.HALF_UP).doubleValue();
		return truncatedDouble;
	}
	
	// backward substitution
	
	public static double[] backward( double[][] mat,double b[], int p) {
		
		int nrow =mat.length;
		int ncol = mat[0].length;  
		
		double sol[] =new double[nrow];
	  
		for(int r=nrow-1; r>=0; r--) {
			double val =0;
			for(int c=ncol-1;c>r;c--) {
				val = val + setpercision((sol[c] * mat[r][c]), p);  
				val = setpercision(val, p);
			}
			val = setpercision(b[r],p) - val;
			sol[r] = val/ setpercision(mat[r][r],p);
			sol[r] = setpercision(sol[r], p);
	      	}     
		return sol; 
	}
	
	// forward substitution
	
	public static double[] forward(double[][] mat,double b[],int p) {
	  
		int nrow =mat.length;  
		
		double sol[] =new double[nrow];
	  
		for (int r=0;r<nrow; r++) {
			double val =0;   
			for (int c=0;c<r; c++) {
				val = val + setpercision((sol[c] * mat[r][c]), p);  
				val = setpercision(val, p);
			}    
			val = setpercision(b[r],p) - val;
			sol[r] = val/ setpercision(mat[r][r],p);
			sol[r] = setpercision(sol[r], p);
		}        
		return sol;
	}
	
  // to get the transpose of L matrix where A = L . Lt
	
	static void transpose(double A[][], double B [][]) { 
    	int i, j;
        for (i = 0; i < A.length; i++) 
            for (j = 0; j < A.length; j++) 
                B[i][j] = A[j][i];
    }
	
// Testing of both forms 
	
public static void main(String[] args) {
        int n = 3;
	int precision = 4;
	
	double mat[][] = { { 4, 12, -16 }, { 12, 37, -43 }, { -16, -43, 98 } };
        double b[] = {6, 7, 15};

	
	System.out.println(LUDoolittle(mat, b, n, precision)[0]);
        System.out.println(LUDoolittle(mat, b, n, precision)[1]);
        System.out.println(LUCrout(mat,b, n, precision)[0]);
        System.out.println(LUCrout(mat,b, n, precision)[1]);
        System.out.println(LUCholesky(mat, b, n, precision)[0]);
        System.out.println(LUCholesky(mat, b, n, precision)[1]);

    }
  
}