package matrices;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class Handling {
	
	static double setpercision(double Number, int percision) {
		Double truncatedDouble = BigDecimal.valueOf(Number).setScale(percision, RoundingMode.HALF_UP).doubleValue();
		return truncatedDouble;
	}
	
	static boolean simpleCheck (String input) {
		String[] lines = input.split("\r\n|\r|\n");
		char someChar= ',';
		int counter = 0;
		int k = 0;
		int m = 0;
		int [] checkArray = new int [lines.length];
		for (int i = 0; i < lines.length; i++) {
			for (int j =0; j < lines[i].length(); j++) {
				if (lines[i].charAt(j) == someChar) {
					counter++;
				}
			}
			checkArray[k] = counter;
			k++;
			counter = 0;
		}
		for (int i=0; i<checkArray.length-1; i++) {
			if(checkArray[0] == checkArray[i+1]  ) {
				m++;
			}
		}
		if (m == checkArray.length-1) {
			return true;
		}
		else {
			return false;
		}
	}
	static int numOfLines (String input) {
		String[] lines = input.split("\r\n|\r|\n");
		return lines.length;
	}
	
	
	static String recievedInitialGuess (String input, int p, int n, double b[]) {
   	 	StringBuilder observedErrors = new StringBuilder();
   	 	
		String[] arrOfStr = input.split("[,]");
		
		if (arrOfStr.length == n) {
			for(int i=0; i<arrOfStr.length; i++) {
	    		try {
	    			double x = Double.parseDouble(arrOfStr[i]) ;    			
	    			x = setpercision(x,p);
	    			b[i] = x;
	    		}
	    		catch (NumberFormatException nfe) {
	    			for(int j=0; i<b.length; i++) {
	    				b[j] = 0;
	    			}
	    			observedErrors.append("Alert: WRONG INPUT!");
	    			return observedErrors.toString();
	    		}
			}
			return observedErrors.toString();
		}
		else {
			observedErrors.append("Alert: Initial Guess Matrix is of DIFFERENT SIZE! Or Wrong Initial Guess");
			return observedErrors.toString();
		}
		
		
	}
	
	static ArrayList<Double> recievedText (String input, int p) {
   	 	
    	String[] arrOfStr = input.split("[\n,]"); 
    	ArrayList<Double> aList = new ArrayList<>();
    	
    	for(int i=0; i<arrOfStr.length; i++) {
    		try {
        		double x = Double.parseDouble(arrOfStr[i]) ;    			
        		x = setpercision(x,p);
        		aList.add(x);
    		}
    		catch (NumberFormatException nfe) {
    			aList.clear();
    			return aList; 
    		}
    	}
    	return aList;
	}
	
	static String handeledMatrix (String input, ArrayList<Double> list, int n, double[][] mat, double b[]) {
		
		StringBuilder observedErrors = new StringBuilder();
		double indexOfMatrixB = n;
		double x = indexOfMatrixB + 1;
		int j=0;
		int k=0;
		double a[] = new double[n*n];
		
	if (simpleCheck(input)) {
		
		if (list.isEmpty()) {
			observedErrors.append("Alert: WRONG INPUT!");
			return observedErrors.toString();
		}
		
		else if (list.size() != (n*n + n)) {
			list.clear();
			observedErrors.append("Alert: WRONG INPUT!");
			return observedErrors.toString();

		}
		else {	
			for(int i=0; i<list.size(); i++) {
				if (i == (indexOfMatrixB)) {
					indexOfMatrixB = indexOfMatrixB + x;
					b[j] = list.get(i); 
					j++;
				}
				else {
					a[k] = list.get(i);
					k++;
				}
			}
			
			if (a.length == n*n) {
				int l=0;
				int u=0;
				for(int i=0; i<a.length; i++) {
					mat[u][l] = a[i];
					l++;
					if (l == n) {
						l=0;
						u++;
					}
				}
			}
		}
		return observedErrors.toString();
	}
	else {
		observedErrors.append("Alert: WRONG INPUT!");
		return observedErrors.toString();

	}
}

    public static void main(String[] args) {

        String input = "5,91,,4\n1,-3,8,4\n1,2,3,9";
        String initial = "5,4=6";
    	String errorInitial;
    	String errorInput;
    	
        int precision = 2;
    	int sizeOfArray = numOfLines(input);
  	    
    	double A[][] = new double [sizeOfArray][sizeOfArray];   
        double B[] = new double [sizeOfArray];
        double I[] = new double [sizeOfArray];
        	
        errorInput = handeledMatrix(input, recievedText (input, precision) ,sizeOfArray , A, B);
        errorInitial = recievedInitialGuess (initial, precision, sizeOfArray, I);
            
        for (int i = 0; i < sizeOfArray; i++) {
        	for (int j = 0; j < sizeOfArray; j++) {
        		System.out.print(A[i][j] + "\t");
            }
            System.out.print("\n");
        }
        System.out.print("\n");

    	for (double element: B) {
    	   System.out.println(element);
    	}
        System.out.print("\n");

    	for (double element: I) {
    		System.out.println(element);
    	}
    	    
        System.out.print("\n");
        System.out.print(errorInput);
        System.out.print("\n");
        System.out.print(errorInitial);

    }

}
