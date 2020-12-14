package matrices;

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

}
