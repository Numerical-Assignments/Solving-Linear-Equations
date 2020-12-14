package matrices;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class HelpTools {
	
	public StringBuilder AppendMatrixToString(StringBuilder str,double[][] matrix) {
		for (int i = 0 ;i<matrix.length;i++) {
			for(int j=0;j<matrix[i].length;j++) {
				str.append(matrix[i][j]+"\t");
			}
			str.append("\n");
		}
		str.append("======================================================================\n");
		return str;
	}
	
	public StringBuilder AppendVectorToString(StringBuilder str,double[] vector) {
		for (int i = 0 ;i<vector.length;i++) {
			str.append(vector[i]+"\t");
		}
		str.append("\n");
		str.append("======================================================================\n");
		return str;
	}
	
	public double setpercision(double Number, int percision) {
		Double truncatedDouble = BigDecimal.valueOf(Number)
			    .setScale(percision, RoundingMode.HALF_UP)
			    .doubleValue();
		return truncatedDouble;
	}
	
	public String SolutionToString(double[] solution) {
		StringBuilder str = new StringBuilder();
		for (int i =0;i<solution.length;i++) {
			str.append("X"+(i+1)+": "+solution[i]+"\t");
		}
		return str.toString();
	}
	
	public String readFromFile(String path) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(path));
		StringBuilder stringBuilder = new StringBuilder();
		String line = null;
		String ls = System.getProperty("line.separator");
		while ((line = reader.readLine()) != null) {
			stringBuilder.append(line);
			stringBuilder.append(ls);
		}
		// delete the last new line separator
		stringBuilder.deleteCharAt(stringBuilder.length() - 1);
		reader.close();
		return stringBuilder.toString();
	}
	
	public double[] handleIntialGuess(String input ) {
		String[] arrOfStr = input.split(",");
		double[] back = new double[arrOfStr.length];
		for(int i = 0 ; i < back.length ; i++) {
			back[i] = Double.parseDouble(arrOfStr[i]);
		}
		return back;
		
	}
	

}
