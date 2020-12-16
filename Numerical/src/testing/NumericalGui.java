package testing;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.management.RuntimeErrorException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.filechooser.FileNameExtensionFilter;

import matrices.Handling;
import matrices.HelpTools;
import methods.GaussElimination;
import methods.GaussEliminationUsingPivoting;
import methods.GaussJordan;
import methods.GaussSeidil;
import methods.JacobiIteration;
import methods.LUDecomposition;

public class NumericalGui {

	private HelpTools help = new HelpTools();
	
	
	private String method = "Choose method";
	private String parameter= "Choose parameter";
	
	private JFrame frame;
	private JScrollPane equationScrollPane;
	private JLabel lblParameter;
	private JScrollPane parameterScrollPane;
	private JLabel lblInitialGuess;
	private JScrollPane initialScrollPane;
	private JComboBox ParametersComboBox;
	private JLabel lblParameters;
	private JTextArea equationTextArea;
	private JTextArea stepsTextArea;
	private JTextArea outputTextArea;
	private JTextArea initialTextArea;
	private JTextArea parameterTextArea;
	private JSpinner spinner;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NumericalGui window = new NumericalGui();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public NumericalGui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.getContentPane().setBackground(new Color(0, 0, 0));
		frame.setBounds(100, 100, 1100, 600);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JComboBox MethodsComboBox = new JComboBox();
		MethodsComboBox.setFont(new Font("Tempus Sans ITC", Font.BOLD, 15));
		MethodsComboBox.setBounds(101, 343, 248, 20);
		MethodsComboBox.setModel(new DefaultComboBoxModel(
				new String[] { "Choose Method?", "Gauss Elimination.", "Gauss Elimination using pivoting.",
						"Gauss Jordan.", "LU Decomposition.", "Gauss Seidil.", "Jacobi Iteration" }));
		frame.getContentPane().add(MethodsComboBox);
		MethodsComboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				method = MethodsComboBox.getSelectedItem().toString();
				switch (method) {
				/*case "Gauss Elimination.":
					equationScrollPane.setBounds(10, 87, 452, 250);
					ParametersComboBox.setVisible(false);
					lblParameters.setVisible(false);
					break;
				case "Gauss Elimination using pivoting.":
					equationScrollPane.setBounds(10, 87, 452, 250);
					ParametersComboBox.setVisible(false);
					lblParameters.setVisible(false);
					break;
				case "Gauss Jordan.":
					equationScrollPane.setBounds(10, 87, 452, 250);
					ParametersComboBox.setVisible(false);
					lblParameters.setVisible(false);
					break;*/
				case "LU Decomposition.":
					equationScrollPane.setBounds(10, 87, 452, 250);
					ParametersComboBox.setModel(new DefaultComboBoxModel(
							new String[] { "Choose Format?", "Downlittle Form", "Crout Form", "Cholesky Form" }));
					lblParameters.setText("Format:");
					lblParameters.setVisible(true);
					ParametersComboBox.setVisible(true);
					break;
				case "Gauss Seidil." :
					equationScrollPane.setBounds(10, 87, 452, 160);
					lblInitialGuess.setVisible(true);
					initialScrollPane.setVisible(true);
					ParametersComboBox.setModel(new DefaultComboBoxModel(
							new String[] { "Stop Condition?", "Number of iterations", "Absolute Relative Error" }));
					lblParameters.setText("Condition:");
					lblParameters.setVisible(true);
					ParametersComboBox.setVisible(true);
					break;
				case "Jacobi Iteration":
					equationScrollPane.setBounds(10, 87, 452, 160);
					lblInitialGuess.setVisible(true);
					initialScrollPane.setVisible(true);
					ParametersComboBox.setModel(new DefaultComboBoxModel(
							new String[] { "Choose Paremeter?", "Number of iterations", "Absolute Relative Error" }));
					lblParameters.setText("Condition:");
					lblParameters.setVisible(true);
					ParametersComboBox.setVisible(true);
					break;
				default:
					equationScrollPane.setBounds(10, 87, 452, 250);
					ParametersComboBox.setVisible(false);
					lblParameters.setVisible(false);
					parameter = "";
				}
				// System.out.println(MethodsComboBox.getSelectedItem().toString());

			}
		});

		ParametersComboBox = new JComboBox();
		ParametersComboBox.setFont(new Font("Tempus Sans ITC", Font.BOLD, 15));
		ParametersComboBox.setBounds(132, 396, 191, 20);
		//ParametersComboBox.setModel(new DefaultComboBoxModel(new String[] { "Choose Paremeter?" }));
		ParametersComboBox.setVisible(false);
		frame.getContentPane().add(ParametersComboBox);
		ParametersComboBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				parameter = ParametersComboBox.getSelectedItem().toString();
				switch (parameter) {
				case "Number of iterations":
					lblParameter.setText("# Iterations:");
					parameterScrollPane.setVisible(true);
					lblParameter.setVisible(true);
					break;
				case "Absolute Relative Error":
					lblParameter.setText("Relative error:");
					parameterScrollPane.setVisible(true);
					lblParameter.setVisible(true);
					break;
				default:
					parameterScrollPane.setVisible(false);
					lblParameter.setVisible(false);
				}
				
			}
		});

		JButton btnLoadFile = new JButton("Load File");
		btnLoadFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fc = new JFileChooser(new File("c:\\temp"));
				fc.setDialogTitle("Load a file");
				fc.setFileFilter(new FileNameExtensionFilter("TextFile","txt"));
				int returnValue =fc.showOpenDialog(null);
				if (returnValue==JFileChooser.APPROVE_OPTION) {
					File selectedFile = fc.getSelectedFile();
					try {
						equationTextArea.setText(help.readFromFile(selectedFile.getAbsolutePath()));
					} catch (IOException e) {
						JOptionPane.showMessageDialog(null, e.getMessage());
					}
				}
			}
		});
		btnLoadFile.setFont(new Font("Tempus Sans ITC", Font.BOLD, 15));
		btnLoadFile.setBounds(10, 11, 109, 23);
		frame.getContentPane().add(btnLoadFile);

		JButton btnOk = new JButton("Ok");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					double[] solution;
					String equation = equationTextArea.getText();
					int sizeOfArray = Handling.numOfLines(equation);
					double A[][] = new double [sizeOfArray][sizeOfArray];   
			        double B[] = new double [sizeOfArray];		        
					int percision = (int) spinner.getValue();
					Handling.handeledMatrix(equation, Handling.recievedText (equation, percision),sizeOfArray, A, B);
					//errorInitial = recievedInitialGuess (initial, precision, sizeOfArray, I);
					switch (method) {
					case "Gauss Elimination.":
						GaussElimination g = new GaussElimination();
						solution= g.solve(A, B,percision);
						stepsTextArea.setText(g.steps());
						outputTextArea.setText(help.SolutionToString(solution));
						break;
					case "Gauss Elimination using pivoting.":
						GaussEliminationUsingPivoting gp = new GaussEliminationUsingPivoting();
						solution= gp.solve(A, B,percision);
						stepsTextArea.setText(gp.steps());
						outputTextArea.setText(help.SolutionToString(solution));
						break;
					case "Gauss Jordan.":
						GaussJordan gj = new GaussJordan();
						solution= gj.solve(A, B,percision);
						stepsTextArea.setText(gj.getSteps());
						outputTextArea.setText(help.SolutionToString(solution));
						break;
					case "LU Decomposition.":
						String[] S = null;
						switch (parameter) {
						case "Downlittle Form":
							S=LUDecomposition.LUDoolittle(A, B,sizeOfArray,percision);
							break;
						case "Crout Form":
							S=LUDecomposition.LUCrout(A, B,sizeOfArray,percision);
							break;
						case "Cholesky Form":
							S=LUDecomposition.LUCholesky(A, B,sizeOfArray,percision);
							break;
						default:
							throw new RuntimeErrorException(null, "Please Choose the Required Form");
						}
						stepsTextArea.setText(S[0]);
						outputTextArea.setText(S[1]);
						break;
					case "Gauss Seidil." :
						GaussSeidil gs = new GaussSeidil();
						double [] initial = new double[sizeOfArray];
						Handling.recievedInitialGuess (initialTextArea.getText(), percision, sizeOfArray, initial);
						switch (parameter) {
						case "Number of iterations": 
							solution = gs.gaussSeidilwithItrations(A,B, Integer.parseInt(parameterTextArea.getText()), initial, percision);
							break;
						case "Absolute Relative Error":
							solution=gs.gaussSeidilwitherror(A, B, Double.parseDouble(parameterTextArea.getText()), initial, percision);
							break;
						default:
							throw new RuntimeErrorException(null, "Please Choose the Required parameter");
						}
						stepsTextArea.setText(gs.steps.toString());
						outputTextArea.setText(help.SolutionToString(solution));
						break;
					case "Jacobi Iteration":
						double [] initial1 = new double[sizeOfArray];
						Handling.recievedInitialGuess (initialTextArea.getText(), percision, sizeOfArray, initial1);
						JacobiIteration j = new JacobiIteration();
						switch (parameter) {
						case "Number of iterations": 
							solution = j.jacobiwithItrations(A, B, Integer.parseInt(parameterTextArea.getText()), initial1, percision);
							break;
						case "Absolute Relative Error":
							solution = j.jacobiwitherror(A, B, Double.parseDouble(parameterTextArea.getText()), initial1, percision);
							break;
						default:
							throw new RuntimeErrorException(null, "Please Choose the Required parameter");
						}
						stepsTextArea.setText(j.steps.toString());
						outputTextArea.setText(help.SolutionToString(solution));
						break;
					default:
						throw new RuntimeErrorException(null, "Please Choose the Required Method");
					}
				
				} catch (Exception e) {
					//JOptionPane.showConfirmDialog(null, e.getMessage());
					JOptionPane.showMessageDialog(null, e.getMessage());
					stepsTextArea.setText("");
					outputTextArea.setText("");
				}
			}
		});
		btnOk.setFont(new Font("Tempus Sans ITC", Font.BOLD, 15));
		btnOk.setBounds(373, 343, 89, 73);
		frame.getContentPane().add(btnOk);

		equationScrollPane = new JScrollPane();
		equationScrollPane.setBounds(10, 87, 452, 250);// 250=>160
		frame.getContentPane().add(equationScrollPane);

		equationTextArea = new JTextArea();
		equationTextArea.setFont(new Font("Monospaced", Font.PLAIN, 18));
		equationScrollPane.setViewportView(equationTextArea);

		JScrollPane stepsScrollPane = new JScrollPane();
		stepsScrollPane.setBounds(472, 87, 602, 329);
		frame.getContentPane().add(stepsScrollPane);

		stepsTextArea = new JTextArea();
		stepsTextArea.setFont(new Font("Monospaced", Font.PLAIN, 18));
		stepsTextArea.setEditable(false);
		stepsScrollPane.setViewportView(stepsTextArea);

		JLabel lblMethod = new JLabel("Method :");
		lblMethod.setFont(new Font("Tempus Sans ITC", Font.BOLD, 16));
		lblMethod.setForeground(Color.YELLOW);
		lblMethod.setBounds(10, 346, 81, 14);
		frame.getContentPane().add(lblMethod);

		lblParameters = new JLabel("Parameters :");
		lblParameters.setForeground(Color.YELLOW);
		lblParameters.setFont(new Font("Tempus Sans ITC", Font.BOLD, 16));
		lblParameters.setBounds(10, 399, 105, 14);
		lblParameters.setVisible(false);
		frame.getContentPane().add(lblParameters);

		JLabel lblEquations = new JLabel("Equations:");
		lblEquations.setForeground(Color.YELLOW);
		lblEquations.setFont(new Font("Tempus Sans ITC", Font.BOLD, 16));
		lblEquations.setBounds(10, 62, 81, 14);
		frame.getContentPane().add(lblEquations);

		JLabel lblSteps = new JLabel("Steps:");
		lblSteps.setForeground(Color.YELLOW);
		lblSteps.setFont(new Font("Tempus Sans ITC", Font.BOLD, 16));
		lblSteps.setBounds(472, 62, 81, 14);
		frame.getContentPane().add(lblSteps);

		lblInitialGuess = new JLabel("Initial Guess :");
		lblInitialGuess.setForeground(Color.YELLOW);
		lblInitialGuess.setFont(new Font("Tempus Sans ITC", Font.BOLD, 16));
		lblInitialGuess.setBounds(10, 258, 204, 14);
		lblInitialGuess.setVisible(false);
		frame.getContentPane().add(lblInitialGuess);

		lblParameter = new JLabel("parameter");
		lblParameter.setForeground(Color.YELLOW);
		lblParameter.setFont(new Font("Tempus Sans ITC", Font.BOLD, 16));
		lblParameter.setBounds(257, 258, 204, 14);
		lblParameter.setVisible(false);
		frame.getContentPane().add(lblParameter);

		JLabel lblOutput = new JLabel("Output:");
		lblOutput.setForeground(Color.YELLOW);
		lblOutput.setFont(new Font("Tempus Sans ITC", Font.BOLD, 16));
		lblOutput.setBounds(10, 438, 105, 14);
		frame.getContentPane().add(lblOutput);

		JScrollPane outputScrollPane = new JScrollPane();
		outputScrollPane.setBounds(10, 463, 1064, 73);
		frame.getContentPane().add(outputScrollPane);

		outputTextArea = new JTextArea();
		outputTextArea.setFont(new Font("Monotype Corsiva", Font.BOLD | Font.ITALIC, 28));
		outputTextArea.setEditable(false);
		outputScrollPane.setViewportView(outputTextArea);

		spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(new Integer(4), new Integer(0), null, new Integer(1)));
		spinner.setBounds(268, 13, 55, 20);
		frame.getContentPane().add(spinner);

		JLabel lblPrecision = new JLabel("precision:");
		lblPrecision.setForeground(Color.YELLOW);
		lblPrecision.setFont(new Font("Tempus Sans ITC", Font.BOLD, 16));
		lblPrecision.setBounds(183, 15, 81, 14);
		frame.getContentPane().add(lblPrecision);

		initialScrollPane = new JScrollPane();
		initialScrollPane.setBounds(10, 283, 204, 32);
		initialScrollPane.setVisible(false);
		frame.getContentPane().add(initialScrollPane);

		initialTextArea = new JTextArea();
		initialTextArea.setFont(new Font("Monospaced", Font.PLAIN, 18));
		initialScrollPane.setViewportView(initialTextArea);

		parameterScrollPane = new JScrollPane();
		parameterScrollPane.setBounds(257, 283, 205, 32);
		parameterScrollPane.setVisible(false);
		frame.getContentPane().add(parameterScrollPane);

		parameterTextArea = new JTextArea();
		parameterTextArea.setFont(new Font("Monospaced", Font.PLAIN, 18));
		parameterScrollPane.setViewportView(parameterTextArea);
	}
}
