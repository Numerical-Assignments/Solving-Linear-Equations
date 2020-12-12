package methods;

public class LUDecomposition {
  static void LUDoolittle(double[][] mat, double [] b, int n) {
	    	
	        double[][] lower = new double[n][n];
	        double[][] upper = new double[n][n];
	 
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
	                    sum += (lower[i][j] * upper[j][k]);
	 
	                // Evaluating U(i, k)
	                upper[i][k] = mat[i][k] - sum;
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
	                        sum += (lower[k][j] * upper[j][i]);
	 
	                    // Evaluating L(k, i)
	                    lower[k][i]
	                        = (mat[k][i] - sum) / upper[i][i];
	                }
	            }
	        }
	        
	        System.out.println("Lower Triangular");
	       
	        // Displaying the result :
	        // Lower
	        
	        for (int i = 0; i < n; i++) {
	            for (int j = 0; j < n; j++) {
	                System.out.print(lower[i][j] + "\t");
	            }
	            System.out.print("\n");
	        }
	        
	        System.out.println("Upper Triangular");	        
	        
	        // Displaying the result :
            // Upper

	        for (int i = 0; i < n; i++) {
	            for (int j = 0; j < n; j++) {
	                System.out.print(upper[i][j] + "\t");
	            }
	            System.out.print("\n");
	        }
	        
	        //finding the solution 
	        
		    //LUX = B
	        //LY = B
	        //UX = Y
	        double ff[] = forward (lower, b);
		    
		    double bb[]= backward (upper, ff);
		    System.out.println("Solution");	 
		       
		    for (double element: bb) {
		    	System.out.println(element);
		    }
		    System.out.println();
	    }
  
  // Method 2 ( Cholesky LU Decomposition )
  
  static void LUCholesky(double[][] matrix, double [] b, int n) {
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
        					lower[j][j] = (int)Math.sqrt(matrix[j][j] - sum);
        			}
        			else {
        				// Evaluating L(i, j)
        				// using L(j, j)
        				for (int k = 0; k < j; k++)
        					sum += (lower[i][k] * lower[j][k]);
        					lower[i][j] = (matrix[i][j] - sum) / lower[j][j];
        			}
        		}
        	}
        	transpose(lower,lowerTranspose);
        	
            System.out.println("Lower Triangular");
    	       
        	// Displaying Lower
            
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    System.out.print(lower[i][j] + "\t");
                }
                System.out.print("\n");
            }
            
            System.out.println("Lower Triangular Transpose");	        
            
    	    // Transpose of
    	    // Lower Triangular

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    System.out.print(lowerTranspose[i][j] + "\t");
                }
                System.out.print("\n");
            }
            
    	    //LUX = B
            //LY = B
            //UX = Y
            
            double ff[] = forward (lower, b);
    	    double bb[]= backward (lowerTranspose, ff);
    	    System.out.println("Solution");	 
    	       
    	    for (double element: bb) {
    	    	System.out.println(element);
    	    }
    	    System.out.println();
    	}
    	
    	else {
    	    System.out.println("Not Symmetric Matrix :)");

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
  
    // doing backward substitution 

	public static double[] backward( double[][] mat,double b[]) {
		
		int nrow =mat.length;
		int ncol = mat[0].length;  
		
		double sol[] =new double[nrow];
	  
		for(int r=nrow-1; r>=0; r--) {
			double val =0;
			for(int c=ncol-1;c>r;c--) {
				val =val +  sol[c] * mat[r][c];  
			}
			val = b[r] - val;
			sol[r] = val/ mat[r][r]; 
	      	}     
		return sol; 
	}
	
  // doing forward substitution 
  
	public static double[] forward(double[][] mat,double b[]) {
	  
		int nrow =mat.length;  
		
		double sol[] =new double[nrow];
	  
		for (int r=0;r<nrow; r++) {
			double val =0;   
			for (int c=0;c<r; c++) {
				val =val +  sol[c] *mat[r][c];     
			}    
			val = b[r] - val;
			sol[r] = val/mat[r][r];
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
        double mat[][] = { { 4, 12, -16 },
				{ 12, 37, -43 },
				{ -16, -43, 98 } };
        double m[] = {6, 7, 15};
	
	LUDoolittle(mat, m, 3);
        LUCholesky(mat, m, 3);
    }
  
}
