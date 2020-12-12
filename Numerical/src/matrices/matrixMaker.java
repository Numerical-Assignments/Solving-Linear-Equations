package matrices;

public class matrixMaker {
	private double matrix[][];
	
	public matrixMaker() {}
	
	public matrixMaker(double[][] newMatrix) {
		this.matrix = newMatrix;
	}
	// appendix matrix
	public matrixMaker(double[][] A, double[] B) {
		matrix = new double[A.length][A[0].length+1];
		for(int i=0; i<A.length; i++) {
			for(int j=0; j<A[i].length; j++) {
				matrix[i][j] = A[i][j];
			}
			matrix[i][A[i].length] = B[i];
		}
	}
	
	public void setMatrix(double[][] newMatrix){
		this.matrix = newMatrix;
	}
	
	public double[][] matrix(){
		return matrix;
	}
	
	// swap two rows
	public void swap(int row1, int row2) {
		double[] temp = matrix[row1];
		matrix[row1] = matrix[row2];
		matrix[row2] = temp;
	}
	
	
	// print matrix
	public void print(double[][] m) {
		for(int i=0; i<m.length; i++) {
			for(int j=0; j<m[i].length-1; j++) {
				System.out.print(m[i][j]+",   ");
			}
			System.out.print(m[i][m[i].length-1]);
			System.out.println("");
		}
	}
	// print vector
	public void print(double[] v) {
		for(int i=0; i<v.length-1; i++) {
			System.out.print(v[i]+", ");
		}
		System.out.print(v[v.length-1]);
	}
}
