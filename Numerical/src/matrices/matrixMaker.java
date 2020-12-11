package matrices;

public class matrixMaker {
	private double matrix[][];
	
	public matrixMaker() {}
	
	public matrixMaker(double[][] newMatrix) {
		this.matrix = newMatrix;
	}
	
	public void setMatrix(double[][] newMatrix){
		this.matrix = newMatrix;
	}
	
	public double[][] getMatrix(){
		return matrix;
	}
	
	
}
